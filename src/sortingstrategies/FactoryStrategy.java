package sortingstrategies;

import baseclasses.Producer;
import database.Database;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class FactoryStrategy {
    private FactoryStrategy() {
    }

    /**
     * Factory ca sa obtin un vector sortat in functie de 'strategyType'
     *
     * @param strategyType strategia aplicata
     * @return vector sortat dupa strategia aplicata
     */
    public static ArrayList<Producer> getSortedProducers(EnergyChoiceStrategyType strategyType) {
        switch (strategyType) {
            case GREEN -> {
                return GreenSortingStrategy.sort(Database.getInstance().getProducers());
            }
            case PRICE -> {
                return PriceSortingStrategy.sort(Database.getInstance().getProducers());
            }
            case QUANTITY -> {
                return QuantitySortingStrategy.sort(Database.getInstance().getProducers());
            }
            default -> {
                return Database.getInstance().getProducers();
            }
        }
    }
}
