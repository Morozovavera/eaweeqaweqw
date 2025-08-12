package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import java.util.*;

public class TarpanAsia
{
    public static Breed breed;
    
    public static void init() {
    }
    
    static {
        Tarpan.init();
        TarpanAsia.breed = new Breed(Tarpan.breed);
        TarpanAsia.breed.name = "tarpan";
        final Map<String, List<Float>> colors = TarpanAsia.breed.colors;
    }
}
