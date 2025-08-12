package sekelsta.horse_colors.renderer;

import java.awt.image.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;
import java.io.*;
import net.minecraft.client.resources.*;
import org.apache.logging.log4j.*;

public class TextureLayer
{
    private static final Logger LOGGER;
    public String name;
    public String description;
    public Type type;
    public int alpha;
    public int red;
    public int green;
    public int blue;
    public TextureLayer next;
    
    public TextureLayer() {
        this.name = null;
        this.description = null;
        this.type = Type.NORMAL;
        this.alpha = 255;
        this.red = 255;
        this.green = 255;
        this.blue = 255;
    }
    
    public BufferedImage getLayer(final IResourceManager manager) {
        if (this.name == null) {
            TextureLayer.LOGGER.error("Attempting to load unspecified texture (name is null)\n");
            return null;
        }
        try (final IResource iresource = manager.func_110536_a(new ResourceLocation(this.name))) {
            final BufferedImage image = TextureUtil.func_177053_a(iresource.func_110527_b());
            if (this.next != null) {
                this.colorLayer(image);
                this.next.combineLayers(image, this.next.getLayer(manager));
                this.red = 255;
                this.green = 255;
                this.blue = 255;
            }
            return image;
        }
        catch (IOException ioexception) {
            TextureLayer.LOGGER.error("Couldn't load layered image", (Throwable)ioexception);
            return null;
        }
    }
    
    public void combineLayers(final BufferedImage base, final BufferedImage image) {
        switch (this.type) {
            case NORMAL: {
                this.blendLayer(base, image);
                break;
            }
            case NO_ALPHA: {
                this.blendLayerKeepAlpha(base, image);
                break;
            }
            case MASK: {
                this.maskLayer(base, image);
                break;
            }
            case SHADE: {
                this.shadeLayer(base, image);
                break;
            }
            case HIGHLIGHT: {
                this.highlightLayer(base, image);
                break;
            }
            case POWER: {
                this.powerLayer(base, image);
                break;
            }
            case ROOT: {
                this.rootLayer(base, image);
                break;
            }
        }
    }
    
