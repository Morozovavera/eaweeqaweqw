package sekelsta.horse_colors.genetics;

import sekelsta.horse_colors.util.*;
import sekelsta.horse_colors.renderer.*;
import sekelsta.horse_colors.config.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;
import com.google.common.collect.*;

public class HorseColorCalculator
{
    static final RandomSupplier randSource;
    private static final int GRAY_BODY_STAGES = 19;
    private static final int GRAY_MANE_STAGES = 20;
    
    public static String fixPath(final String inStr) {
        if (inStr == null || inStr.contains(".png")) {
            return inStr;
        }
        if (inStr == "") {
            return null;
        }
        return "horse_colors:textures/entity/horse/" + inStr + ".png";
    }
    
    public static void adjustConcentration(final TextureLayer layer, final float power) {
        final float r = layer.red / 255.0f;
        final float g = layer.green / 255.0f;
        final float b = layer.blue / 255.0f;
        final float red = (float)Math.pow(r, power) * 255.0f;
        final float green = (float)Math.pow(g, power) * 255.0f;
        final float blue = (float)Math.pow(b, power) * 255.0f;
        layer.red = Math.max(0, Math.min(255, (int)red));
        layer.green = Math.max(0, Math.min(255, (int)green));
        layer.blue = Math.max(0, Math.min(255, (int)blue));
    }
    
    public static void addWhite(final TextureLayer layer, final float white) {
        layer.red = (int)(255.0 * white + layer.red * (1.0f - white));
        layer.green = (int)(255.0 * white + layer.green * (1.0f - white));
        layer.blue = (int)(255.0 * white + layer.blue * (1.0f - white));
    }
    
    public static void setPheomelanin(final TextureLayer layer, final float concentration, final float white) {
        layer.red = 228;
        layer.green = 192;
        layer.blue = 119;
        adjustConcentration(layer, concentration);
        addWhite(layer, white);
    }
    
    public static void setEumelanin(final TextureLayer layer, final float concentration, final float white) {
        layer.red = 192;
        layer.green = 154;
        layer.blue = 95;
        adjustConcentration(layer, concentration);
        addWhite(layer, white);
    }
    
    public static void colorRedBody(final HorseGenome horse, final TextureLayer layer) {
        float concentration = 5.0f;
        float white = 0.08f;
        if (horse.isDoubleCream() || horse.isHomozygous("ivory", 1)) {
            concentration *= 0.05f;
            white += 0.4f;
        }
        else if (horse.isCreamPearl()) {
            concentration *= 0.1f;
        }
        else if (horse.hasCream()) {
            concentration *= 0.6f;
            white += 0.15f;
            if (horse.hasAllele("cream", 4)) {
                concentration *= 0.6f;
                white += (float)0.04;
            }
        }
        else if (horse.isPearl()) {
            concentration *= 0.6f;
            white += 0.15f;
            if (horse.hasAllele("cream", 4)) {
                concentration *= 0.9f;
                white += 0.04f;
            }
        }
        else if (horse.isHomozygous("cream", 4)) {
            concentration *= 0.9f;
            white += 0.04f;
        }
        if (horse.hasAllele("cameo", 1)) {
            concentration *= 0.3f;
            white += 0.25f;
        }
        if (horse.hasAllele("rufous", 1)) {
            concentration *= 1.1f;
        }
        if (horse.isHomozygous("dark_red", 1)) {
            concentration *= 1.2f;
        }
        if (horse.isHomozygous("dense", 1)) {
            concentration *= 1.1f;
            white -= 0.03f;
        }
        white = Math.max(white, 0.0f);
        setPheomelanin(layer, concentration, white);
        if (horse.isChestnut() && horse.isHomozygous("liver", 0)) {
            final TextureLayer dark = new TextureLayer();
            setPheomelanin(dark, concentration * 5.0f, white);
            final float a = 0.4f;
            layer.red = (int)(dark.red * a + layer.red * (1.0f - a));
            layer.green = (int)(dark.green * a + layer.green * (1.0f - a));
            layer.blue = (int)(dark.blue * a + layer.blue * (1.0f - a));
            layer.clamp();
        }
    }
    
    public static TextureLayer getRedBody(final HorseGenome horse) {
        final TextureLayer layer = new TextureLayer();
        layer.name = fixPath("base");
        colorRedBody(horse, layer);
        setGrayConcentration(horse, layer);
        return layer;
    }
    
