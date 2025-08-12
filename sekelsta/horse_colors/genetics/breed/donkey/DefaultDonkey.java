package sekelsta.horse_colors.genetics.breed.donkey;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class DefaultDonkey
{
    public static Breed breed;
    
    static {
        DefaultDonkey.breed = new Breed(BaseDonkey.breed);
        final Map<String, List<Float>> COLORS = DefaultDonkey.breed.colors;
        COLORS.put("cameo", (List<Float>)ImmutableList.of((Object)0.99f, (Object)1.0f));
        COLORS.put("ivory", (List<Float>)ImmutableList.of((Object)0.9f, (Object)1.0f));
    }
}