    public void blendLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                blendPixel(base, j, i, this.multiply(image.getRGB(j, i)));
            }
        }
    }
    
    public void blendLayerKeepAlpha(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int cb = base.getRGB(j, i);
                final int ci = this.multiply(image.getRGB(j, i));
                final float a = getAlpha(ci) / 255.0f;
                final float r = (float)getRed(ci);
                final float g = (float)getGreen(ci);
                final float b = (float)getBlue(ci);
                final float br = (float)getRed(cb);
                final float bg = (float)getGreen(cb);
                final float bb = (float)getBlue(cb);
                final int fa = getAlpha(cb);
                final int fr = (int)(r * a + br * (1.0f - a));
                final int fg = (int)(g * a + bg * (1.0f - a));
                final int fb = (int)(b * a + bb * (1.0f - a));
                base.setRGB(j, i, getCombined(fa, fb, fg, fr));
            }
        }
    }
    
    public void shadeLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = base.getRGB(j, i);
                final int shading = this.multiply(image.getRGB(j, i));
                base.setRGB(j, i, this.shade(color, shading));
            }
        }
    }
    
    public void highlightLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = base.getRGB(j, i);
                final int highlight = this.multiply(image.getRGB(j, i));
                base.setRGB(j, i, this.highlight(color, highlight));
            }
        }
    }
    
    public void maskLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = base.getRGB(j, i);
                final int mask = image.getRGB(j, i);
                final int maskedColor = this.mask(color, mask);
                base.setRGB(j, i, maskedColor);
            }
        }
    }
    
    public void powerLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = base.getRGB(j, i);
                int exp = image.getRGB(j, i);
                exp = this.multiply(exp);
                blendPixel(base, j, i, this.power(color, exp));
            }
        }
    }
    
    public void rootLayer(final BufferedImage base, final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = base.getRGB(j, i);
                int exp = image.getRGB(j, i);
                exp = this.multiply(exp);
                blendPixel(base, j, i, this.root(color, exp));
            }
        }
    }
    
    public void colorLayer(final BufferedImage image) {
        for (int i = 0; i < image.getHeight(); ++i) {
            for (int j = 0; j < image.getWidth(); ++j) {
                final int color = image.getRGB(j, i);
                image.setRGB(j, i, this.multiply(color));
            }
        }
    }
    
    public int multiply(final int color) {
        int a = getAlpha(color);
        a = (int)(a * (float)this.alpha / 255.0f);
        int r = getRed(color);
        r = (int)(r * (float)this.red / 255.0f);
        int g = getGreen(color);
        g = (int)(g * (float)this.green / 255.0f);
        int b = getBlue(color);
        b = (int)(b * (float)this.blue / 255.0f);
        return getCombined(a, b, g, r);
    }
    
    public int shade(final int color, final int shading) {
        final float cr = (float)getRed(color);
        final float cg = (float)getGreen(color);
        final float cb = (float)getBlue(color);
        final float sr = (float)getRed(shading);
        final float sg = (float)getGreen(shading);
        final float sb = (float)getBlue(shading);
        float a = getAlpha(shading) / 255.0f;
        final float avg = (cr + cg + cb) / 255.0f / 3.0f;
        a *= 0.5f + 0.5f * (1.0f - avg) * (1.0f - avg);
        final float na = 1.0f - a;
        final float r = Math.max(0.0f, Math.min(255.0f, sr * a + cr * na));
        final float g = Math.max(0.0f, Math.min(255.0f, sg * a + cg * na));
        final float b = Math.max(0.0f, Math.min(255.0f, sb * a + cb * na));
        final int ca = getAlpha(color);
        return getCombined(ca, (int)b, (int)g, (int)r);
    }
    
    public int highlight(final int color, final int light) {
        final float r0 = (float)getRed(color);
        final float g0 = (float)getGreen(color);
        final float b0 = (float)getBlue(color);
        final float r2 = (float)getRed(light);
        final float g2 = (float)getGreen(light);
        final float b2 = (float)getBlue(light);
        float a = getAlpha(light) / 255.0f;
        final float avg = (r0 + g0 + b0) / 255.0f / 3.0f;
        a *= 0.5f + 0.5f * avg * avg;
        final float na = 1.0f - a;
        final float r3 = Math.max(0.0f, Math.min(255.0f, r2 * a + r0 * na));
        final float g3 = Math.max(0.0f, Math.min(255.0f, g2 * a + g0 * na));
        final float b3 = Math.max(0.0f, Math.min(255.0f, b2 * a + b0 * na));
        final int ca = getAlpha(color);
        return getCombined(ca, (int)b3, (int)g3, (int)r3);
    }
    
    public int power(final int color, final int exp) {
        final float r0 = getRed(color) / 255.0f;
        final float g0 = getGreen(color) / 255.0f;
        final float b0 = getBlue(color) / 255.0f;
        final float r2 = Math.max(0.002f, getRed(exp) / 255.0f);
        final float g2 = Math.max(0.002f, getGreen(exp) / 255.0f);
        final float b2 = Math.max(0.002f, getBlue(exp) / 255.0f);
        final int r3 = this.clamp((int)(255.0 * Math.pow(r0, 1.0f / r2)));
        final int g3 = this.clamp((int)(255.0 * Math.pow(g0, 1.0f / g2)));
        final int b3 = this.clamp((int)(255.0 * Math.pow(b0, 1.0f / b2)));
        final int a = getAlpha(exp);
        return getCombined(a, b3, g3, r3);
    }
    
    public int root(final int color, final int exp) {
        final float r0 = getRed(color) / 255.0f;
        final float g0 = getGreen(color) / 255.0f;
        final float b0 = getBlue(color) / 255.0f;
        final float r2 = getRed(exp) / 255.0f;
        final float g2 = getGreen(exp) / 255.0f;
        final float b2 = getBlue(exp) / 255.0f;
        final int r3 = this.clamp((int)(255.0 * Math.pow(r0, r2)));
        final int g3 = this.clamp((int)(255.0 * Math.pow(g0, g2)));
        final int b3 = this.clamp((int)(255.0 * Math.pow(b0, b2)));
        final int a = getAlpha(exp);
        return getCombined(a, b3, g3, r3);
    }
    
    public int mask(final int color, final int mask) {
        float a = (float)(getAlpha(color) * getAlpha(mask));
        a /= 255.0f;
        final float weight = this.alpha / 255.0f;
        a = a * weight + getAlpha(color) * (1.0f - weight);
        final int r = getRed(color);
        final int g = getGreen(color);
        final int b = getBlue(color);
        return getCombined((int)a, b, g, r);
    }
    
    public void clamp() {
        this.red = this.clamp(this.red);
        this.green = this.clamp(this.green);
        this.blue = this.clamp(this.blue);
    }
    
    private int clamp(final int x) {
        return Math.max(0, Math.min(x, 255));
    }
    
    private String getAbv(String s) {
        final int i = s.lastIndexOf("/");
        if (i > -1) {
            s = s.substring(i + 1);
        }
        if (s.endsWith(".png")) {
            s = s.substring(0, s.length() - 4);
        }
        return s;
    }
    
    @Override
    public String toString() {
        if (this.name == null) {
            return "";
        }
        String s = this.getAbv(this.name);
        s = s + "-" + this.type.toString();
        s = s + "-" + Integer.toHexString(this.alpha);
        s += Integer.toHexString(this.red);
        s += Integer.toHexString(this.green);
        s += Integer.toHexString(this.blue);
        return s;
    }
    
    public static int getAlpha(final int col) {
        return col >> 24 & 0xFF;
    }
    
    public static int getRed(final int col) {
        return col >> 16 & 0xFF;
    }
    
    public static int getGreen(final int col) {
        return col >> 8 & 0xFF;
    }
    
    public static int getBlue(final int col) {
        return col >> 0 & 0xFF;
    }
    
    public static int getCombined(final int alpha, final int blue, final int green, final int red) {
        return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF) << 0;
    }
    
    public static void blendPixel(final BufferedImage image, final int xIn, final int yIn, final int colIn) {
        final int i = image.getRGB(xIn, yIn);
        final float f = getAlpha(colIn) / 255.0f;
        final float f2 = getBlue(colIn) / 255.0f;
        final float f3 = getGreen(colIn) / 255.0f;
        final float f4 = getRed(colIn) / 255.0f;
        final float f5 = getAlpha(i) / 255.0f;
        final float f6 = getBlue(i) / 255.0f;
        final float f7 = getGreen(i) / 255.0f;
        final float f8 = getRed(i) / 255.0f;
        final float f9 = 1.0f - f;
        float f10 = f * f + f5 * f9;
        float f11 = f2 * f + f6 * f9;
        float f12 = f3 * f + f7 * f9;
        float f13 = f4 * f + f8 * f9;
        if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        if (f11 > 1.0f) {
            f11 = 1.0f;
        }
        if (f12 > 1.0f) {
            f12 = 1.0f;
        }
        if (f13 > 1.0f) {
            f13 = 1.0f;
        }
        final int j = (int)(f10 * 255.0f);
        final int k = (int)(f11 * 255.0f);
        final int l = (int)(f12 * 255.0f);
        final int i2 = (int)(f13 * 255.0f);
        image.setRGB(xIn, yIn, getCombined(j, k, l, i2));
    }
    
    static {
        LOGGER = LogManager.getLogger();
    }
    
    public enum Type
    {
        NORMAL, 
        NO_ALPHA, 
        MASK, 
        SHADE, 
        HIGHLIGHT, 
        POWER, 
        ROOT;
    }
}
