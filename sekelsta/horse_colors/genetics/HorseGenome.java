package sekelsta.horse_colors.genetics;

import com.google.common.collect.*;
import sekelsta.horse_colors.config.*;
import sekelsta.horse_colors.entity.*;
import sekelsta.horse_colors.*;
import sekelsta.horse_colors.genetics.breed.*;
import sekelsta.horse_colors.renderer.*;
import sekelsta.horse_colors.util.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;

public class HorseGenome extends Genome
{
    public static final ImmutableList<String> genes;
    public static final ImmutableList<String> genericChromosomes;
    public static final ImmutableList<String> stats;
    public static final ImmutableList<String> chromosomes;
    
    public HorseGenome(final Species species, final IGeneticEntity entityIn) {
        super(species, entityIn);
    }
    
    public HorseGenome(final Species species) {
        super(species);
    }
    
    public ImmutableList<String> listGenes() {
        return HorseGenome.genes;
    }
    
    public ImmutableList<String> listGenericChromosomes() {
        return HorseGenome.genericChromosomes;
    }
    
    public ImmutableList<String> listStats() {
        return HorseGenome.stats;
    }
    
    @Override
    public List<Linkage> listLinkages() {
        final List<Linkage> linkages = super.listLinkages();
        linkages.add(new Linkage("extension", 0.015f));
        linkages.add(new Linkage("KIT"));
        linkages.add(new Linkage("agouti", 0.0f));
        linkages.add(new Linkage("light_belly"));
        return linkages;
    }
    
    @Override
    public int getGeneSize(final String gene) {
        switch (gene) {
            case "KIT": {
                return 6;
            }
            case "MITF":
            case "PAX3":
            case "speed1":
            case "speed2":
            case "speed3":
            case "athletics1":
            case "athletics2":
            case "jump1":
            case "jump2":
            case "jump3":
            case "health1":
            case "health2":
            case "health3":
            case "stamina": {
                return 4;
            }
            case "cream":
            case "extension":
            case "agouti": {
                return 3;
            }
            case "dun": {
                return 2;
            }
            default: {
                return 1;
            }
        }
    }
    
    public void printGeneLocations() {
        for (final String gene : HorseGenome.genes) {
            System.out.println(gene + ": size=" + this.getGeneSize(gene) + ", pos=" + this.getGenePos(gene) + ", chr=" + this.getGeneChromosome(gene));
        }
    }
    
    public boolean isChestnut() {
        final int e = this.getMaxAllele("extension");
        return e == 0 || e == 1 || e == 2 || e == 3;
    }
    
    public boolean hasCream() {
        return this.hasAllele("cream", 3);
    }
    
    public boolean isPearl() {
        return this.isHomozygous("cream", 2);
    }
    
    public boolean isDoubleCream() {
        return this.isHomozygous("cream", 3) || this.isHomozygous("cream", 1) || (this.hasAllele("cream", 3) && this.hasAllele("cream", 1));
    }
    
    public boolean isCreamPearl() {
        return (this.hasAllele("cream", 3) || this.hasAllele("cream", 1)) && this.hasAllele("cream", 2);
    }
    
    public boolean isSilver() {
        return this.hasAllele("silver", 1);
    }
    
    public boolean isGray() {
        return this.hasAllele("gray", 1);
    }
    
    public boolean isDun() {
        return this.hasAllele("donkey_dun", 0) && (this.hasAllele("dun", 2) || this.isHomozygous("dun", 3));
    }
    
    public boolean hasStripe() {
        return !this.isHomozygous("dun", 0) && !this.isHomozygous("donkey_dun", 2) && (this.hasAllele("dun", 2) || this.hasAllele("donkey_dun", 0) || (!this.hasAllele("dun", 0) && this.hasAllele("dun", 3)));
    }
    
    public boolean isMealy() {
        return (this.getAllele("light_belly", 0) == 1 && this.getAllele("agouti", 0) != 0) || (this.getAllele("light_belly", 1) == 1 && this.getAllele("agouti", 1) != 0);
    }
    
    public boolean hasMC1RWhiteBoost() {
        return this.isChestnut();
    }
    
