package sekelsta.horse_colors.genetics.breed.donkey;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class BaseDonkey
{
    public static Breed breed;
    
    static {
        BaseDonkey.breed = new Breed(BaseEquine.breed);
        final Map<String, List<Float>> COLORS = BaseDonkey.breed.colors;
        COLORS.put("extension", (List<Float>)ImmutableList.of((Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)0.2f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("agouti", (List<Float>)ImmutableList.of((Object)0.05f, (Object)0.15f, (Object)0.1f, (Object)0.1f, (Object)1.0f, (Object)1.0f, (Object)1.0f, (Object)1.0f));
        COLORS.put("cross", (List<Float>)ImmutableList.of((Object)0.0f, (Object)1.0f));
        COLORS.put("light_legs", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("less_light_legs", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        COLORS.put("donkey_dun", (List<Float>)ImmutableList.of((Object)0.5f, (Object)0.95f, (Object)1.0f, (Object)0.0f));
    }
}
