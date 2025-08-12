package sekelsta.horse_colors.entity;

import net.minecraft.inventory.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.entity.ai.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.nbt.*;
import java.util.*;
import sekelsta.horse_colors.*;
import sekelsta.horse_colors.config.*;
import net.minecraft.util.*;
import sekelsta.horse_colors.item.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.entity.ai.attributes.*;
import sekelsta.horse_colors.genetics.breed.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import javax.annotation.*;
import net.minecraft.network.datasync.*;

public abstract class AbstractHorseGenetic extends AbstractChestHorse implements IGeneticEntity
{
    protected HorseGenome genes;
    protected static final DataParameter<Integer> HORSE_VARIANT;
    protected static final DataParameter<Integer> HORSE_VARIANT2;
    protected static final DataParameter<Integer> HORSE_VARIANT3;
    protected static final DataParameter<Integer> HORSE_VARIANT4;
    protected static final DataParameter<Integer> HORSE_VARIANT5;
    protected static final DataParameter<Integer> HORSE_SPEED;
    protected static final DataParameter<Integer> HORSE_JUMP;
    protected static final DataParameter<Integer> HORSE_HEALTH;
    protected static final DataParameter<Integer> HORSE_MHC1;
    protected static final DataParameter<Integer> HORSE_MHC2;
    protected static final DataParameter<Integer> HORSE_IMMUNE;
    protected static final DataParameter<Integer> HORSE_RANDOM;
    protected static final DataParameter<Integer> DISPLAY_AGE;
    protected static final DataParameter<Boolean> GENDER;
    protected static final DataParameter<Boolean> IS_CASTRATED;
    protected static final DataParameter<Integer> PREGNANT_SINCE;
    protected int trueAge;
    protected static final UUID CSNB_SPEED_UUID;
    protected static final UUID CSNB_JUMP_UUID;
    protected static final AttributeModifier CSNB_SPEED_MODIFIER;
    protected static final AttributeModifier CSNB_JUMP_MODIFIER;
    protected static final int HORSE_GENETICS_VERSION = 1;
    protected List<AbstractHorseGenetic> unbornChildren;
    
