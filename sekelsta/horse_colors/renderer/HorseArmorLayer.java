package sekelsta.horse_colors.renderer;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraftforge.fml.relauncher.*;
import sekelsta.horse_colors.entity.*;
import sekelsta.horse_colors.util.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

@SideOnly(Side.CLIENT)
public class HorseArmorLayer implements LayerRenderer<AbstractHorseGenetic>
{
    private final HorseGeneticModel horseModel;
    private final HorseGeneticRenderer renderer;
    
    public HorseArmorLayer(final HorseGeneticRenderer renderer) {
        this.horseModel = new HorseGeneticModel(0.1f);
        this.renderer = renderer;
    }
    
    public void doRenderLayer(final AbstractHorseGenetic entityIn, final float limbSwing, final float limbSwingAmount, final float partialTickTime, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (!(entityIn instanceof HorseGeneticEntity)) {
            return;
        }
        final HorseGeneticEntity horse = (HorseGeneticEntity)entityIn;
        final ItemStack itemstack = horse.getHorseArmor();
        final Item armor = itemstack.func_77973_b();
        final ResourceLocation textureLocation = HorseArmorer.getTexture((EntityLiving)horse, itemstack);
        if (textureLocation != null) {
            this.horseModel.func_178686_a(this.renderer.func_177087_b());
            this.horseModel.func_78086_a((EntityLivingBase)entityIn, limbSwing, limbSwingAmount, partialTickTime);
            this.renderer.func_110776_a(textureLocation);
            float r = 1.0f;
            float g = 1.0f;
            float b = 1.0f;
            if (armor instanceof ItemBlock) {
                final ItemBlock blockItem = (ItemBlock)armor;
                if (blockItem.func_179223_d() instanceof BlockCarpet) {
                    final EnumDyeColor dyeColor = EnumDyeColor.func_176764_b(itemstack.func_77960_j());
                    final float[] colors = dyeColor.func_193349_f();
                    b = colors[2];
                    g = colors[1];
                    r = colors[0];
                }
            }
            GlStateManager.func_179131_c(r, g, b, 1.0f);
            this.horseModel.func_78088_a((Entity)entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
    
    public boolean func_177142_b() {
        return false;
    }
}
