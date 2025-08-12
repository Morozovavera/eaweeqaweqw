package sekelsta.horse_colors;

import net.minecraft.creativetab.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

public class CreativeTab extends CreativeTabs
{
    public static final CreativeTab instance;
    
    public CreativeTab(final String label) {
        super(label);
    }
    
    public ItemStack func_78016_d() {
        return new ItemStack(Item.func_150898_a(Blocks.field_150407_cf));
    }
    
    static {
        instance = new CreativeTab("tabHorseColors");
    }
}