    public boolean isTobiano() {
        return HorseAlleles.isTobianoAllele(this.getAllele("KIT", 0)) || HorseAlleles.isTobianoAllele(this.getAllele("KIT", 1));
    }
    
    public boolean isWhite() {
        return this.hasAllele("KIT", 15) || this.isLethalWhite() || this.isHomozygous("KIT", 12) || (this.hasAllele("KIT", 12) && this.hasAllele("frame", 1) && this.isTobiano());
    }
    
    public boolean showsLegMarkings() {
        return !this.isWhite() && !this.isTobiano();
    }
    
    public boolean isDappleInclined() {
        return this.isHomozygous("dapple", 1);
    }
    
    public boolean isLethalWhite() {
        return this.isHomozygous("frame", 1);
    }
    
    public boolean isEmbryonicLethal() {
        return this.isHomozygous("KIT", 15);
    }
    
    public boolean hasERURiskFactor() {
        return (this.getChromosome("mhc1") >>> 8) % 4 == 3;
    }
    
    public int getSootyLevel() {
        return 1 + this.getMaxAllele("sooty1") + this.getMaxAllele("sooty2") - this.getMaxAllele("sooty3");
    }
    
    public float getGrayRate() {
        final int gray = this.countAlleles("gray", 1);
        float rate = 3.0f * (3 - gray);
        if (this.isHomozygous("slow_gray1", 1)) {
            rate *= 1.5f;
        }
        else if (this.hasAllele("slow_gray1", 1)) {
            rate *= 1.2f;
        }
        if (this.hasAllele("slow_gray2", 1)) {
            rate *= 1.3f;
        }
        if (this.isHomozygous("slow_gray3", 1)) {
            rate *= 1.2f;
        }
        if (this.hasAllele("gray_mane1", 1)) {
            rate *= 1.2f;
        }
        return rate;
    }
    
    public float getGrayManeRate() {
        float rate = this.getGrayRate();
        if (this.hasAllele("gray_mane1", 0)) {
            rate *= 0.9f;
        }
        if (this.isHomozygous("gray_mane2", 0)) {
            rate *= 0.9f;
        }
        return rate * 17.0f / 19.0f;
    }
    
    public float getImmuneHealth() {
        final float scale = 8.0f;
        int diffs = this.countDiffs(this.getChromosome("mhc1"));
        diffs += this.countDiffs(this.getChromosome("mhc2"));
        diffs += this.countDiffs(this.getChromosome("immune"));
        float heterozygosity = diffs / 24.0f;
        if (heterozygosity > 1.0f) {
            heterozygosity = 0.25f * (heterozygosity - 1.0f) + 1.0f;
        }
        return scale * heterozygosity;
    }
    
    public float getGrayHealthLoss() {
        float base = (float)this.countAlleles("gray", 1);
        if (this.isHomozygous("gray_melanoma", 0)) {
            --base;
        }
        if (this.isWhite()) {
            base -= 1.5f;
        }
        return Math.max(0.0f, base);
    }
    
    public float getSilverHealthLoss() {
        if (this.isHomozygous("silver", 1)) {
            return 1.0f;
        }
        if (this.hasAllele("silver", 1)) {
            return 0.5f;
        }
        return 0.0f;
    }
    
    public float getDeafHealthLoss() {
        if (HorsePatternCalculator.hasPigmentInEars(this)) {
            return 0.0f;
        }
        return 1.0f;
    }
    
    public float getERUHealthLoss() {
        if (this.hasERURiskFactor()) {
            return 0.5f * this.countAlleles("leopard", 1);
        }
        return 0.0f;
    }
    
    public float getBaseHealth() {
        if (HorseConfig.getEnableHealthEffects()) {
            return -this.getGrayHealthLoss() - this.getSilverHealthLoss() - this.getDeafHealthLoss() - this.getERUHealthLoss();
        }
        return 0.0f;
    }
    
    public float getHealth() {
        final float healthStat = this.getStatValue("health1") + this.getStatValue("health2") + this.getStatValue("health3") + this.getImmuneHealth();
        float maxHealth = 15.0f + healthStat * 0.5f;
        maxHealth += this.getBaseHealth();
        return maxHealth;
    }
    
    public int countW20() {
        return this.countAlleles("KIT", 7) + this.countAlleles("KIT", 13);
    }
    
