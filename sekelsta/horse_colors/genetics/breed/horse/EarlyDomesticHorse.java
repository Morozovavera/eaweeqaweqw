package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class EarlyDomesticHorse
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.init();
        EarlyDomesticHorse.breed = new Breed(Tarpan.breed);
        final Map<String, List<Float>> COLORS = EarlyDomesticHorse.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.375f, (Object)0.5f, (Object)0.5f, (Object)0.5f, (Object)1.0f, (Object)0.875f, (Object)0.9375f, (Object)1.0f));
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.4f, (Object)0.8f, (Object)1.0f, (Object)0.0f));
        COLORS.put("KIT", (List<Float>)ImmutableList.of((Object)0.65f, (Object)0.66f, (Object)0.67f, (Object)0.68f, (Object)0.69f, (Object)0.7f, (Object)0.71f, (Object)0.71f, (Object)0.0f, (Object)0.73f, (Object)0.0f, (Object)1.0f, (Object[])new Float[] { 1.0f, 1.0f, 1.0f, 1.0f }));
        COLORS.put("white_star", (List<Float>)ImmutableList.of((Object)0.85f, (Object)1.0f));
        COLORS.put("white_forelegs", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
    }
}
