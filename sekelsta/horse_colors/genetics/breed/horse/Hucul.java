package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class Hucul
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        TarpanEurope.init();
        Hucul.breed = new Breed(TarpanEurope.breed);
        EarlyDomesticHorse.init();
        Hucul.breed.merge(EarlyDomesticHorse.breed, 0.2f);
        MongolianHorse.init();
        Hucul.breed.merge(MongolianHorse.breed, 0.2f);
        Hucul.breed.name = "hucul";
        Hucul.breed.population = 4000;
        final Map<String, List<Float>> COLORS = Hucul.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.28f, (Object)0.28f, (Object)0.28f, (Object)0.28f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.5f, (Object)0.6f, (Object)0.6f, (Object)0.6f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("dun", (List<Float>)ImmutableList.of((Object)0.3f, (Object)0.8f, (Object)1.0f, (Object)0.0f));
        COLORS.put("KIT", (List<Float>)ImmutableList.of((Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.89f, (Object)0.0f, (Object)0.89f, (Object)0.0f, (Object)1.0f, (Object[])new Float[] { 1.0f, 1.0f, 1.0f, 1.0f }));
        COLORS.put("gray", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("cream", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("leopard", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("light_belly", (List<Float>)ImmutableList.of((Object)1.0f, (Object)1.0f));
        COLORS.put("mealy1", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
        COLORS.put("mealy2", (List<Float>)ImmutableList.of((Object)0.75f, (Object)1.0f));
    }
}
