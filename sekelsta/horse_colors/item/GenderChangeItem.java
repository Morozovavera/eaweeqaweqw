package sekelsta.horse_colors.item;

import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.client.util.*;
import sekelsta.horse_colors.config.*;
import net.minecraft.util.text.translation.*;
import net.minecraftforge.fml.relauncher.*;

public class GenderChangeItem extends Item
{
    public GenderChangeItem() {
        this.func_77625_d(64);
    }
    
    public boolean func_111207_a(final ItemStack stack, final EntityPlayer player, final EntityLivingBase target, final EnumHand hand) {
        if (target instanceof IGeneticEntity) {
            final IGeneticEntity g = (IGeneticEntity)target;
            g.setMale(!g.isMale());
            if (player != null) {
                target.field_70170_p.func_184148_a((EntityPlayer)null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187827_fP, SoundCategory.PLAYERS, 0.5f, 0.4f / (GenderChangeItem.field_77697_d.nextFloat() * 0.4f + 0.8f));
            }
            if (player == null || !player.field_71075_bZ.field_75098_d) {
                stack.func_190918_g(1);
            }
            return true;
        }
        return false;
    }
    
    public boolean func_77636_d(final ItemStack stack) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack stack, @Nullable final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
        if (!HorseConfig.isGenderEnabled()) {
            final String translation = "horse_colors.gender_change_item.gender_disabled_warning";
            tooltip.add(I18n.func_74838_a(translation));
        }
    }
}