    public static void colorBlackBody(final HorseGenome horse, final TextureLayer layer) {
        float concentration = 20.0f;
        float white = 0.02f;
        if (horse.isDoubleCream() || horse.isHomozygous("ivory", 1)) {
            concentration *= 0.02f;
        }
        else if (horse.isCreamPearl()) {
            concentration *= 0.025f;
        }
        else if (horse.hasCream()) {
            concentration *= 0.5f;
        }
        else if (horse.isPearl()) {
            concentration *= 0.25f;
            white += 0.18f;
        }
        if (horse.hasAllele("cameo", 1)) {
            concentration *= 0.2f;
            white += 0.2f;
        }
        if (horse.hasAllele("silver", 1)) {
            concentration *= 0.4f;
        }
        if (horse.isHomozygous("dense", 1)) {
            concentration *= 1.1f;
            white -= 0.01f;
        }
        white = Math.max(white, 0.0f);
        setEumelanin(layer, concentration, white);
    }
    
    public static TextureLayer getBlackBody(final HorseGenome horse) {
        if (horse.isChestnut()) {
            return null;
        }
        final TextureLayer layer = new TextureLayer();
        layer.description = "black body";
        if (horse.getMaxAllele("agouti") == 0) {
            layer.name = fixPath("base");
        }
        else {
            if (horse.getMaxAllele("agouti") != 1 && horse.getMaxAllele("agouti") != 2) {
                return getSooty(horse);
            }
            layer.name = fixPath("brown");
        }
        colorBlackBody(horse, layer);
        setGrayConcentration(horse, layer);
        return layer;
    }
    
    public static void addRedManeTail(final HorseGenome horse, final List<TextureLayer> layers) {
        final float PALOMINO_POWER = 0.2f;
        if (!horse.isChestnut()) {
            return;
        }
        if (horse.hasAllele("cream", 3)) {
            final TextureLayer palomino_mane = new TextureLayer();
            palomino_mane.description = "palomino mane";
            palomino_mane.name = fixPath("manetail");
            colorRedBody(horse, palomino_mane);
            adjustConcentration(palomino_mane, 0.2f);
            setGrayConcentration(horse, palomino_mane);
            layers.add(palomino_mane);
        }
        if (!horse.isHomozygous("flaxen1", 0) && !horse.isHomozygous("flaxen2", 0)) {
            return;
        }
        final TextureLayer flaxen = new TextureLayer();
        flaxen.name = fixPath("flaxen");
        flaxen.description = "flaxen";
        colorRedBody(horse, flaxen);
        float power = 1.0f;
        if (horse.hasAllele("cream", 3)) {
            power *= 0.2f;
        }
        float white = 0.0f;
        if (horse.isHomozygous("flaxen1", 0)) {
            power *= 0.5f;
            white += 0.2f;
        }
        if (horse.isHomozygous("flaxen2", 0)) {
            power *= 0.8f;
            white += 0.1f;
        }
        if (horse.hasAllele("flaxen_boost", 1)) {
            Math.pow(power, 1.5);
            white *= 1.5;
        }
        adjustConcentration(flaxen, power);
        setGrayConcentration(horse, flaxen);
        addWhite(flaxen, white);
        layers.add(flaxen);
    }
    
    public static TextureLayer getBlackManeTail(final HorseGenome horse) {
        if (horse.isChestnut()) {
            return null;
        }
        if (!horse.hasAllele("silver", 1)) {
            return null;
        }
        final TextureLayer layer = new TextureLayer();
        layer.name = fixPath("flaxen");
        layer.description = "silver dapple mane";
        setEumelanin(layer, 0.3f, 0.0f);
        setGrayConcentration(horse, layer);
        return layer;
    }
    
    public static void colorSkin(final HorseGenome horse, final TextureLayer layer) {
        if (horse.isCreamPearl() || horse.hasAllele("cameo", 1)) {
            setEumelanin(layer, 5.0f, 0.2f);
        }
        else if (!horse.isDoubleCream() && !horse.isHomozygous("ivory", 1)) {
            setEumelanin(layer, 18.0f, 0.1f);
        }
        int old = layer.green;
        layer.green = (int)(layer.green * 214 / 255.0f);
        old = layer.blue;
        layer.blue = (int)(layer.blue * 182 / 255.0f);
    }
    
    public static void colorGray(final HorseGenome horse, final TextureLayer layer) {
        colorSkin(horse, layer);
        addWhite(layer, 0.99f);
    }
    
    public static TextureLayer getNose(final HorseGenome horse) {
        final TextureLayer layer = new TextureLayer();
        layer.name = fixPath("nose");
        colorSkin(horse, layer);
        return layer;
    }
    
    public static TextureLayer getHooves(final HorseGenome horse) {
        final TextureLayer layer = new TextureLayer();
        layer.name = fixPath("hooves");
        colorSkin(horse, layer);
        addWhite(layer, 0.4f);
        layer.red = (int)(layer.red * 255.0f / 255.0f);
        layer.green = (int)(layer.green * 229.0f / 255.0f);
        layer.blue = (int)(layer.blue * 184.0f / 255.0f);
        layer.clamp();
        return layer;
    }
    
