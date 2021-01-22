package sortingstrategies;

import baseclasses.Producer;

import java.util.ArrayList;

public final class QuantitySortingStrategy implements SortingStrategy {

    private QuantitySortingStrategy() {
    }

    /**
     * Strategie de sortare Quantity
     *
     * @param producers
     * @return
     */
    public static ArrayList<Producer> sort(ArrayList<Producer> producers) {
        ArrayList<Producer> sortedQuantityProducers = new ArrayList<>(producers);

        sortedQuantityProducers.sort((p1, p2) -> {
            if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()) {
                return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
            }
            return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
        });

        return sortedQuantityProducers;
    }
}
