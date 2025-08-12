package sekelsta.horse_colors.genetics.breed.horse;

import sekelsta.horse_colors.genetics.breed.*;
import java.util.*;

public class Takhi
{
    public static Breed breed;
    
    static {
        Takhi.breed = new Breed(BaseHorse.breed);
        Takhi.breed.name = "takhi";
        final Map<String, List<Float>> colors = Takhi.breed.colors;
    }
}
