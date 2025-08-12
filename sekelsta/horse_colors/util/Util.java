package sekelsta.horse_colors.util;

import net.minecraft.entity.passive.*;
import net.minecraft.util.text.*;

public class Util
{
    public static boolean horseCanMate(final AbstractHorse horse) {
        return !horse.func_184207_aI() && !horse.func_184218_aH() && horse.func_110248_bS() && !horse.func_70631_g_() && horse.func_110143_aJ() >= horse.func_110138_aP() && horse.func_70880_s();
    }
    
    public static String translate(final String in) {
        return new TextComponentTranslation("horse_colors." + in, new Object[0]).func_150254_d();
    }
}