    public boolean clientNeedsAge() {
        return this.isGray() || (HorseConfig.getGrowsGradually() && this.entity instanceof AbstractHorseGenetic && ((AbstractHorseGenetic)this.entity).func_70631_g_());
    }
    
    public int getAge() {
        if (this.entity instanceof AbstractHorseGenetic) {
            return ((AbstractHorseGenetic)this.entity).getDisplayAge();
        }
        return 0;
    }
    
    public int chooseRandomAllele(final List<Float> distribution) {
        final float n = this.entity.getRand().nextFloat();
        for (int i = 0; i < distribution.size(); ++i) {
            if (n < distribution.get(i)) {
                return i;
            }
        }
        return distribution.size() - 1;
    }
    
    public void randomizeNamedGenes(final Map<String, List<Float>> map) {
        for (final String gene : HorseGenome.genes) {
            if (map.containsKey(gene)) {
                final List<Float> distribution = map.get(gene);
                final int left = this.chooseRandomAllele(distribution);
                final int right = this.chooseRandomAllele(distribution);
                final int size = this.getGeneSize(gene);
                this.setNamedGene(gene, left << size | right);
            }
            else {
                HorseColors.logger.debug(gene + " is not in the given map");
                this.setNamedGene(gene, 0);
            }
        }
    }
    
    public void randomize(final Breed breed) {
        this.randomizeNamedGenes(breed.colors);
        if (this.isHomozygous("frame", 1)) {
            this.setNamedGene("frame", 1);
        }
        if (this.isHomozygous("KIT", 15)) {
            this.setNamedGene("KIT", 15);
        }
        for (final String stat : this.listGenericChromosomes()) {
            this.entity.setChromosome(stat, this.entity.getRand().nextInt());
        }
        this.entity.setChromosome("random", this.entity.getRand().nextInt());
        this.entity.setMale(HorseGenome.rand.nextBoolean());
    }
    
    private String getAbv(final TextureLayer layer) {
        if (layer == null || layer.name == null) {
            return "";
        }
        String abv = layer.toString() + "_";
        if (layer.next != null) {
            abv = abv + ".-" + this.getAbv(layer.next) + "-.";
        }
        return abv.toLowerCase();
    }
    
    public String judgeStatRaw(final int val) {
        if (val <= 0) {
            return "worst";
        }
        if (val <= 2) {
            return "bad";
        }
        if (val <= 5) {
            return "avg";
        }
        if (val <= 7) {
            return "good";
        }
        return "best";
    }
    
    public String judgeStat(final int val, final String loc) {
        return Util.translate(loc + this.judgeStatRaw(val));
    }
    
    public String judgeStat(final String stat) {
        return Util.translate("stats." + this.judgeStatRaw(this.getStatValue(stat)));
    }
    
