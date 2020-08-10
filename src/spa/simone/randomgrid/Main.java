package spa.simone.randomgrid;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author saimon
 */
public class Main {

    /*
     * 42 è il massimo.
     * Con 43 numeri posizionati l'algoritmo va in starvation.
     */
    public static void main(String[] args) {
        /*
        RandomGrid grid = new RandomGrid();
        grid.initGrid(70);
        grid.printGrid();
        */
        HashMap<String, String> hm1 = new HashMap<>();
        HashMap<String, String> hm2 = new HashMap<>();
        Collection<HashMap<String, String>> coll = doSomething(hm1, hm2);
    }
    
    public static <T> Collection<T> doSomething(T... params) {
        return null;
    }
}
