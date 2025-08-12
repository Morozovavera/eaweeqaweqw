package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class BaseHorse
{
    public static Breed breed;
    
    static {
        BaseHorse.breed = new Breed(BaseEquine.breed);
        final Map<String, List<Float>> COLORS = BaseHorse.breed.colors;
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.0f, (Object)0.0f, (Object)1.0f, (Object)0.0f));
        COLORS.put("liver", (List<Float>)ImmutableList.of((Object)0.1f, (Object)1.0f));
        COLORS.put("flaxen1", (List<Float>)ImmutableList.of((Object)0.1f, (Object)1.0f));
        COLORS.put("flaxen2", (List<Float>)ImmutableList.of((Object)0.1f, (Object)1.0f));
        COLORS.put("sooty2", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("mealy1", (List<Float>)ImmutableList.of((Object)0.25f, (Object)1.0f));
        COLORS.put("mealy2", (List<Float>)ImmutableList.of((Object)0.25f, (Object)1.0f));
        COLORS.put("white_suppression", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("reduced_points", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("flaxen_boost", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("light_dun", (List<Float>)ImmutableList.of((Object)1.0f, (Object)0.0f));
    }
}
