package sekelsta.horse_colors.entity;

import java.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.storage.loot.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.util.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import sekelsta.horse_colors.genetics.breed.*;
import sekelsta.horse_colors.genetics.breed.horse.*;
import net.minecraft.network.datasync.*;

public class HorseGeneticEntity extends AbstractHorseGenetic
{
    private static final UUID ARMOR_MODIFIER_UUID;
    private static final DataParameter<Integer> HORSE_ARMOR;
    private static final DataParameter<ItemStack> HORSE_ARMOR_STACK;
    
    public HorseGeneticEntity(final World worldIn) {
        super(worldIn);
    }
    
    @Override
    public void copyAbstractHorse(final AbstractHorse horse) {
        super.copyAbstractHorse(horse);
        final ContainerHorseChest inv = (ContainerHorseChest)ObfuscationReflectionHelper.getPrivateValue((Class)AbstractHorse.class, (Object)horse, new String[] { "horseChest", "field_110296_bG" });
        this.field_110296_bG.func_70299_a(0, inv.func_70301_a(0));
        this.field_110296_bG.func_70299_a(1, inv.func_70301_a(1));
    }
    
    @Override
    public void func_70014_b(final NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (!this.field_110296_bG.func_70301_a(1).func_190926_b()) {
            compound.func_74782_a("ArmorItem", (NBTBase)this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
        }
    }
    
    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a((DataParameter)HorseGeneticEntity.HORSE_ARMOR, (Object)HorseArmorType.NONE.func_188579_a());
        this.field_70180_af.func_187214_a((DataParameter)HorseGeneticEntity.HORSE_ARMOR_STACK, (Object)ItemStack.field_190927_a);
    }
    
