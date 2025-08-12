package sekelsta.horse_colors.util;

import java.util.*;

public class RandomSupplier
{
    List<String> keys;
    
    public RandomSupplier(final List keys) {
        this.keys = new ArrayList<String>();
        this.keys = (List<String>)keys;
    }
    
    public int getVal(final String keyRequested, final int seed) {
        final Random rand = new Random(seed);
        int current = seed;
        for (final String key : this.keys) {
            if (key.equals(keyRequested)) {
                return current;
            }
            current = rand.nextInt();
        }
        System.err.println("Key not found in RandomSupplier: " + keyRequested);
        return 0;
    }
}
