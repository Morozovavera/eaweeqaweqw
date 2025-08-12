package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class MongolianHorse
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        EarlyDomesticHorse.init();
        MongolianHorse.breed = new Breed(EarlyDomesticHorse.breed);
        MongolianHorse.breed.name = "mongolian";
        final Map<String, List<Float>> COLORS = MongolianHorse.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.375f, (Object)0.5f, (Object)0.5f, (Object)0.5f, (Object)1.0f, (Object)0.875f, (Object)0.9375f, (Object)1.0f));
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.4f, (Object)0.8f, (Object)1.0f, (Object)0.0f));
    }
}
