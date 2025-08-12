package sekelsta.horse_colors.genetics;

import sekelsta.horse_colors.renderer.*;
import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import sekelsta.horse_colors.config.*;

public abstract class Genome
{
    public final Species species;
    protected IGeneticEntity entity;
    protected String textureCacheName;
    protected List<TextureLayer> textureLayers;
    public static Random rand;
    
    public abstract List<String> listGenes();
    
    public abstract List<String> listGenericChromosomes();
    
    public abstract List<String> listStats();
    
    public List<Linkage> listLinkages() {
        final ArrayList linkages = new ArrayList();
        for (final String gene : this.listGenes()) {
            linkages.add(new Linkage(gene));
        }
        return (List<Linkage>)linkages;
    }
    
    public Genome(final Species species) {
        this(species, new FakeGeneticEntity());
    }
    
    public Genome(final Species species, final IGeneticEntity entityIn) {
        this.species = species;
        this.entity = entityIn;
    }
    
    public void resetTexture() {
        this.textureCacheName = null;
    }
    
    public abstract List<List<String>> getBookContents();
    
    public abstract void setTexturePaths();
    
    public abstract String genesToString();
    
    public abstract void genesFromString(final String p0);
    
    public abstract boolean isValidGeneString(final String p0);
    
    @SideOnly(Side.CLIENT)
    public String getTexture() {
        if (this.textureCacheName == null) {
            this.setTexturePaths();
        }
        return this.textureCacheName;
    }
    
    @SideOnly(Side.CLIENT)
    public List<TextureLayer> getVariantTexturePaths() {
        if (this.textureCacheName == null) {
            this.setTexturePaths();
        }
        return this.textureLayers;
    }
    
    public abstract int getGeneSize(final String p0);
    
    public int getChromosome(final String name) {
        return this.entity.getChromosome(name);
    }
    
    public void setNamedGene(final String name, final int val) {
        final String chr = this.getGeneChromosome(name);
        this.entity.setChromosome(chr, (this.entity.getChromosome(chr) & ~this.getGeneLoci(name)) | val << this.getGenePos(name) % 32);
    }
    
    public int getNamedGene(final String name) {
        final String chr = this.getGeneChromosome(name);
        return (this.entity.getChromosome(chr) & this.getGeneLoci(name)) >>> this.getGenePos(name);
    }
    
    public int getRawStat(final String name) {
        final String chr = this.listGenericChromosomes().get(this.getStatPos(name) / 32);
        return this.entity.getChromosome(chr) & this.getStatLoci(name);
    }
    
    public int getStatValue(final String name) {
        final int val = this.getRawStat(name);
        return this.countBits(val);
    }
    
