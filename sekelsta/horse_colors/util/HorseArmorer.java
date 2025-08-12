package sekelsta.horse_colors.util;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class HorseArmorer
{
    private static ResourceLocation getVanillaLocation(final EntityLiving wearer, final ItemStack armorStack) {
        if (armorStack == null || armorStack.func_190926_b() || armorStack.func_77973_b() == null) {
            return null;
        }
        return new ResourceLocation(armorStack.func_77973_b().getHorseArmorTexture(wearer, armorStack));
    }
    
    @SideOnly(Side.CLIENT)
    public static ResourceLocation getTexture(final EntityLiving wearer, final ItemStack armorStack) {
        final Item armor = armorStack.func_77973_b();
        if (armor instanceof ItemBlock && ((ItemBlock)armor).func_179223_d() instanceof BlockCarpet) {
            return new ResourceLocation(HorseColorCalculator.fixPath("armor/carpet"));
        }
        final ResourceLocation vanilla = getVanillaLocation(wearer, armorStack);
        if (vanilla == null) {
            return vanilla;
        }
        final String namespace = vanilla.func_110624_b();
        if (namespace == null || namespace.equals("minecraft")) {
            return new ResourceLocation("horse_colors", vanilla.func_110623_a());
        }
        return vanilla;
    }
}
