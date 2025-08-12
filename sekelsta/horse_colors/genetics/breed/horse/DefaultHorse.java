package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class DefaultHorse
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.init();
        DefaultHorse.breed = new Breed(Tarpan.breed);
        final Map<String, List<Float>> COLORS = DefaultHorse.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.5f, (Object)0.25f, (Object)0.375f, (Object)0.5f, (Object)1.0f, (Object)0.75f, (Object)0.875f, (Object)1.0f));
        COLORS.put("gray", (List<Float>)ImmutableList.of((Object)0.95f, (Object)1.0f));
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.9f, (Object)0.92f, (Object)1.0f, (Object)0.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.375f, (Object)0.5f, (Object)0.5f, (Object)0.5f, (Object)1.0f, (Object)0.875f, (Object)0.9375f, (Object)1.0f));
        COLORS.put("silver", (List<Float>)ImmutableList.of((Object)0.96875f, (Object)1.0f));
        COLORS.put("cream", (List<Float>)ImmutableList.of((Object)0.8375f, (Object)0.84f, (Object)0.86875f, (Object)0.9f, (Object)1.0f));
        COLORS.put("liver", (List<Float>)ImmutableList.of((Object)0.25f, (Object)1.0f));
        COLORS.put("flaxen1", (List<Float>)ImmutableList.of((Object)0.2f, (Object)1.0f));
        COLORS.put("flaxen2", (List<Float>)ImmutableList.of((Object)0.2f, (Object)1.0f));
        COLORS.put("dapple", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("sooty2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("light_belly", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("mealy1", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("mealy2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("white_suppression", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("KIT", (List<Float>)ImmutableList.of((Object)0.65f, (Object)0.66f, (Object)0.67f, (Object)0.68f, (Object)0.69f, (Object)0.7f, (Object)0.71f, (Object)0.86f, (Object)0.0f, (Object)0.88f, (Object)0.0f, (Object)0.92f, (Object[])new Float[] { 0.94f, 0.96f, 0.99f, 1.0f }));
        COLORS.put("frame", (List<Float>)ImmutableList.of((Object)0.96875f, (Object)1.0f));
        COLORS.put("MITF", (List<Float>)ImmutableList.of((Object)0.1f, (Object)0.1f, (Object)0.1f, (Object)1.0f));
        COLORS.put("PAX3", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.96f, (Object)1.0f, (Object)1.0f));
        COLORS.put("white_star", (List<Float>)ImmutableList.of((Object)0.85f, (Object)1.0f));
        COLORS.put("white_forelegs", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("white_hindlegs", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("champagne", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("donkey_dark", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("reduced_points", (List<Float>)ImmutableList.of((Object)0.95f, (Object)1.0f));
        COLORS.put("flaxen_boost", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("light_dun", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.0f));
        COLORS.put("leopard", (List<Float>)ImmutableList.of((Object)0.96875f, (Object)1.0f));
        COLORS.put("PATN1", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN2", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN3", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("marble", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("leopard_suppression", (List<Float>)ImmutableList.of((Object)0.88f, (Object)1.0f));
        COLORS.put("leopard_suppression2", (List<Float>)ImmutableList.of((Object)0.88f, (Object)1.0f));
        COLORS.put("PATN_boost1", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN_boost2", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
    }
}