    public AbstractHorseGenetic(final World worldIn) {
        super(worldIn);
        this.unbornChildren = new ArrayList<AbstractHorseGenetic>();
        this.setChromosome("random", this.field_70146_Z.nextInt());
        this.setMale(this.field_70146_Z.nextBoolean());
        this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)(-1));
    }
    
    public void copyAbstractHorse(final AbstractHorse horse) {
        this.randomize();
        this.func_70012_b(horse.field_70165_t, horse.field_70163_u, horse.field_70161_v, horse.field_70177_z, horse.field_70125_A);
        this.func_110234_j(horse.func_110248_bS());
        this.func_110238_s(horse.func_110252_cg());
        this.func_70873_a(horse.func_70874_b());
        this.trueAge = horse.func_70874_b();
        final ContainerHorseChest inv = (ContainerHorseChest)ReflectionHelper.getPrivateValue((Class)AbstractHorse.class, (Object)horse, new String[] { "horseChest", "field_110296_bG" });
        this.field_110296_bG.func_70299_a(0, inv.func_70301_a(0));
        this.field_110296_bG.func_70299_a(1, inv.func_70301_a(1));
        this.func_110232_cE();
        final double health = horse.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(health);
        final double jump = horse.func_110148_a(AbstractHorseGenetic.field_110271_bv).func_111125_b();
        this.func_110148_a(AbstractHorseGenetic.field_110271_bv).func_111128_a(jump);
        final double speed = horse.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(speed);
        this.useGeneticAttributes();
    }
    
    public HorseGenome getGenes() {
        return this.genes;
    }
    
    public abstract boolean fluffyTail();
    
    public abstract boolean longEars();
    
    public abstract boolean thinMane();
    
    public abstract Species getSpecies();
    
    public boolean canEquipChest() {
        return true;
    }
    
    public Random getRand() {
        return super.func_70681_au();
    }
    
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.2));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIRunAroundLikeCrazy((AbstractHorse)this, 1.2));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new GenderedBreedGoal((EntityAnimal)this, 1.0, (Class<? extends EntityAnimal>)AbstractHorse.class));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new RandomWalkGroundTie((EntityCreature)this, 0.7));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }
    
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT2, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT3, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT4, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT5, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_SPEED, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_HEALTH, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_MHC1, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_MHC2, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_IMMUNE, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_JUMP, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.HORSE_RANDOM, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.DISPLAY_AGE, (Object)0);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.GENDER, (Object)false);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.IS_CASTRATED, (Object)false);
        this.field_70180_af.func_187214_a((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)(-1));
    }
    
    public void func_70014_b(final NBTTagCompound compound) {
        super.func_70014_b(compound);
        this.getEntityData().func_74768_a("HorseGeneticsVersion", 1);
        compound.func_74768_a("Variant", this.getChromosome("0"));
        compound.func_74768_a("Variant2", this.getChromosome("1"));
        compound.func_74768_a("Variant3", this.getChromosome("2"));
        compound.func_74768_a("Variant4", this.getChromosome("3"));
        compound.func_74768_a("Variant5", this.getChromosome("4"));
        compound.func_74768_a("SpeedGenes", this.getChromosome("speed"));
        compound.func_74768_a("JumpGenes", this.getChromosome("jump"));
        compound.func_74768_a("HealthGenes", this.getChromosome("health"));
        compound.func_74768_a("MHC1", this.getChromosome("mhc1"));
        compound.func_74768_a("MHC2", this.getChromosome("mhc2"));
        compound.func_74768_a("Immune", this.getChromosome("immune"));
        compound.func_74768_a("Random", this.getChromosome("random"));
        compound.func_74768_a("true_age", this.trueAge);
        compound.func_74757_a("gender", this.isMale());
        compound.func_74757_a("is_castrated", this.isCastrated());
        compound.func_74768_a("pregnant_since", this.getPregnancyStart());
        if (this.unbornChildren != null) {
            final NBTTagList unbornChildrenTag = new NBTTagList();
            for (final AbstractHorseGenetic child : this.unbornChildren) {
                final NBTTagCompound childNBT = new NBTTagCompound();
                childNBT.func_74778_a("species", child.getSpecies().toString());
                childNBT.func_74778_a("genes", child.getGenes().genesToString());
                unbornChildrenTag.func_74742_a((NBTBase)childNBT);
            }
            compound.func_74782_a("unborn_children", (NBTBase)unbornChildrenTag);
        }
    }
    
    public void func_70037_a(final NBTTagCompound compound) {
        super.func_70037_a(compound);
        this.setChromosome("0", compound.func_74762_e("Variant"));
        this.setChromosome("1", compound.func_74762_e("Variant2"));
        this.setChromosome("2", compound.func_74762_e("Variant3"));
        this.setChromosome("3", compound.func_74762_e("Variant4"));
        if (compound.func_74764_b("Variant5")) {
            this.setChromosome("4", compound.func_74762_e("Variant5"));
        }
        else if (!this.getEntityData().func_74764_b("HorseGeneticsVersion")) {
            this.getEntityData().func_74768_a("HorseGeneticsVersion", 1);
            this.getGenes().datafixAddingFourthChromosome();
        }
        this.setChromosome("speed", compound.func_74762_e("SpeedGenes"));
        this.setChromosome("jump", compound.func_74762_e("JumpGenes"));
        this.setChromosome("health", compound.func_74762_e("HealthGenes"));
        if (compound.func_74764_b("MHC1")) {
            this.setChromosome("mhc1", compound.func_74762_e("MHC1"));
            this.setChromosome("mhc2", compound.func_74762_e("MHC2"));
        }
        else {
            this.getGenes().setNamedGene("leopard", 0);
            this.setChromosome("mhc1", this.field_70146_Z.nextInt());
            this.setChromosome("mhc2", this.field_70146_Z.nextInt());
        }
        if (compound.func_74764_b("Immune")) {
            this.setChromosome("immune", compound.func_74762_e("Immune"));
        }
        else {
            for (int i = 0; i < 2; ++i) {
                if (this.getGenes().getAllele("dun", i) == 3) {
                    this.getGenes().setAllele("dun", i, 2);
                }
                if (this.getGenes().getAllele("dun", i) == 1) {
                    this.getGenes().setAllele("dun", i, 0);
                }
            }
            this.getGenes().setNamedGene("gray_suppression", 0);
            this.setChromosome("immune", this.field_70146_Z.nextInt());
        }
        this.setChromosome("random", compound.func_74762_e("Random"));
        this.trueAge = compound.func_74762_e("true_age");
        if (compound.func_74764_b("gender")) {
            this.setMale(compound.func_74767_n("gender"));
        }
        else {
            this.setMale(this.field_70146_Z.nextBoolean());
        }
        this.setCastrated(compound.func_74767_n("is_castrated"));
        int pregnantSince = -1;
        if (compound.func_74764_b("pregnant_since")) {
            pregnantSince = compound.func_74762_e("pregnant_since");
        }
        this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)pregnantSince);
        if (compound.func_74764_b("unborn_children")) {
            final NBTBase nbt = compound.func_74781_a("unborn_children");
            if (nbt instanceof NBTTagList) {
                final NBTTagList childListTag = (NBTTagList)nbt;
                for (int j = 0; j < childListTag.func_74745_c(); ++j) {
                    final NBTBase cnbt = childListTag.func_179238_g(j);
                    if (cnbt instanceof NBTTagCompound) {
                        final NBTTagCompound childNBT = (NBTTagCompound)cnbt;
                        final Species species = Species.valueOf(childNBT.func_74779_i("species"));
                        AbstractHorseGenetic child = null;
                        switch (species) {
                            case HORSE: {
                                child = new HorseGeneticEntity(this.field_70170_p);
                                break;
                            }
                            case DONKEY: {
                                child = new DonkeyGeneticEntity(this.field_70170_p);
                                break;
                            }
                            case MULE: {
                                child = new MuleGeneticEntity(this.field_70170_p);
                                break;
                            }
                        }
                        if (child != null) {
                            final HorseGenome genome = new HorseGenome(child.getSpecies(), child);
                            genome.genesFromString(childNBT.func_74779_i("genes"));
                            this.unbornChildren.add(child);
                        }
                    }
                }
            }
        }
        this.func_110232_cE();
        if (this instanceof HorseGeneticEntity) {
            final int spawndata = compound.func_74762_e("VillageSpawn");
            if (spawndata != 0) {
                this.initFromVillageSpawn();
            }
        }
    }
    
    public int getDisplayAge() {
        return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.DISPLAY_AGE);
    }
    
    public void setDisplayAge(final int age) {
        this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.DISPLAY_AGE, (Object)age);
    }
    
    public void setChromosome(final String name, final int variant) {
        switch (name) {
            case "0": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_VARIANT, (Object)variant);
                this.getGenes().resetTexture();
            }
            case "1": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_VARIANT2, (Object)variant);
                this.getGenes().resetTexture();
            }
            case "2": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_VARIANT3, (Object)variant);
                this.getGenes().resetTexture();
            }
            case "3": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_VARIANT4, (Object)variant);
                this.getGenes().resetTexture();
            }
            case "4": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_VARIANT5, (Object)variant);
                this.getGenes().resetTexture();
            }
            case "speed": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_SPEED, (Object)variant);
                this.useGeneticAttributes();
            }
            case "jump": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_JUMP, (Object)variant);
                this.useGeneticAttributes();
            }
            case "health": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_HEALTH, (Object)variant);
                this.useGeneticAttributes();
            }
            case "mhc1": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_MHC1, (Object)variant);
                this.useGeneticAttributes();
            }
            case "mhc2": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_MHC2, (Object)variant);
                this.useGeneticAttributes();
            }
            case "immune": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_IMMUNE, (Object)variant);
                this.useGeneticAttributes();
            }
            case "random": {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.HORSE_RANDOM, (Object)variant);
            }
            default: {
                HorseColors.logger.error("Unrecognized horse data for setting: " + name + "\n");
            }
        }
    }
    
    public int getChromosome(final String name) {
        switch (name) {
            case "0": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT);
            }
            case "1": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT2);
            }
            case "2": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT3);
            }
            case "3": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT4);
            }
            case "4": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_VARIANT5);
            }
            case "speed": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_SPEED);
            }
            case "jump": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_JUMP);
            }
            case "health": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_HEALTH);
            }
            case "mhc1": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_MHC1);
            }
            case "mhc2": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_MHC2);
            }
            case "immune": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_IMMUNE);
            }
            case "random": {
                return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.HORSE_RANDOM);
            }
            default: {
                HorseColors.logger.error("Unrecognized horse data for getting: " + name + "\n");
                return 0;
            }
        }
    }
    
    public boolean isMale() {
        return (boolean)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.GENDER);
    }
    
    public void setMale(final boolean gender) {
        if (gender) {
            this.unbornChildren = new ArrayList<AbstractHorseGenetic>();
            this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)(-1));
        }
        else {
            this.setCastrated(false);
        }
        this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.GENDER, (Object)gender);
    }
    
    public boolean isCastrated() {
        return (boolean)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.IS_CASTRATED);
    }
    
    public void setCastrated(final boolean isCastrated) {
        this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.IS_CASTRATED, (Object)isCastrated);
    }
    
    public boolean isPregnant() {
        return this.getPregnancyStart() >= 0;
    }
    
    public int getPregnancyStart() {
        return (int)this.field_70180_af.func_187225_a((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE);
    }
    
    public float getPregnancyProgress() {
        final int passed = this.getDisplayAge() - this.getPregnancyStart();
        final int total = HorseConfig.getHorsePregnancyLength();
        return passed / (float)total;
    }
    
    public int getRebreedTicks() {
        return HorseConfig.getHorseRebreedTicks(this.isMale());
    }
    
    public int getBirthAge() {
        return HorseConfig.getHorseBirthAge();
    }
    
    public ContainerHorseChest getInventory() {
        return this.field_110296_bG;
    }
    
    protected SoundEvent func_184601_bQ(final DamageSource damageSourceIn) {
        if (damageSourceIn != DamageSource.field_76368_d && damageSourceIn != DamageSource.field_76367_g) {
            super.func_184601_bQ(damageSourceIn);
        }
        return null;
    }
    
    public void func_70873_a(final int age) {
        if (age == -24000 && this.func_70874_b() > age) {
            super.func_70873_a(this.getBirthAge());
        }
        else {
            super.func_70873_a(age);
        }
    }
    
    public boolean func_184645_a(final EntityPlayer player, final EnumHand hand) {
        final ItemStack itemstack = player.func_184586_b(hand);
        if (!itemstack.func_190926_b() && itemstack.func_77973_b() == Items.field_151063_bx) {
            return super.func_184645_a(player, hand);
        }
        if (!this.func_70631_g_()) {
            if (this.func_110248_bS() && player.func_70093_af()) {
                this.func_110199_f(player);
                return true;
            }
            if (this.func_184207_aI()) {
                return super.func_184645_a(player, hand);
            }
        }
        if (itemstack.func_190926_b()) {
            if (this.func_70631_g_()) {
                return super.func_184645_a(player, hand);
            }
            this.func_110237_h(player);
            return true;
        }
        else {
            if (itemstack.func_77973_b() == Items.field_151122_aG && (HorseConfig.getBookShowsGenes() || HorseConfig.getBookShowsTraits()) && (this.func_110248_bS() || player.field_71075_bZ.field_75098_d)) {
                final ItemStack book = new ItemStack((Item)ModItems.geneBookItem);
                if (book.func_77978_p() == null) {
                    book.func_77982_d(new NBTTagCompound());
                }
                book.func_77978_p().func_74778_a("species", this.getSpecies().name());
                book.func_77978_p().func_74778_a("genes", this.getGenes().genesToString());
                if (this.func_145818_k_()) {
                    book.func_151001_c(this.func_95999_t());
                }
                if (!player.func_191521_c(book)) {
                    this.func_70099_a(book, 0.0f);
                }
                if (!player.field_71075_bZ.field_75098_d) {
                    itemstack.func_190918_g(1);
                }
                return true;
            }
            if (this.func_190678_b(player, itemstack)) {
                if (!player.field_71075_bZ.field_75098_d) {
                    itemstack.func_190918_g(1);
                }
                return true;
            }
            if (itemstack.func_111282_a(player, (EntityLivingBase)this, hand)) {
                return true;
            }
            if (!this.func_110248_bS()) {
                this.func_190687_dF();
                return true;
            }
            if (this.func_70631_g_()) {
                return false;
            }
            if (!this.func_110257_ck() && itemstack.func_77973_b() == Items.field_151141_av) {
                if (HorseConfig.getAutoEquipSaddle()) {
                    if (!this.field_70170_p.field_72995_K) {
                        final ItemStack saddle = itemstack.func_77979_a(1);
                        this.field_110296_bG.func_70299_a(0, saddle);
                    }
                }
                else {
                    this.func_110199_f(player);
                }
                return true;
            }
            if (this.func_190682_f(itemstack) && this.func_190677_dK()) {
                if (HorseConfig.getAutoEquipSaddle() && this.field_110296_bG.func_70301_a(1).func_190926_b()) {
                    if (!this.field_70170_p.field_72995_K) {
                        final ItemStack armor = itemstack.func_77979_a(1);
                        this.field_110296_bG.func_70299_a(1, armor);
                    }
                }
                else {
                    this.func_110199_f(player);
                }
                return true;
            }
            if (!this.func_190695_dh() && itemstack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150486_ae) && this.canEquipChest()) {
                this.func_110207_m(true);
                this.func_190697_dk();
                this.func_110226_cD();
                if (!player.field_71075_bZ.field_75098_d) {
                    itemstack.func_190918_g(1);
                }
            }
            this.func_110237_h(player);
            return true;
        }
    }
    
    protected void useGeneticAttributes() {
        if (HorseConfig.getUseGeneticStats()) {
            final HorseGenome genes = this.getGenes();
            final float maxHealth = this.getGenes().getHealth();
            final float speedStat = genes.getStatValue("speed1") + genes.getStatValue("speed2") + genes.getStatValue("speed3") + genes.getStatValue("athletics1") / 2.0f + genes.getStatValue("athletics2") / 2.0f;
            final double movementSpeed = 0.1125 + speedStat * 0.00703125;
            final float jumpStat = genes.getStatValue("jump1") + genes.getStatValue("jump2") + genes.getStatValue("jump3") + genes.getStatValue("athletics1") / 2.0f + genes.getStatValue("athletics2") / 2.0f;
            final double jumpStrength = 0.4 + jumpStat * 0.01875;
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)maxHealth);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(movementSpeed);
            this.func_110148_a(AbstractHorseGenetic.field_110271_bv).func_111128_a(jumpStrength);
        }
        else {
            final float maxHealth2 = this.func_110267_cL() + this.getGenes().getBaseHealth();
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)maxHealth2);
        }
    }
    
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.genes = new HorseGenome(this.getSpecies(), this);
        final float maxHealth = this.func_110267_cL() + this.getGenes().getBaseHealth();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)maxHealth);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110203_cN());
        this.func_110148_a(AbstractHorseGenetic.field_110271_bv).func_111128_a(this.func_110245_cM());
    }
    
    abstract AbstractHorse getChild(final EntityAgeable p0);
    
    public boolean isOppositeGender(final AbstractHorseGenetic other) {
        return !HorseConfig.isGenderEnabled() || (!this.isCastrated() && !other.isCastrated() && this.isMale() != other.isMale());
    }
    
    public EntityAgeable func_90011_a(final EntityAgeable ageable) {
        if (!(ageable instanceof EntityAnimal)) {
            return null;
        }
        final EntityAnimal otherAnimal = (EntityAnimal)ageable;
        if (this.isMale() && ageable instanceof AbstractHorseGenetic && !((AbstractHorseGenetic)ageable).isMale()) {
            return ageable.func_90011_a((EntityAgeable)this);
        }
        final AbstractHorse child = this.getChild(ageable);
        if (child != null) {
            this.func_190681_a(ageable, child);
        }
        if (child instanceof AbstractHorseGenetic) {
            final AbstractHorseGenetic foal = (AbstractHorseGenetic)child;
            if (ageable instanceof AbstractHorseGenetic) {
                final AbstractHorseGenetic other = (AbstractHorseGenetic)ageable;
                foal.getGenes().inheritGenes(this.getGenes(), other.getGenes());
            }
            if (foal.getGenes().isEmbryonicLethal()) {
                this.func_70875_t();
                otherAnimal.func_70875_t();
                this.field_70170_p.func_72960_a((Entity)this, (byte)6);
                return null;
            }
            foal.setMale(this.field_70146_Z.nextBoolean());
            foal.useGeneticAttributes();
            foal.func_70873_a(HorseConfig.getMinAge());
        }
        return (EntityAgeable)child;
    }
    
    public boolean setPregnantWith(final EntityAgeable child, final EntityAgeable otherParent) {
        if (otherParent instanceof IGeneticEntity) {
            final IGeneticEntity otherGenetic = (IGeneticEntity)otherParent;
            if (this.isMale() == otherGenetic.isMale()) {
                return false;
            }
            if (this.isMale() && !otherGenetic.isMale()) {
                return otherGenetic.setPregnantWith(child, (EntityAgeable)this);
            }
        }
        if (this.isMale()) {
            return false;
        }
        if (child instanceof AbstractHorseGenetic) {
            this.unbornChildren.add((AbstractHorseGenetic)child);
            if (!this.field_70170_p.field_72995_K) {
                this.trueAge = Math.max(0, this.trueAge);
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)this.trueAge);
            }
            return true;
        }
        return false;
    }
    
    public boolean shouldRecordAge() {
        return this.getGenes().clientNeedsAge() || this.isPregnant();
    }
    
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.field_70180_af.func_187223_a()) {
            this.field_70180_af.func_187230_e();
            this.getGenes().resetTexture();
        }
        if (!this.field_70170_p.field_72995_K && this.shouldRecordAge()) {
            if (this.field_175504_a < 0) {
                this.trueAge = this.field_175504_a;
            }
            else {
                this.trueAge = Math.max(0, this.trueAge + 1);
            }
            final int c = 400;
            if (this.trueAge / 400 != this.getDisplayAge() / 400 || this.trueAge < 0 != this.getDisplayAge() < 0) {
                this.setDisplayAge(this.trueAge);
            }
        }
        if (!this.field_70170_p.field_72995_K && this.isPregnant()) {
            if (this.unbornChildren == null || this.unbornChildren.size() == 0) {
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)(-1));
            }
            final int totalLength = HorseConfig.getHorsePregnancyLength();
            final int currentLength = this.trueAge - this.getPregnancyStart();
            if (currentLength >= totalLength) {
                for (final AbstractHorseGenetic child : this.unbornChildren) {
                    GenderedBreedGoal.spawnChild((EntityAgeable)this, (EntityAgeable)child, this.field_70170_p);
                }
                this.unbornChildren = new ArrayList<AbstractHorseGenetic>();
                this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)(-1));
            }
        }
        if (this.field_70170_p.field_72995_K) {}
        if (this.getGenes().isLethalWhite() && this.field_70173_aa > 80) {
            if (!this.func_70644_a(MobEffects.field_76436_u)) {
                this.func_70690_d(new PotionEffect(MobEffects.field_76436_u, 100, 3));
            }
            if (this.func_110143_aJ() < 2.0f) {
                this.func_70690_d(new PotionEffect(MobEffects.field_76433_i, 1, 3));
            }
        }
    }
    
    public void func_70636_d() {
        if (this.unbornChildren != null && this.unbornChildren.size() > 0 && this.getPregnancyStart() < 0) {
            this.field_70180_af.func_187227_b((DataParameter)AbstractHorseGenetic.PREGNANT_SINCE, (Object)0);
        }
        if (this.getGenes().isHomozygous("leopard", 1) && !this.field_70170_p.field_72995_K) {
            final IAttributeInstance speedAttribute = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            final IAttributeInstance jumpAttribute = this.func_110148_a(AbstractHorseGenetic.field_110271_bv);
            final float brightness = this.func_70013_c();
            if (brightness > 0.5f) {
                if (speedAttribute.func_111127_a(AbstractHorseGenetic.CSNB_SPEED_UUID) != null) {
                    speedAttribute.func_111124_b(AbstractHorseGenetic.CSNB_SPEED_MODIFIER);
                }
                if (jumpAttribute.func_111127_a(AbstractHorseGenetic.CSNB_JUMP_UUID) != null) {
                    jumpAttribute.func_111124_b(AbstractHorseGenetic.CSNB_JUMP_MODIFIER);
                }
            }
            else {
                if (speedAttribute.func_111127_a(AbstractHorseGenetic.CSNB_SPEED_UUID) == null) {
                    speedAttribute.func_111121_a(AbstractHorseGenetic.CSNB_SPEED_MODIFIER);
                }
                if (jumpAttribute.func_111127_a(AbstractHorseGenetic.CSNB_JUMP_UUID) == null) {
                    jumpAttribute.func_111121_a(AbstractHorseGenetic.CSNB_JUMP_MODIFIER);
                }
            }
        }
        super.func_70636_d();
    }
    
    public Breed getDefaultBreed() {
        return BaseEquine.breed;
    }
    
    @Nullable
    public IEntityLivingData func_180482_a(final DifficultyInstance difficulty, @Nullable IEntityLivingData spawnDataIn) {
        spawnDataIn = super.func_180482_a(difficulty, spawnDataIn);
        this.randomize();
        return spawnDataIn;
    }
    
    private void randomize() {
        this.getGenes().randomize(this.getDefaultBreed());
        this.trueAge = this.field_70146_Z.nextInt(HorseConfig.GROWTH.getMaxAge());
        if (this.field_70146_Z.nextInt(5) == 0) {
            this.trueAge = this.getBirthAge() + this.field_70146_Z.nextInt(-this.getBirthAge() / 2);
        }
        this.setMale(this.field_70146_Z.nextBoolean());
        this.func_70873_a(Math.min(0, this.trueAge));
        this.useGeneticAttributes();
    }
    
    public void initFromVillageSpawn() {
        this.randomize();
        this.func_110198_t(this.func_190676_dC() / 2);
        if (!this.func_70631_g_() && this.field_70146_Z.nextInt(16) == 0) {
            this.func_110234_j(true);
            final ItemStack saddle = new ItemStack(Items.field_151141_av);
            this.field_110296_bG.func_70299_a(0, saddle);
        }
    }
    
    public float fractionGrown() {
        if (!this.func_70631_g_()) {
            return 1.0f;
        }
        if (HorseConfig.getGrowsGradually()) {
            final int minAge = HorseConfig.getMinAge();
            int age = Math.min(0, this.getDisplayAge());
            if (this.getDisplayAge() == 0) {
                age = minAge;
            }
            final float fractionGrown = (minAge - age) / (float)minAge;
            return Math.max(0.0f, fractionGrown);
        }
        return 0.0f;
    }
    
    public float getProportionalScale() {
        final float ageScale = 0.5f + 0.5f * this.fractionGrown();
        return ageScale / this.getGangliness();
    }
    
    public float getGangliness() {
        return 0.5f + 0.5f * this.fractionGrown() * this.fractionGrown();
    }
    
    static {
        HORSE_VARIANT = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_VARIANT2 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_VARIANT3 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_VARIANT4 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_VARIANT5 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_SPEED = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_JUMP = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_HEALTH = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_MHC1 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_MHC2 = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_IMMUNE = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        HORSE_RANDOM = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        DISPLAY_AGE = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        GENDER = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187198_h);
        IS_CASTRATED = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187198_h);
        PREGNANT_SINCE = EntityDataManager.func_187226_a((Class)AbstractHorseGenetic.class, DataSerializers.field_187192_b);
        CSNB_SPEED_UUID = UUID.fromString("84ca527a-5c70-4336-a737-ae3f6d40ef45");
        CSNB_JUMP_UUID = UUID.fromString("72323326-888b-4e46-bf52-f669600642f7");
        CSNB_SPEED_MODIFIER = new AttributeModifier(AbstractHorseGenetic.CSNB_SPEED_UUID, "CSNB speed penalty", -0.6, 2).func_111168_a(false);
        CSNB_JUMP_MODIFIER = new AttributeModifier(AbstractHorseGenetic.CSNB_JUMP_UUID, "CSNB jump penalty", -0.6, 2).func_111168_a(false);
    }
}
