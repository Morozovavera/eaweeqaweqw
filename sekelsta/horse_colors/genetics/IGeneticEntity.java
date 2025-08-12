package sekelsta.horse_colors.genetics;

import java.util.*;
import net.minecraft.entity.*;
import sekelsta.horse_colors.genetics.breed.*;

public interface IGeneticEntity
{
    Genome getGenes();
    
    int getChromosome(final String p0);
    
    void setChromosome(final String p0, final int p1);
    
    Random getRand();
    
    default Species getSpecies() {
        return null;
    }
    
    boolean isMale();
    
    void setMale(final boolean p0);
    
    int getRebreedTicks();
    
    int getBirthAge();
    
    boolean setPregnantWith(final EntityAgeable p0, final EntityAgeable p1);
    
    default Breed getDefaultBreed() {
        return new Breed();
    }
}
