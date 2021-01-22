package sortingstrategies;

import baseclasses.Producer;

import java.util.ArrayList;

public final class PriceSortingStrategy implements SortingStrategy {

    private PriceSortingStrategy() {
    }

    /**
     * Strategie de sortare Price
     *
     * @param producers
     * @return
     */
    public static ArrayList<Producer> sort(ArrayList<Producer> producers) {
        ArrayList<Producer> sortedPriceProducers = new ArrayList<>(producers);

        sortedPriceProducers.sort((p1, p2) -> {
            if (p1.getPriceKW() == p2.getPriceKW()) {
                if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()) {
                    return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
                }
                return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
            }
            return Double.compare(p1.getPriceKW(), p2.getPriceKW());
        });

        return sortedPriceProducers;
    }
}
