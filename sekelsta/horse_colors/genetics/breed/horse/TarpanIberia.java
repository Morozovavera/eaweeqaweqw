package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class TarpanIberia
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.init();
        TarpanIberia.breed = new Breed(Tarpan.breed);
        TarpanIberia.breed.name = "tarpan";
        final Map<String, List<Float>> COLORS = TarpanIberia.breed.colors;
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.6f, (Object)0.65f, (Object)0.65f, (Object)0.65f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
    }
}
