package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class Tarpan
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.breed = new Breed(BaseHorse.breed);
        Tarpan.breed.name = "tarpan";
        final Map<String, List<Float>> COLORS = Tarpan.breed.colors;
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.5f, (Object)1.0f, (Object)0.0f));
        COLORS.put("liver", (List<Float>)ImmutableList.of((Object)0.25f, (Object)1.0f));
        COLORS.put("dapple", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("flaxen1", (List<Float>)ImmutableList.of((Object)0.2f, (Object)1.0f));
        COLORS.put("flaxen2", (List<Float>)ImmutableList.of((Object)0.2f, (Object)1.0f));
        COLORS.put("sooty2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("mealy1", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("mealy2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("light_belly", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("gray_suppression", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("reduced_points", (List<Float>)ImmutableList.of((Object)0.95f, (Object)1.0f));
    }
}
