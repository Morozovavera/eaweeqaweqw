package sekelsta.horse_colors.item;

import net.minecraft.nbt.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import sekelsta.horse_colors.entity.*;
import net.minecraft.util.text.translation.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import sekelsta.horse_colors.client.*;
import net.minecraft.client.gui.*;
import sekelsta.horse_colors.genetics.*;

public class GeneBookItem extends Item
{
    public GeneBookItem() {
        this.func_77625_d(1);
    }
    
    public static boolean validBookTagContents(final NBTTagCompound nbt) {
        if (nbt == null) {
            return false;
        }
        if (!nbt.func_150297_b("species", 8)) {
            return false;
        }
        if (!nbt.func_150297_b("genes", 8)) {
            return false;
        }
        try {
            Species.valueOf(nbt.func_74779_i("species"));
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    public static Species getSpecies(final NBTTagCompound compoundnbt) {
        final String s = compoundnbt.func_74779_i("species");
        if (!StringUtils.func_151246_b(s)) {
            return Species.valueOf(s);
        }
        return null;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack stack, @Nullable final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
        if (stack.func_77942_o()) {
            final NBTTagCompound compoundnbt = stack.func_77978_p();
            final String s = compoundnbt.func_74779_i("species");
            final Species species = getSpecies(compoundnbt);
            if (species != null) {
                String translation = null;
                switch (species) {
                    case HORSE: {
                        translation = ModEntities.HORSE_GENETIC.getName();
                        break;
                    }
                    case DONKEY: {
                        translation = ModEntities.DONKEY_GENETIC.getName();
                        break;
                    }
                    case MULE: {
                        translation = ModEntities.MULE_GENETIC.getName();
                        break;
                    }
                }
                if (translation != null) {
                    tooltip.add(I18n.func_74838_a("entity." + translation + ".name"));
                }
            }
        }
    }
    
    public ActionResult<ItemStack> func_77659_a(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
        final ItemStack itemstack = playerIn.func_184586_b(handIn);
        if (validBookTagContents(itemstack.func_77978_p())) {
            if (worldIn.field_72995_K) {
                this.openGeneBook(itemstack.func_77978_p());
            }
            return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)itemstack);
        }
        System.out.println("Gene book has invalid NBT");
        return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.FAIL, (Object)itemstack);
    }
    
    @SideOnly(Side.CLIENT)
    public void openGeneBook(final NBTTagCompound nbt) {
        final Minecraft mc = Minecraft.func_71410_x();
        final Genome genome = new HorseGenome(getSpecies(nbt));
        genome.genesFromString(nbt.func_74779_i("genes"));
        mc.func_147108_a((GuiScreen)new GeneBookScreen(genome));
    }
}
