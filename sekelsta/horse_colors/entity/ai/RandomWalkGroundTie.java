package sekelsta.horse_colors.entity.ai;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.config.*;

public class RandomWalkGroundTie extends EntityAIWanderAvoidWater
{
    public RandomWalkGroundTie(final EntityCreature creature, final double speedIn) {
        super(creature, speedIn);
    }
    
    public RandomWalkGroundTie(final EntityCreature creature, final double speedIn, final float probabilityIn) {
        super(creature, speedIn, probabilityIn);
    }
    
    public boolean func_75250_a() {
        return (!(this.field_75457_a instanceof AbstractHorse) || !HorseConfig.getEnableGroundTie() || !((AbstractHorse)this.field_75457_a).func_110257_ck()) && super.func_75250_a();
    }
}
