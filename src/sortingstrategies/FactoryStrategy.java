package sortingstrategies;

import strategies.EnergyChoiceStrategyType;

public final class FactoryStrategy {
    private FactoryStrategy() {
    }

    /**
     * Factory ca sa obtin un vector sortat in functie de 'strategyType'
     *
     * @param strategyType strategia aplicata
     * @return vector sortat dupa strategia aplicata
     */
    public static SortingStrategy getSortedProducers(EnergyChoiceStrategyType strategyType) {
        switch (strategyType) {
            case GREEN -> {
                return new GreenSortingStrategy();
            }
            case PRICE -> {
                return new PriceSortingStrategy();
            }
            case QUANTITY -> {
                return new QuantitySortingStrategy();
            }
            default -> {
                return null;
            }
        }
    }
}
