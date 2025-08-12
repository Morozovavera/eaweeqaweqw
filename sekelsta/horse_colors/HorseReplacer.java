package sekelsta.horse_colors;

import net.minecraftforge.event.entity.*;
import sekelsta.horse_colors.config.*;
import sekelsta.horse_colors.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HorseReplacer
{
    public static void preInit() {
    }
    
    public static void init() {
    }
    
    @SubscribeEvent
    public static void replaceHorses(final EntityJoinWorldEvent event) {
        if (event.getEntity().getClass() == EntityHorse.class && !event.getWorld().field_72995_K && HorseConfig.convertVanillaHorses()) {
            final EntityHorse horse = (EntityHorse)event.getEntity();
            if (!horse.getEntityData().func_74764_b("converted")) {
                final HorseGeneticEntity newHorse = new HorseGeneticEntity(event.getWorld());
                newHorse.copyAbstractHorse((AbstractHorse)horse);
                event.getWorld().func_72838_d((Entity)newHorse);
                horse.getEntityData().func_74757_a("converted", true);
            }
            event.setCanceled(true);
        }
    }
}
