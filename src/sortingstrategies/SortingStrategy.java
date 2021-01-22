package sortingstrategies;

import baseclasses.Producer;

import java.util.ArrayList;

public interface SortingStrategy {

    /**
     * Metoda de sortare a producatorilor dupa o anumita strategie
     * @param producers
     * @return
     */
    static ArrayList<Producer> sort(ArrayList<Producer> producers) {
        return new ArrayList<>();
    }
}