    @Override
    public List<List<String>> getBookContents() {
        final List<List<String>> contents = new ArrayList<List<String>>();
        final List<String> physical = new ArrayList<String>();
        physical.add(Util.translate("book.physical"));
        String health = Util.translate("stats.health") + "\n";
        health = health + "  " + Util.translate("stats.health1") + ": " + this.judgeStat("health1") + "\n";
        health = health + "  " + Util.translate("stats.health2") + ": " + this.judgeStat("health2") + "\n";
        health = health + "  " + Util.translate("stats.health3") + ": " + this.judgeStat("health3") + "\n";
        health = health + "  " + Util.translate("stats.immune") + ": " + this.judgeStat((int)this.getImmuneHealth(), "stats.immune.");
        String healthEffects = "";
        if (HorseConfig.getEnableHealthEffects()) {
            if (this.getDeafHealthLoss() > 0.5f) {
                healthEffects = healthEffects + "\n" + Util.translate("stats.health.deaf");
            }
            final float h = this.getHealth() + this.getSilverHealthLoss();
            if ((int)this.getHealth() != (int)h) {
                healthEffects = healthEffects + "\n" + Util.translate("stats.health.MCOA");
            }
            final float h2 = h + this.getGrayHealthLoss();
            if ((int)h != (int)h2) {
                healthEffects = healthEffects + "\n" + Util.translate("stats.health.melanoma");
            }
            if ((int)h2 != (int)(h2 + this.getERUHealthLoss())) {
                healthEffects = healthEffects + "\n" + Util.translate("stats.health.ERU");
            }
            if (this.isHomozygous("leopard", 1)) {
                healthEffects = healthEffects + "\n" + Util.translate("stats.health.CSNB");
            }
        }
        physical.add(health);
        String athletics = Util.translate("stats.athletics") + "\n";
        athletics = athletics + "  " + Util.translate("stats.athletics1") + ": " + this.judgeStat("athletics1") + "\n";
        athletics = athletics + "  " + Util.translate("stats.athletics2") + ": " + this.judgeStat("athletics2");
        physical.add(athletics);
        String speed = Util.translate("stats.speed") + "\n";
        speed = speed + "  " + Util.translate("stats.speed1") + ": " + this.judgeStat("speed1") + "\n";
        speed = speed + "  " + Util.translate("stats.speed2") + ": " + this.judgeStat("speed2") + "\n";
        speed = speed + "  " + Util.translate("stats.speed3") + ": " + this.judgeStat("speed3");
        physical.add(speed);
        String jump = Util.translate("stats.jump") + "\n";
        jump = jump + "  " + Util.translate("stats.jump1") + ": " + this.judgeStat("jump1") + "\n";
        jump = jump + "  " + Util.translate("stats.jump2") + ": " + this.judgeStat("jump2") + "\n";
        jump = jump + "  " + Util.translate("stats.jump3") + ": " + this.judgeStat("jump3");
        physical.add(jump);
        physical.add(healthEffects);
        if (HorseConfig.GENETICS.useGeneticStats && HorseConfig.GENETICS.bookShowsTraits) {
            contents.add(physical);
        }
        List<String> genelist = (List<String>)ImmutableList.of((Object)"extension", (Object)"agouti", (Object)"dun", (Object)"gray", (Object)"cream", (Object)"silver", (Object)"KIT", (Object)"frame", (Object)"MITF", (Object)"leopard", (Object)"PATN1");
        if (this.species == Species.DONKEY) {
            genelist = (List<String>)ImmutableList.of((Object)"extension", (Object)"agouti", (Object)"KIT");
        }
        final List<String> genetic = new ArrayList<String>();
        genetic.add(Util.translate("book.genetic"));
        for (final String gene : genelist) {
            if (gene.equals("KIT") && this.species != Species.DONKEY) {
                final String tobianoLocation = "genes.tobiano";
                String tobi = Util.translate(tobianoLocation + ".name") + ": ";
                final String a1 = HorseAlleles.isTobianoAllele(this.getAllele("KIT", 0)) ? "Tobiano" : "Wildtype";
                final String a2 = HorseAlleles.isTobianoAllele(this.getAllele("KIT", 1)) ? "Tobiano" : "Wildtype";
                tobi = tobi + Util.translate(tobianoLocation + ".allele" + a1) + "/";
                tobi += Util.translate(tobianoLocation + ".allele" + a2);
                genetic.add(tobi);
            }
            final String translationLocation = "genes." + gene;
            String s = Util.translate(translationLocation + ".name") + ": ";
            s = s + Util.translate(translationLocation + ".allele" + this.getAllele(gene, 0)) + "/";
            s += Util.translate(translationLocation + ".allele" + this.getAllele(gene, 1));
            genetic.add(s);
        }
        if (HorseConfig.getBookShowsGenes()) {
            contents.add(genetic);
        }
        return contents;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void setTexturePaths() {
        this.textureLayers = HorseColorCalculator.getTexturePaths(this);
        this.textureCacheName = "horse/cache_";
        for (int i = 0; i < this.textureLayers.size(); ++i) {
            this.textureCacheName += this.getAbv(this.textureLayers.get(i));
        }
    }
    
    @Override
    public String genesToString() {
        String answer = this.entity.isMale() ? "M" : "F";
        for (final String chr : HorseGenome.chromosomes) {
            answer += String.format("%1$08X", this.getChromosome(chr));
        }
        return answer;
    }
    
    @Override
    public void genesFromString(String s) {
        if (s.length() % 8 != 0) {
            final String g = s.substring(0, 1);
            this.entity.setMale(g.equals("M"));
            s = s.substring(1);
        }
        for (int i = 0; i < HorseGenome.chromosomes.size(); ++i) {
            int val = 0;
            try {
                final String c = s.substring(8 * i, 8 * (i + 1));
                val = (int)Long.parseLong(c, 16);
            }
            catch (IndexOutOfBoundsException ex) {}
            catch (NumberFormatException ex2) {}
            this.entity.setChromosome((String)HorseGenome.chromosomes.get(i), val);
        }
        if (s.length() <= 88) {
            this.datafixAddingFourthChromosome();
        }
    }
    
    @Override
    public boolean isValidGeneString(String s) {
        if (s.length() < 2) {
            return false;
        }
        if (s.length() % 8 == 0) {
            return s.matches("[0-9a-fA-F]*");
        }
        final String g = s.substring(0, 1);
        if (!g.equals("M") && !g.equals("F")) {
            return false;
        }
        s = s.substring(1);
        return s.length() % 8 == 0 && s.matches("[0-9a-fA-F]*");
    }
    
    public void datafixAddingFourthChromosome() {
        final int prevSplash = this.getNamedGene("MITF");
        this.setAllele("MITF", 0, prevSplash & 0x3);
        this.setAllele("MITF", 1, prevSplash >>> 2 & 0x3);
        this.setAllele("PAX3", 0, prevSplash >>> 4 & 0x3);
        this.setAllele("PAX3", 1, prevSplash >>> 6 & 0x3);
        final int prevKIT = this.getNamedGene("KIT");
        this.setAllele("white_suppression", 0, prevKIT & 0x1);
        this.setAllele("white_suppression", 1, prevKIT >>> 1 & 0x1);
        this.setAllele("KIT", 0, prevKIT >>> 2 & 0xF);
        this.setAllele("KIT", 1, prevKIT >>> 6 & 0xF);
        this.setAllele("frame", 0, prevKIT >>> 10 & 0x1);
        this.setAllele("frame", 1, prevKIT >>> 11 & 0x1);
        final int prevCream = this.getNamedGene("cream");
        this.setAllele("cream", 0, prevCream & 0x3);
        this.setAllele("cream", 1, prevCream >>> 2 & 0x3);
        this.setAllele("silver", 0, prevCream >>> 4 & 0x1);
        this.setAllele("silver", 1, prevCream >>> 5 & 0x1);
    }
    
    static {
        genes = ImmutableList.of((Object)"extension", (Object)"agouti", (Object)"dun", (Object)"gray", (Object)"cream", (Object)"liver", (Object)"flaxen1", (Object)"flaxen2", (Object)"dapple", (Object)"sooty1", (Object)"sooty2", (Object)"sooty3", (Object[])new String[] { "light_belly", "mealy1", "mealy2", "KIT", "MITF", "leopard", "PATN1", "PATN2", "PATN3", "gray_suppression", "slow_gray1", "slow_gray2", "slow_gray3", "white_star", "white_forelegs", "white_hindlegs", "gray_melanoma", "gray_mane1", "gray_mane2", "rufous", "dense", "champagne", "cameo", "ivory", "donkey_dark", "cross", "reduced_points", "light_legs", "less_light_legs", "donkey_dun", "flaxen_boost", "light_dun", "marble", "leopard_suppression", "leopard_suppression2", "PATN_boost1", "PATN_boost2", "PAX3", "white_suppression", "frame", "silver", "dark_red", "leg_stripes", "stripe_spacing" });
        genericChromosomes = ImmutableList.of((Object)"speed", (Object)"jump", (Object)"health", (Object)"mhc1", (Object)"mhc2", (Object)"immune");
        stats = ImmutableList.of((Object)"speed1", (Object)"speed2", (Object)"speed3", (Object)"athletics1", (Object)"athletics2", (Object)"jump1", (Object)"jump2", (Object)"jump3", (Object)"health1", (Object)"health2", (Object)"health3", (Object)"stamina", (Object[])new String[0]);
        chromosomes = ImmutableList.of((Object)"0", (Object)"1", (Object)"2", (Object)"3", (Object)"speed", (Object)"jump", (Object)"health", (Object)"mhc1", (Object)"mhc2", (Object)"immune", (Object)"random", (Object)"4", (Object[])new String[0]);
    }
}
