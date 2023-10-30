package arbol;

import java.util.Comparator;

public class ComparatorInteger implements Comparator<Integer> {
    @Override
    public int compare(Integer x, Integer y) {
        return x.compareTo(y);
    }
}

