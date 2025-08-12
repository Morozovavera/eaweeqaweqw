package sekelsta.horse_colors.genetics;

import java.util.*;
import net.minecraft.entity.*;

public class FakeGeneticEntity implements IGeneticEntity
{
    private Genome genome;
    private HashMap<String, Integer> map;
    private boolean gender;
    
    public FakeGeneticEntity() {
        this.map = new HashMap<String, Integer>();
    }
    
    @Override
    public Genome getGenes() {
        return this.genome;
    }
    
    @Override
    public int getChromosome(final String name) {
        if (this.map.containsKey(name)) {
            return this.map.get(name);
        }
        return 0;
    }
    
    @Override
    public void setChromosome(final String name, final int val) {
        this.map.put(name, val);
    }
    
    @Override
    public Random getRand() {
        return new Random();
    }
    
    @Override
    public boolean isMale() {
        return this.gender;
    }
    
    @Override
    public void setMale(final boolean gender) {
        this.gender = gender;
    }
    
    @Override
    public int getRebreedTicks() {
        return 0;
    }
    
    @Override
    public int getBirthAge() {
        return 0;
    }
    
    @Override
    public boolean setPregnantWith(final EntityAgeable child, final EntityAgeable otherParent) {
        return false;
    }
}