    public int countBits(int val) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            count += (val % 2 + 2) % 2;
            val >>= 1;
        }
        return count;
    }
    
    public int countDiffs(int val) {
        int count = 0;
        for (int i = 0; i < 16; ++i) {
            final int one = (val % 2 + 2) % 2;
            val >>= 1;
            final int two = (val % 2 + 2) % 2;
            val >>= 1;
            count += (one ^ two);
        }
        return count;
    }
    
    public int getGenePos(final String name) {
        return this.getPos(name, this.listGenes());
    }
    
    public int getStatPos(final String name) {
        return this.getPos(name, this.listStats());
    }
    
    private int getPos(final String name, final List<String> genes) {
        int i = 0;
        for (final String gene : genes) {
            final int next = i + 2 * this.getGeneSize(gene);
            if (next / 32 != i / 32 && next % 32 != 0) {
                i = (i / 32 + 1) * 32;
            }
            if (gene == name) {
                return i;
            }
            i += 2 * this.getGeneSize(gene);
        }
        System.out.println("Gene not recognized: " + name);
        return -1;
    }
    
    public int getGeneLoci(final String gene) {
        return this.getLoci(gene, this.getGenePos(gene));
    }
    
    public int getStatLoci(final String gene) {
        return this.getLoci(gene, this.getStatPos(gene));
    }
    
    private int getLoci(final String gene, final int pos) {
        return (1 << 2 * this.getGeneSize(gene)) - 1 << pos % 32;
    }
    
    public String getGeneChromosome(final String gene) {
        return Integer.toString(this.getGenePos(gene) / 32);
    }
    
    public int getAllele(final String name, final int n) {
        int gene = this.getNamedGene(name);
        gene >>= n * this.getGeneSize(name);
        gene %= 1 << this.getGeneSize(name);
        return gene;
    }
    
    public void setAllele(final String name, final int n, final int v) {
        final int other = this.getAllele(name, 1 - n);
        final int size = this.getGeneSize(name);
        this.setNamedGene(name, other << (1 - n) * size | v << n * size);
    }
    
    public void mutateAllele(final String gene, final int n) {
        final Map<String, List<Float>> map = this.entity.getDefaultBreed().colors;
        if (!map.containsKey(gene)) {
            return;
        }
        final List<Float> frequencies = map.get(gene);
        final List<Integer> allowedAlleles = new ArrayList<Integer>();
        float val = 0.0f;
        for (int i = 0; i < frequencies.size() && val < 1.0f; ++i) {
            if (val < frequencies.get(i)) {
                allowedAlleles.add(i);
                val = frequencies.get(i);
            }
        }
        final int size = allowedAlleles.size();
        final int v = allowedAlleles.get(Genome.rand.nextInt(size));
        this.setAllele(gene, n, v);
    }
    
    public void mutateAlleleChance(final String gene, final int n, final double p) {
        if (Genome.rand.nextDouble() < p) {
            this.mutateAllele(gene, n);
        }
    }
    
    public int mutateIntMask(final double p) {
        int mask = 0;
        if (Genome.rand.nextDouble() < p) {
            ++mask;
        }
        for (int i = 1; i < 32; ++i) {
            mask <<= 1;
            if (Genome.rand.nextDouble() < p) {
                ++mask;
            }
        }
        return mask;
    }
    
    public void mutateGenericChromosome(final String name, final double p) {
        this.entity.setChromosome(name, this.entity.getChromosome(name) ^ this.mutateIntMask(p / 2.0));
    }
    
    public void mutate() {
        final double p = HorseConfig.getMutationChance();
        for (final String gene : this.listGenes()) {
            final int a = this.getAllele(gene, 0);
            final int b = this.getAllele(gene, 1);
            this.mutateAlleleChance(gene, 0, p);
            this.mutateAlleleChance(gene, 1, p);
        }
        for (final String stat : this.listGenericChromosomes()) {
            this.mutateGenericChromosome(stat, p);
        }
    }
    
    public boolean hasAllele(final String name, final int allele) {
        return this.getAllele(name, 0) == allele || this.getAllele(name, 1) == allele;
    }
    
    public int getMaxAllele(final String name) {
        return Math.max(this.getAllele(name, 0), this.getAllele(name, 1));
    }
    
    public boolean isHomozygous(final String name, final int allele) {
        return this.getAllele(name, 0) == allele && this.getAllele(name, 1) == allele;
    }
    
    public int countAlleles(final String gene, final int allele) {
        int count = 0;
        if (this.getAllele(gene, 0) == allele) {
            ++count;
        }
        if (this.getAllele(gene, 1) == allele) {
            ++count;
        }
        return count;
    }
    
    public int getRandomGenericGenes(final int n, final int data, final float linkage) {
        int rand = Genome.rand.nextInt(2);
        int answer = 0;
        for (int i = 0; i < 16; ++i) {
            if (Genome.rand.nextFloat() < linkage) {
                rand = 1 - rand;
            }
            answer += (data & 1 << 2 * i + rand) >> rand << n;
        }
        return answer;
    }
    
    public void inheritNamedGenes(final Genome parent1, final Genome parent2) {
        int rand1 = Genome.rand.nextInt(2);
        int rand2 = Genome.rand.nextInt(2);
        for (final Linkage link : this.listLinkages()) {
            final int allele1 = parent1.getAllele(link.gene, rand1);
            final int allele2 = parent2.getAllele(link.gene, rand2);
            this.setAllele(link.gene, 0, allele1);
            this.setAllele(link.gene, 1, allele2);
            if (Genome.rand.nextFloat() < link.p) {
                rand1 = 1 - rand1;
            }
            if (Genome.rand.nextFloat() < link.p) {
                rand2 = 1 - rand2;
            }
        }
    }
    
    public static String chrToStr(final int chr) {
        String s = "";
        for (int i = 16; i > 0; --i) {
            s += (chr >>> 2 * i - 1 & 0x1);
            s += (chr >>> 2 * i - 2 & 0x1);
            if (i > 1) {
                s += " ";
            }
        }
        return s;
    }
    
    public void inheritGenericGenes(final Genome parent1, final Genome parent2) {
        float linkage = 0.5f;
        for (final String chr : this.listGenericChromosomes()) {
            if (chr.startsWith("mhc")) {
                linkage = 0.05f;
            }
            else {
                linkage = 0.5f;
            }
            final int mother = parent1.getRandomGenericGenes(1, parent1.getChromosome(chr), linkage);
            final int father = parent2.getRandomGenericGenes(0, parent2.getChromosome(chr), linkage);
            this.entity.setChromosome(chr, mother | father);
        }
    }
    
    public void inheritGenes(final Genome parent1, final Genome parent2) {
        this.inheritNamedGenes(parent1, parent2);
        this.inheritGenericGenes(parent1, parent2);
        this.mutate();
    }
    
    static {
        Genome.rand = new Random();
    }
    
    public static class Linkage
    {
        public String gene;
        public float p;
        
        public Linkage(final String gene, final float p) {
            this.gene = gene;
            this.p = p;
        }
        
        public Linkage(final String gene) {
            this.gene = gene;
            this.p = 0.5f;
        }
    }
}
