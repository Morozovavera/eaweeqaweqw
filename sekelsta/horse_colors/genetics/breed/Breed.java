package sekelsta.horse_colors.genetics.breed;

import com.google.common.collect.*;
import java.util.*;

public class Breed
{
    public String name;
    public int population;
    public Map<String, List<Float>> colors;
    
    public Breed() {
        this.colors = new HashMap<String, List<Float>>();
    }
    
    public Breed(final Breed copy) {
        this.colors = new HashMap<String, List<Float>>(copy.colors);
    }
    
    public void merge(final Breed breed, final float weight) {
        final TreeSet<String> colorKeys = new TreeSet<String>(this.colors.keySet());
        for (final String key : breed.colors.keySet()) {
            colorKeys.add(key);
        }
        for (final String s : colorKeys) {
            List<Float> l1 = (List<Float>)ImmutableList.of((Object)1.0f);
            if (this.colors.containsKey(s)) {
                l1 = this.colors.get(s);
            }
            List<Float> l2 = (List<Float>)ImmutableList.of((Object)1.0f);
            if (breed.colors.containsKey(s)) {
                l2 = breed.colors.get(s);
            }
            this.colors.put(s, this.mergeList(l1, l2, weight));
        }
    }
    
    public List<Float> mergeList(final List<Float> base, final List<Float> additional, final float weight) {
        final ArrayList<Float> output = new ArrayList<Float>();
        for (int size = Math.max(base.size(), additional.size()), i = 0; i < size; ++i) {
            float baseNum = 1.0f;
            if (base.size() > i) {
                baseNum = base.get(i);
            }
            float otherNum = 1.0f;
            if (additional.size() > i) {
                otherNum = additional.get(i);
            }
            output.add(baseNum * (1.0f - weight) + otherNum * weight);
        }
        return output;
    }
}
