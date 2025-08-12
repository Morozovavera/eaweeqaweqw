package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class TarpanEurope
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.init();
        TarpanEurope.breed = new Breed(Tarpan.breed);
        TarpanEurope.breed.name = "tarpan";
        final Map<String, List<Float>> COLORS = TarpanEurope.breed.colors;
        COLORS.put("leopard", (List<Float>)ImmutableList.of((Object)0.96875f, (Object)1.0f));
        COLORS.put("PATN1", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN2", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN3", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("marble", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
        COLORS.put("leopard_suppression", (List<Float>)ImmutableList.of((Object)0.92f, (Object)1.0f));
        COLORS.put("leopard_suppression2", (List<Float>)ImmutableList.of((Object)0.88f, (Object)1.0f));
        COLORS.put("PATN_boost1", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
        COLORS.put("PATN_boost2", (List<Float>)ImmutableList.of((Object)0.9375f, (Object)1.0f));
    }
}
