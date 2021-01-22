package sortingstrategies;

import baseclasses.Producer;

import java.util.ArrayList;

public final class GreenSortingStrategy implements SortingStrategy {
    public GreenSortingStrategy() {
    }

    /**
     * Strategie de sortare Green
     *
     * @param producers
     * @return
     */
    public ArrayList<Producer> sort(final ArrayList<Producer> producers) {

        ArrayList<Producer> sortedGreenProducers = new ArrayList<>(producers);

        sortedGreenProducers.sort((p1, p2) -> {
            if (p1.getEnergyType().isRenewable() == p2.getEnergyType().isRenewable()) {
                if (p1.getPriceKW() == p2.getPriceKW()) {
                    if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()) {
                        return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
                    }
                    return Integer.compare(p2.getEnergyPerDistributor(),
                            p1.getEnergyPerDistributor());
                }
                return Double.compare(p1.getPriceKW(), p2.getPriceKW());
            } else {
                if (p1.getEnergyType().isRenewable()) {
                    return -1;
                }
                return 1;
            }
        });

        return sortedGreenProducers;
    }
}