    @Override
    public void func_70037_a(final NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("ArmorItem", 10)) {
            final ItemStack itemstack = new ItemStack(compound.func_74775_l("ArmorItem"));
            if (!itemstack.func_190926_b() && this.func_190682_f(itemstack)) {
                this.field_110296_bG.func_70299_a(1, itemstack);
            }
        }
        this.func_110232_cE();
    }
    
    protected void func_110232_cE() {
        super.func_110232_cE();
        this.setHorseArmorStack(this.field_110296_bG.func_70301_a(1));
    }
    
    private void setHorseArmorStack(final ItemStack itemstack) {
        final HorseArmorType horsearmortype = HorseArmorType.func_188580_a(itemstack);
        this.field_70180_af.func_187227_b((DataParameter)HorseGeneticEntity.HORSE_ARMOR, (Object)horsearmortype.func_188579_a());
        this.field_70180_af.func_187227_b((DataParameter)HorseGeneticEntity.HORSE_ARMOR_STACK, (Object)itemstack);
        this.getGenes().resetTexture();
        if (!this.field_70170_p.field_72995_K) {
            this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_188479_b(HorseGeneticEntity.ARMOR_MODIFIER_UUID);
            final int i = horsearmortype.func_188578_c();
            if (i != 0) {
                this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111121_a(new AttributeModifier(HorseGeneticEntity.ARMOR_MODIFIER_UUID, "Horse armor bonus", (double)i, 0).func_111168_a(false));
            }
        }
    }
    
    public ItemStack getHorseArmor() {
        final ItemStack armorStack = (ItemStack)this.field_70180_af.func_187225_a((DataParameter)HorseGeneticEntity.HORSE_ARMOR_STACK);
        return armorStack;
    }
    
    public HorseArmorType getHorseArmorType() {
        HorseArmorType armor = HorseArmorType.func_188580_a((ItemStack)this.field_70180_af.func_187225_a((DataParameter)HorseGeneticEntity.HORSE_ARMOR_STACK));
        if (armor == HorseArmorType.NONE) {
            armor = HorseArmorType.func_188575_a((int)this.field_70180_af.func_187225_a((DataParameter)HorseGeneticEntity.HORSE_ARMOR));
        }
        return armor;
    }
    
    public void func_76316_a(final IInventory invBasic) {
        final HorseArmorType horsearmortype = this.getHorseArmorType();
        super.func_76316_a(invBasic);
        final HorseArmorType horsearmortype2 = this.getHorseArmorType();
        if (this.field_70173_aa > 20 && horsearmortype != horsearmortype2 && horsearmortype2 != HorseArmorType.NONE) {
            this.func_184185_a(SoundEvents.field_187702_cm, 0.5f, 1.0f);
        }
    }
    
    protected void func_190680_a(final SoundType p_190680_1_) {
        super.func_190680_a(p_190680_1_);
        if (this.field_70146_Z.nextInt(10) == 0) {
            this.func_184185_a(SoundEvents.field_187705_cn, p_190680_1_.func_185843_a() * 0.6f, p_190680_1_.func_185847_b());
        }
    }
    
    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        final ItemStack stack = this.field_110296_bG.func_70301_a(1);
        if (this.func_190682_f(stack)) {
            stack.func_77973_b().onHorseArmorTick(this.field_70170_p, (EntityLiving)this, stack);
        }
    }
    
    protected SoundEvent func_184639_G() {
        super.func_184639_G();
        return SoundEvents.field_187696_ck;
    }
    
    protected SoundEvent func_184615_bR() {
        super.func_184615_bR();
        return SoundEvents.field_187708_co;
    }
    
    @Override
    protected SoundEvent func_184601_bQ(final DamageSource damageSourceIn) {
        super.func_184601_bQ(damageSourceIn);
        return SoundEvents.field_187717_cr;
    }
    
    protected SoundEvent func_184785_dv() {
        super.func_184785_dv();
        return SoundEvents.field_187699_cl;
    }
    
    protected ResourceLocation func_184647_J() {
        return LootTableList.field_186396_D;
    }
    
    @Override
    public boolean fluffyTail() {
        return true;
    }
    
    @Override
    public boolean longEars() {
        return false;
    }
    
    @Override
    public boolean thinMane() {
        return false;
    }
    
    @Override
    public boolean canEquipChest() {
        return false;
    }
    
    @Override
    public Species getSpecies() {
        return Species.HORSE;
    }
    
    public boolean func_70878_b(final EntityAnimal otherAnimal) {
        return otherAnimal != this && (!(otherAnimal instanceof AbstractHorseGenetic) || this.isOppositeGender((AbstractHorseGenetic)otherAnimal)) && (otherAnimal instanceof DonkeyGeneticEntity || otherAnimal instanceof HorseGeneticEntity || otherAnimal instanceof EntityDonkey || otherAnimal instanceof EntityHorse) && this.func_110200_cJ() && Util.horseCanMate((AbstractHorse)otherAnimal);
    }
    
    public AbstractHorse getChild(final EntityAgeable ageable) {
        if (ageable instanceof AbstractHorseGenetic) {
            AbstractHorseGenetic child = null;
            final AbstractHorseGenetic other = (AbstractHorseGenetic)ageable;
            if (ageable instanceof HorseGeneticEntity) {
                child = new HorseGeneticEntity(this.field_70170_p);
            }
            else if (ageable instanceof DonkeyGeneticEntity) {
                child = new MuleGeneticEntity(this.field_70170_p);
            }
            return (AbstractHorse)child;
        }
        if (ageable instanceof EntityHorse) {
            final EntityHorse child2 = new EntityHorse(this.field_70170_p);
            child2.func_110235_q(((EntityHorse)ageable).func_110202_bQ());
            return (AbstractHorse)child2;
        }
        if (ageable instanceof EntityDonkey) {
            return (AbstractHorse)new EntityDonkey(this.field_70170_p);
        }
        return null;
    }
    
    public boolean func_190677_dK() {
        return true;
    }
    
    public boolean func_190682_f(final ItemStack stack) {
        return HorseArmorType.isHorseArmor(stack) || (stack.func_77973_b() instanceof ItemBlock && ((ItemBlock)stack.func_77973_b()).func_179223_d() instanceof BlockCarpet);
    }
    
    @Override
    public Breed getDefaultBreed() {
        return DefaultHorse.breed;
    }
    
    static {
        ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
        HORSE_ARMOR = EntityDataManager.func_187226_a((Class)HorseGeneticEntity.class, DataSerializers.field_187192_b);
        HORSE_ARMOR_STACK = EntityDataManager.func_187226_a((Class)HorseGeneticEntity.class, DataSerializers.field_187196_f);
    }
}