    public static void addDun(final HorseGenome horse, final List<TextureLayer> layers) {
        if (!horse.hasStripe()) {
            return;
        }
        final TextureLayer white = new TextureLayer();
        white.name = fixPath("dun");
        white.alpha = 25;
        if (!horse.isDun()) {
            white.alpha *= (int)0.1;
        }
        if (horse.isHomozygous("light_dun", 1)) {
            final TextureLayer textureLayer = white;
            textureLayer.alpha *= 2;
        }
        white.type = TextureLayer.Type.SHADE;
        layers.add(white);
        final TextureLayer layer = new TextureLayer();
        layer.name = fixPath("dun");
        layer.type = TextureLayer.Type.ROOT;
        float dunpower = 0.6f;
        if (!horse.isDun()) {
            dunpower = 0.9f;
        }
        final int val = (int)(dunpower * 255.0f);
        layer.red = val;
        layer.green = val;
        layer.blue = val;
        layers.add(layer);
    }
    
    public static TextureLayer getSooty(final HorseGenome horse) {
        final TextureLayer layer = new TextureLayer();
        final int sooty_level = horse.getSootyLevel();
        switch (sooty_level) {
            case 0: {
                return null;
            }
            case 1: {
                layer.alpha = 102;
                break;
            }
            case 2: {
                layer.alpha = 204;
                break;
            }
            case 3: {
                layer.alpha = 255;
                break;
            }
            default: {
                layer.alpha = 255;
                break;
            }
        }
        if (horse.hasAllele("donkey_dark", 1) && !horse.isChestnut()) {
            layer.alpha = 255;
        }
        layer.name = fixPath("sooty_countershade");
        if (horse.isDappleInclined()) {
            layer.name = fixPath("sooty_dapple");
        }
        else if (horse.isChestnut()) {
            layer.name = fixPath("base");
            final TextureLayer textureLayer = layer;
            textureLayer.alpha /= 2;
        }
        colorBlackBody(horse, layer);
        setGrayConcentration(horse, layer);
        return layer;
    }
    
    public static TextureLayer getMealy(final HorseGenome horse) {
        if (!horse.isMealy()) {
            return null;
        }
        final TextureLayer light_belly = new TextureLayer();
        int spread = 1;
        int color = 0;
        if (horse.hasAllele("mealy1", 1)) {
            spread += 2;
        }
        if (horse.hasAllele("mealy2", 1)) {
            ++color;
        }
        if (horse.isHomozygous("flaxen2", 0)) {
            ++spread;
        }
        String prefix = "";
        if (horse.isHomozygous("light_legs", 1)) {
            prefix = "l";
        }
        else if (horse.hasAllele("less_light_legs", 0)) {
            prefix = "l";
            if (spread > 1) {
                --spread;
                light_belly.next = new TextureLayer();
                light_belly.next.name = fixPath("mealy/mealy1");
                colorRedBody(horse, light_belly.next);
                adjustConcentration(light_belly.next, 0.04f * (2 - color));
            }
        }
        light_belly.name = fixPath("mealy/" + prefix + "mealy" + spread);
        colorRedBody(horse, light_belly);
        adjustConcentration(light_belly, 0.04f * (2 - color));
        return light_belly;
    }
    
    public static void addPoints(final HorseGenome horse, final List<TextureLayer> layers) {
        String prefix = "";
        if (horse.hasAllele("reduced_points", 1)) {
            prefix = "wild_";
        }
        if (horse.hasStripe()) {
            final TextureLayer stripe = new TextureLayer();
            if (horse.hasAllele("cross", 1)) {
                stripe.name = fixPath("marks/" + prefix + "cross");
            }
            else {
                stripe.name = fixPath("marks/" + prefix + "dorsal");
            }
            if (horse.isChestnut()) {
                colorRedBody(horse, stripe);
            }
            else {
                colorBlackBody(horse, stripe);
            }
            adjustConcentration(stripe, 1.2f);
            layers.add(stripe);
        }
        else if (!horse.isChestnut()) {
            final TextureLayer points = new TextureLayer();
            points.name = fixPath(prefix + "bay");
            colorBlackBody(horse, points);
            layers.add(points);
        }
    }
    
    public static void addGray(final HorseGenome horse, final List<TextureLayer> layers) {
        if (!horse.isGray()) {
            return;
        }
        final float rate = horse.getGrayRate();
        final float mane_rate = horse.getGrayManeRate();
        final int body_stage = grayStage(horse, rate, 19, 0.25f);
        final int mane_stage = grayStage(horse, mane_rate, 20, 0.3f);
        if (body_stage > 0) {
            final TextureLayer body = new TextureLayer();
            if (body_stage > 19) {
                body.name = fixPath("body");
            }
            else {
                body.name = fixPath("gray/dapple" + body_stage);
            }
            colorGray(horse, body);
            layers.add(body);
        }
        if (mane_stage > 0) {
            final TextureLayer mane = new TextureLayer();
            if (mane_stage > 20) {
                mane.name = fixPath("manetail");
            }
            else {
                mane.name = fixPath("gray/mane" + mane_stage);
            }
            colorGray(horse, mane);
            layers.add(mane);
        }
    }
    
