package sekelsta.horse_colors.entity.ai;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import sekelsta.horse_colors.genetics.*;
import sekelsta.horse_colors.config.*;
import net.minecraft.stats.*;
import net.minecraft.advancements.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;

public class GenderedBreedGoal extends EntityAIBase
{
    private final EntityAnimal animal;
    private final Class<? extends EntityAnimal> mateClass;
    World world;
    private EntityAnimal targetMate;
    int spawnBabyDelay;
    double moveSpeed;
    
    public GenderedBreedGoal(final EntityAnimal animal, final double speedIn) {
        this(animal, speedIn, animal.getClass());
    }
    
    public GenderedBreedGoal(final EntityAnimal animal, final double moveSpeed, final Class<? extends EntityAnimal> clazz) {
        this.animal = animal;
        this.world = animal.field_70170_p;
        this.mateClass = clazz;
        this.moveSpeed = moveSpeed;
        this.func_75248_a(3);
    }
    
    public boolean func_75250_a() {
        if (!this.animal.func_70880_s()) {
            return false;
        }
        this.targetMate = this.getNearbyMate();
        return this.targetMate != null;
    }
    
    public boolean func_75253_b() {
        return this.targetMate.func_70089_S() && this.targetMate.func_70880_s() && this.spawnBabyDelay < 60;
    }
    
    public void func_75251_c() {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }
    
    public void func_75246_d() {
        this.animal.func_70671_ap().func_75651_a((Entity)this.targetMate, 10.0f, (float)this.animal.func_70646_bf());
        this.animal.func_70661_as().func_75497_a((Entity)this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;
        if (this.spawnBabyDelay >= 60 && this.animal.func_70068_e((Entity)this.targetMate) < 9.0) {
            this.spawnBaby();
        }
    }
    
    private EntityAnimal getNearbyMate() {
        final List<EntityAnimal> list = (List<EntityAnimal>)this.world.func_72872_a((Class)this.mateClass, this.animal.func_174813_aQ().func_186662_g(8.0));
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;
        for (final EntityAnimal entityanimal2 : list) {
            if (this.animal.func_70878_b(entityanimal2) && this.animal.func_70068_e((Entity)entityanimal2) < d0) {
                entityanimal = entityanimal2;
                d0 = this.animal.func_70068_e((Entity)entityanimal2);
            }
        }
        return entityanimal;
    }
    
    protected void spawnBaby() {
        EntityAgeable child = this.animal.func_90011_a((EntityAgeable)this.targetMate);
        if (child == null) {
            return;
        }
        final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent((EntityLiving)this.animal, (EntityLiving)this.targetMate, child);
        final boolean cancelled = MinecraftForge.EVENT_BUS.post((Event)event);
        child = event.getChild();
        if (child == null) {
            return;
        }
        int animalRebreedTicks = 6000;
        int mateRebreedTicks = 6000;
        if (this.animal instanceof IGeneticEntity && this.targetMate instanceof IGeneticEntity) {
            final IGeneticEntity geneticAnimal = (IGeneticEntity)this.animal;
            final IGeneticEntity geneticMate = (IGeneticEntity)this.targetMate;
            animalRebreedTicks = geneticAnimal.getRebreedTicks();
            mateRebreedTicks = geneticMate.getRebreedTicks();
            if (!cancelled && HorseConfig.isPregnancyEnabled() && geneticAnimal.setPregnantWith(child, (EntityAgeable)this.targetMate)) {
                child = null;
                this.world.func_72960_a((Entity)this.animal, (byte)18);
            }
        }
        if (cancelled) {
            this.animal.func_70873_a(animalRebreedTicks);
            this.targetMate.func_70873_a(mateRebreedTicks);
            this.animal.func_70875_t();
            this.targetMate.func_70875_t();
            return;
        }
        EntityPlayerMP serverplayerentity = this.animal.func_191993_do();
        if (serverplayerentity == null && this.targetMate.func_191993_do() != null) {
            serverplayerentity = this.targetMate.func_191993_do();
        }
        if (serverplayerentity != null) {
            serverplayerentity.func_71029_a(StatList.field_151186_x);
            CriteriaTriggers.field_192134_n.func_192168_a(serverplayerentity, this.animal, this.targetMate, child);
        }
        this.animal.func_70873_a(animalRebreedTicks);
        this.targetMate.func_70873_a(mateRebreedTicks);
        this.animal.func_70875_t();
        this.targetMate.func_70875_t();
        if (child != null) {
            spawnChild((EntityAgeable)this.animal, child, this.world);
        }
        if (this.world.func_82736_K().func_82766_b("doMobLoot")) {
            this.world.func_72838_d((Entity)new EntityXPOrb(this.world, this.animal.field_70165_t, this.animal.field_70163_u, this.animal.field_70161_v, this.animal.func_70681_au().nextInt(7) + 1));
        }
    }
    
    public static void spawnChild(final EntityAgeable mother, final EntityAgeable child, final World world) {
        if (child instanceof IGeneticEntity) {
            child.func_70873_a(((IGeneticEntity)child).getBirthAge());
        }
        else {
            child.func_70873_a(-24000);
        }
        child.func_70012_b(mother.field_70165_t, mother.field_70163_u, mother.field_70161_v, 0.0f, 0.0f);
        world.func_72838_d((Entity)child);
        world.func_72960_a((Entity)mother, (byte)18);
    }
}
