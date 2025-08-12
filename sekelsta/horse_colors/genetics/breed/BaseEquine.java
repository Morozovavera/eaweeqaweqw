package sekelsta.horse_colors.genetics.breed;

import com.google.common.collect.*;
import java.util.*;

public class BaseEquine
{
    public static Breed breed;
    
    static {
        BaseEquine.breed = new Breed();
        final Map<String, List<Float>> COLORS = BaseEquine.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.0f, (Object)0.0f, (Object)0.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("gray", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.0f, (Object)0.0f, (Object)1.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.1f, (Object)0.1f, (Object)0.1f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("silver", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("cream", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.0f, (Object)0.0f, (Object)0.0f, (Object)0.0f));
        COLORS.put("liver", (List<Float>)ImmutableList.of((Object)0.1f, (Object)1.0f));
        COLORS.put("flaxen1", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("flaxen2", (List<Float>)ImmutableList.of((Object)0.2f, (Object)1.0f));
        COLORS.put("dapple", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("sooty1", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("sooty2", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("sooty3", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("light_belly", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("mealy1", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("mealy2", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("white_suppression", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("KIT", (List<Float>)ImmutableList.of((Object)1.0f));
        COLORS.put("frame", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("MITF", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.0f, (Object)0.0f, (Object)1.0f));
        COLORS.put("PAX3", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("leopard", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("PATN1", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("PATN2", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("PATN3", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("gray_suppression", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("slow_gray1", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("slow_gray2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("slow_gray3", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("white_star", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("white_forelegs", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("white_hindlegs", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("gray_melanoma", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("gray_mane1", (List<Float>)ImmutableList.of((Object)0.25f, (Object)1.0f));
        COLORS.put("gray_mane2", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("rufous", (List<Float>)ImmutableList.of((Object)0.1f, (Object)1.0f));
        COLORS.put("dense", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("champagne", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("cameo", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("ivory", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("donkey_dark", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("reduced_points", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("light_legs", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("less_light_legs", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("donkey_dun", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.0f, (Object)0.0f, (Object)0.0f));
        COLORS.put("flaxen_boost", (List<Float>)ImmutableList.of((Object)0.95f, (Object)1.0f));
        COLORS.put("light_dun", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("marble", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("leopard_suppression", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("leopard_suppression2", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("PATN_boost1", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("PATN_boost2", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("dark_red", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
    }
}
