package sekelsta.horse_colors.entity;

import net.minecraft.world.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import sekelsta.horse_colors.genetics.*;
import sekelsta.horse_colors.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.genetics.breed.*;
import sekelsta.horse_colors.genetics.breed.donkey.*;

public class DonkeyGeneticEntity extends AbstractHorseGenetic
{
    public DonkeyGeneticEntity(final World world) {
        super(world);
    }
    
    protected SoundEvent func_184639_G() {
        super.func_184639_G();
        return SoundEvents.field_187580_av;
    }
    
    protected SoundEvent func_184615_bR() {
        super.func_184615_bR();
        return SoundEvents.field_187586_ay;
    }
    
    @Override
    protected SoundEvent func_184601_bQ(final DamageSource damageSourceIn) {
        super.func_184601_bQ(damageSourceIn);
        return SoundEvents.field_187588_az;
    }
    
    @Override
    public boolean fluffyTail() {
        return false;
    }
    
    @Override
    public boolean longEars() {
        return true;
    }
    
    @Override
    public boolean thinMane() {
        return true;
    }
    
    @Override
    public Species getSpecies() {
        return Species.DONKEY;
    }
    
    public boolean func_70878_b(final EntityAnimal otherAnimal) {
        return otherAnimal != this && (!(otherAnimal instanceof AbstractHorseGenetic) || this.isOppositeGender((AbstractHorseGenetic)otherAnimal)) && (otherAnimal instanceof DonkeyGeneticEntity || otherAnimal instanceof HorseGeneticEntity || otherAnimal instanceof EntityDonkey || otherAnimal instanceof EntityHorse) && this.func_110200_cJ() && Util.horseCanMate((AbstractHorse)otherAnimal);
    }
    
    public AbstractHorse getChild(final EntityAgeable ageable) {
        if (ageable instanceof AbstractHorseGenetic) {
            AbstractHorseGenetic child = null;
            final AbstractHorseGenetic other = (AbstractHorseGenetic)ageable;
            if (ageable instanceof HorseGeneticEntity) {
                child = new MuleGeneticEntity(this.field_70170_p);
            }
            else if (ageable instanceof DonkeyGeneticEntity) {
                child = new DonkeyGeneticEntity(this.field_70170_p);
            }
            return (AbstractHorse)child;
        }
        if (ageable instanceof EntityHorse) {
            return (AbstractHorse)new EntityMule(this.field_70170_p);
        }
        if (ageable instanceof EntityDonkey) {
            return (AbstractHorse)new EntityDonkey(this.field_70170_p);
        }
        return null;
    }
    
    @Override
    public Breed getDefaultBreed() {
        return DefaultDonkey.breed;
    }
}
