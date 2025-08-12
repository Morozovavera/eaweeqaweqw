package sekelsta.horse_colors.entity;

import net.minecraft.entity.passive.*;
import net.minecraft.world.storage.loot.*;
import javax.annotation.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import sekelsta.horse_colors.genetics.breed.horse.*;
import sekelsta.horse_colors.genetics.breed.donkey.*;
import sekelsta.horse_colors.genetics.*;

public class MuleGeneticEntity extends AbstractHorseGenetic
{
    public MuleGeneticEntity(final World world) {
        super(world);
    }
    
    @Override
    public boolean fluffyTail() {
        return true;
    }
    
    @Override
    public boolean longEars() {
        return true;
    }
    
    @Override
    public boolean thinMane() {
        return false;
    }
    
    @Override
    public Species getSpecies() {
        return Species.MULE;
    }
    
    protected boolean func_110200_cJ() {
        return false;
    }
    
    public AbstractHorse getChild(final EntityAgeable ageable) {
        final MuleGeneticEntity child = new MuleGeneticEntity(this.field_70170_p);
        return (AbstractHorse)child;
    }
    
    @Nullable
    protected ResourceLocation func_184647_J() {
        return LootTableList.field_191191_I;
    }
    
    protected SoundEvent func_184639_G() {
        super.func_184639_G();
        return SoundEvents.field_187786_du;
    }
    
    protected SoundEvent func_184615_bR() {
        super.func_184615_bR();
        return SoundEvents.field_187788_dv;
    }
    
    @Override
    protected SoundEvent func_184601_bQ(final DamageSource damageSourceIn) {
        super.func_184601_bQ(damageSourceIn);
        return SoundEvents.field_187790_dw;
    }
    
    protected void func_190697_dk() {
        this.func_184185_a(SoundEvents.field_191259_dX, 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
    }
    
    @Nullable
    @Override
    public IEntityLivingData func_180482_a(final DifficultyInstance difficulty, @Nullable IEntityLivingData spawnDataIn) {
        spawnDataIn = super.func_180482_a(difficulty, spawnDataIn);
        final HorseGenome horse = new HorseGenome(Species.HORSE);
        horse.randomize(DefaultHorse.breed);
        final HorseGenome donkey = new HorseGenome(Species.DONKEY);
        donkey.randomize(DefaultDonkey.breed);
        this.genes.inheritGenes(horse, donkey);
        this.useGeneticAttributes();
        return spawnDataIn;
    }
}
