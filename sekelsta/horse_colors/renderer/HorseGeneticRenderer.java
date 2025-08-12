package sekelsta.horse_colors.renderer;

import sekelsta.horse_colors.entity.*;
import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.layers.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.*;
import com.google.common.collect.*;

@SideOnly(Side.CLIENT)
public class HorseGeneticRenderer extends RenderLiving<AbstractHorseGenetic>
{
    private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE;
    
    protected void preRenderCallback(final AbstractHorseGenetic horse, final float partialTickTime) {
        final float scale = horse.getProportionalScale();
        GlStateManager.func_179152_a(scale, scale, scale);
        super.func_77041_b((EntityLivingBase)horse, partialTickTime);
    }
    
    public HorseGeneticRenderer(final RenderManager renderManager) {
        super(renderManager, (ModelBase)new HorseGeneticModel(), 0.75f);
        this.func_177094_a((LayerRenderer)new HorseArmorLayer(this));
    }
    
    public ResourceLocation getEntityTexture(final AbstractHorseGenetic entity) {
        if (entity instanceof IGeneticEntity) {
            final String s = entity.getGenes().getTexture();
            ResourceLocation resourcelocation = HorseGeneticRenderer.LAYERED_LOCATION_CACHE.get(s);
            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s);
                Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, (ITextureObject)new CustomLayeredTexture(entity.getGenes().getVariantTexturePaths()));
                HorseGeneticRenderer.LAYERED_LOCATION_CACHE.put(s, resourcelocation);
            }
            return resourcelocation;
        }
        System.out.println("Trying to render an ineligible entity");
        return null;
    }
    
    static {
        LAYERED_LOCATION_CACHE = Maps.newHashMap();
    }
}
