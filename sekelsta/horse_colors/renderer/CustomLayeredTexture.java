package sekelsta.horse_colors.renderer;

import net.minecraftforge.fml.relauncher.*;
import com.google.common.collect.*;
import net.minecraft.client.resources.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import net.minecraft.client.renderer.texture.*;
import org.apache.logging.log4j.*;

@SideOnly(Side.CLIENT)
public class CustomLayeredTexture extends AbstractTexture
{
    private static final Logger LOGGER;
    public final List<TextureLayer> layers;
    
    public CustomLayeredTexture(final List<TextureLayer> textures) {
        this.layers = (List<TextureLayer>)Lists.newArrayList((Iterable)textures);
        if (this.layers.isEmpty()) {
            throw new IllegalStateException("Layered texture with no layers.");
        }
    }
    
    public void func_110551_a(final IResourceManager manager) throws IOException {
        final Iterator<TextureLayer> iterator = this.layers.iterator();
        final TextureLayer baselayer = iterator.next();
        final BufferedImage baseimage = baselayer.getLayer(manager);
        if (baseimage == null) {
            return;
        }
        baselayer.colorLayer(baseimage);
        while (iterator.hasNext()) {
            final TextureLayer layer = iterator.next();
            if (layer == null) {
                continue;
            }
            if (layer.name == null) {
                continue;
            }
            final BufferedImage image = layer.getLayer(manager);
            if (image == null) {
                continue;
            }
            layer.combineLayers(baseimage, image);
        }
        this.loadImage(baseimage);
    }
    
    private void loadImage(final BufferedImage imageIn) {
        TextureUtil.func_110987_a(this.func_110552_b(), imageIn);
    }
    
    static {
        LOGGER = LogManager.getLogger();
    }
}