    public static int grayStage(final HorseGenome horse, final float rate, final int num_stages, final float delay) {
        final int YEAR_TICKS = HorseConfig.getYearLength();
        final int MAX_AGE = HorseConfig.GROWTH.getMaxAge();
        int age = horse.getAge() + 24000;
        age = Math.min(age, MAX_AGE);
        if (!HorseConfig.GROWTH.grayGradually) {
            age = (int)(MAX_AGE * 0.5f);
        }
        float gray_age = age / (YEAR_TICKS * rate);
        gray_age = (gray_age - delay) / (1.0f - delay);
        if (gray_age <= 0.0f) {
            return 0;
        }
        if (gray_age >= 1.0f) {
            return num_stages + 1;
        }
        return (int)(gray_age * num_stages);
    }
    
    public static float grayConcentration(final HorseGenome horse, final float rate) {
        final int stage = grayStage(horse, rate, 50, 0.0f);
        final double val = 1.1 + Math.pow(1.06, stage) * stage / 50.0 * stage / 50.0;
        return (float)val;
    }
    
    public static void setGrayConcentration(final HorseGenome horse, final TextureLayer layer) {
        if (horse.isGray()) {
            final float prevRed = (float)layer.red;
            final float prevGreen = (float)layer.green;
            final float prevBlue = (float)layer.blue;
            final float concentration = grayConcentration(horse, horse.getGrayRate());
            adjustConcentration(layer, concentration);
            final float lightnessDiff = (layer.red + layer.green + layer.blue) / (prevRed + prevGreen + prevBlue);
            layer.red = (int)((layer.red + lightnessDiff * prevRed) / 2.0f);
            layer.green = (int)((layer.green + lightnessDiff * prevGreen) / 2.0f);
            layer.blue = (int)((layer.blue + lightnessDiff * prevBlue) / 2.0f);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static List<TextureLayer> getTexturePaths(final HorseGenome horse) {
        final List<TextureLayer> textureLayers = new ArrayList<TextureLayer>();
        final TextureLayer red = getRedBody(horse);
        textureLayers.add(red);
        textureLayers.add(getMealy(horse));
        final TextureLayer black = getBlackBody(horse);
        textureLayers.add(black);
        addDun(horse, textureLayers);
        addPoints(horse, textureLayers);
        addRedManeTail(horse, textureLayers);
        textureLayers.add(getBlackManeTail(horse));
        addGray(horse, textureLayers);
        textureLayers.add(getNose(horse));
        textureLayers.add(getHooves(horse));
        if (horse.hasAllele("KIT", 14)) {
            final int variants = 4;
            final int rnd = HorseColorCalculator.randSource.getVal("roan_var", horse.getChromosome("random"));
            final int idx = rnd % variants;
            final TextureLayer roan = new TextureLayer();
            if (idx == 0) {
                roan.name = fixPath("roan/roan");
            }
            else {
                roan.name = fixPath("roan/roan" + idx);
            }
            textureLayers.add(roan);
        }
        HorsePatternCalculator.addFaceMarkings(horse, textureLayers);
        if (horse.showsLegMarkings()) {
            final String[] legMarkings;
            final String[] leg_markings = legMarkings = HorsePatternCalculator.getLegMarkings(horse);
            for (final String marking : legMarkings) {
                final TextureLayer layer = new TextureLayer();
                layer.name = marking;
                textureLayers.add(layer);
            }
        }
        textureLayers.add(HorsePatternCalculator.getPinto(horse));
        HorsePatternCalculator.addLeopard(horse, textureLayers);
        final TextureLayer highlights = new TextureLayer();
        highlights.name = fixPath("base");
        highlights.type = TextureLayer.Type.HIGHLIGHT;
        highlights.alpha = 51;
        textureLayers.add(highlights);
        final TextureLayer shading = new TextureLayer();
        shading.name = fixPath("shading");
        shading.type = TextureLayer.Type.SHADE;
        shading.alpha = 127;
        textureLayers.add(shading);
        final TextureLayer common = new TextureLayer();
        common.name = fixPath("common");
        textureLayers.add(common);
        return textureLayers;
    }
    
    static {
        randSource = new RandomSupplier((List)ImmutableList.of((Object)"leg_white", (Object)"face_white", (Object)"leopard", (Object)"star_choice", (Object)"pinto_tobiano", (Object)"pinto_frame", (Object)"pinto_sabino", (Object)"roan_var", (Object)"pinto_splash"));
    }
}
