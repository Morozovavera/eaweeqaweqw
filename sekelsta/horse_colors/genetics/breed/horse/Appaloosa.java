package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import com.google.common.collect.*;
import java.util.*;

public class Appaloosa
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Appaloosa.breed = new Breed(DefaultHorse.breed);
        Appaloosa.breed.name = "appaloosa";
        final Map<String, List<Float>> APPALOOSA = Appaloosa.breed.colors;
        APPALOOSA.put("leopard", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        APPALOOSA.put("PATN1", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        APPALOOSA.put("PATN2", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
        APPALOOSA.put("PATN3", (List<Float>)ImmutableList.of((Object)0.5f, (Object)1.0f));
    }
}
