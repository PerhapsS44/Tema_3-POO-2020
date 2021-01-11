package gamemaster;

import baseclasses.Consumer;
import baseclasses.Contract;
import baseclasses.Distributor;
import database.Database;
import inputclasses.DistributorChange;
import inputclasses.MonthlyUpdate;

import java.util.ArrayList;

public final class GameMaster {
    private static Database database;
    private static GameMaster instance;

    private GameMaster() {
        Database.resetInstance();
        database = Database.getInstance();
    }

    /**
     * Singleton get-method
     * @return
     */
    public static GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    /**
     * Singleton reset-method
     * (pentru rularea multor teste intr-un singur run)
     */
    public static void resetInstance() {
        instance = new GameMaster();
    }

    public static void setDatabase(final Database database) {
        GameMaster.database = database;
    }

    /**
     * Ruleaza simularea
     */
    public void simulate() {
        monthlySimulation();
        for (int i = 0; i < database.getInputData().getNumberOfTurns(); i++) {
            monthlyUpdate(i);
            monthlySimulation();
        }
    }

    /**
     * Ruleaza simularea pentru o singura luna
     * (o singura iteratie din simulare)
     */
    public void monthlySimulation() {

        createBaseContracts();

        createNewContracts();

        for (Distributor distributor : database.getDistributors()) {
            distributor.updateExpenses();
        }

        for (Consumer consumer : database.getConsumers()) {
            if (consumer.isBankrupt()) {
                continue;
            }
            consumer.getSalary();
            consumer.payTaxes();
        }

        removeBankruptConsumersFromGame();

        for (Distributor distributor : database.getDistributors()) {
            if (!distributor.isBankrupt()) {
                distributor.payTaxes();
            }
        }

    }

    /**
     * Se actualizeaza baza de date in functie de datele din input
     * @param month luna curenta
     */
    public void monthlyUpdate(final int month) {
        MonthlyUpdate currentUpdate = database.getInputData().getMonthlyUpdates().get(month);
        database.getConsumers().addAll(currentUpdate.getNewConsumers());
        for (DistributorChange costChange : currentUpdate.getDistributorChanges()) {
            for (Distributor distributor : database.getDistributors()) {
                if (distributor.getId() == costChange.getId()) {
                    distributor.setInitialInfrastructureCost(costChange.getInfrastructureCost());
//                    distributor.setInitialProductionCost(costChange.getProductionCost());
                    distributor.setInitialProductionCost(0);
                    break;
                }
            }
        }
    }

    /**
     * Creeaza contractele de baza (schelete) pentru toti distribuitorii
     */
    public void createBaseContracts() {
        for (Distributor distributor : database.getDistributors()) {
            if (distributor.isBankrupt()) {
                continue;
            }
            if (distributor.getContracts() == null) {
                distributor.setContracts(new ArrayList<>());
            }

            distributor.createBaseContract();
        }
    }

    /**
     * Creeaza contractele pentru consumatorii fara contracte / cu contractele expirate
     */
    public void createNewContracts() {
        Distributor lowestPrice = database.getLowestPrice();
        Contract temp;
        for (Consumer consumer : database.getConsumers()) {
            if (consumer.isBankrupt()) {
                continue;
            }
            if (consumer.getCurrentContract() != null
                    && consumer.getCurrentContract().getRemainedContractMonths() != 0) {
                continue;
            }
            temp = new Contract(lowestPrice.getBaseContract().getRemainedContractMonths(),
                    lowestPrice.getBaseContract().getPrice(), consumer, lowestPrice);
            consumer.setCurrentContract(temp);
            lowestPrice.getContracts().add(temp);
        }

        for (Distributor distributor : database.getDistributors()) {
            distributor.getContracts().removeIf((c) -> c.getRemainedContractMonths() == 0);
        }
    }

    /**
     * Scoate din joc consumatorii bankrupt
     */
    public void removeBankruptConsumersFromGame() {
        for (Consumer consumer : database.getConsumers()) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentContract().getDistributor().getContracts()
                        .remove(consumer.getCurrentContract());
            }
        }
    }
}
