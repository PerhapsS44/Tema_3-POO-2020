package gamemaster;

import Utils.SortedProducers;
import baseclasses.Consumer;
import baseclasses.Contract;
import baseclasses.Distributor;
import baseclasses.Producer;
import database.Database;
import inputclasses.DistributorChange;
import inputclasses.MonthlyUpdate;
import inputclasses.ProducerChange;

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
//        System.out.println("luna 0");
        monthlySimulation();
        for (int i = 0; i < database.getInputData().getNumberOfTurns(); i++) {
//            System.out.println();
//            System.out.println();
//            System.out.println("luna " + (i+1));
            monthlyUpdateBeginning(i);
            if (database.areDistributorsBankrupt() == true){
                break;
            }
            monthlySimulation();
            monthlyUpdateEnding(i);
            for (Producer producer : database.getProducers()){
                producer.setMonthlyStats(i);
            }
        }
    }

    /**
     * Ruleaza simularea pentru o singura luna
     * (o singura iteratie din simulare)
     */
    public void monthlySimulation() {
//        System.out.println("Inceput de luna");
//        System.out.println(database.getConsumers());
//        System.out.println(database.getDistributors());
//        System.out.println(database.getProducers());

        SortedProducers sortedProducers = new SortedProducers(database.getProducers());
        for (Distributor distributor : database.getDistributors()){
            distributor.updateProductionCost(sortedProducers);
        }

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

//        System.out.println("Final de luna");
//        System.out.println(database.getConsumers());
//        System.out.println(database.getDistributors());
//        System.out.println(database.getProducers());
    }

    /**
     * Se actualizeaza baza de date in functie de datele din input
     * @param month luna curenta
     */
    public void monthlyUpdateBeginning(final int month) {
        MonthlyUpdate currentUpdate = database.getInputData().getMonthlyUpdates().get(month);
        database.getConsumers().addAll(currentUpdate.getNewConsumers());
        for (DistributorChange costChange : currentUpdate.getDistributorChanges()) {
            for (Distributor distributor : database.getDistributors()) {
                if (distributor.getId() == costChange.getId()) {
                    distributor.setInitialInfrastructureCost(costChange.getInfrastructureCost());
                    break;
                }
            }
        }
    }

    public void monthlyUpdateEnding(final int month){
        MonthlyUpdate currentUpdate = database.getInputData().getMonthlyUpdates().get(month);
        for (ProducerChange producerChange : currentUpdate.getProducerChanges()){
            for (Producer producer : database.getProducers()){
                if (producer.getId() == producerChange.getId()){
                    producer.setEnergyPerDistributor(producerChange.getEnergyPerDistributor());
                    producer.getDistributors().forEach(Distributor::setNeedProdUpdate);
                    break;
                }
            }
        }
        SortedProducers sortedProducers = new SortedProducers(database.getProducers());
        for (Distributor distributor : database.getDistributors()){
            if (distributor.isNeedProdUpdate()){
                for (Producer producer2 : database.getProducers()){
                    producer2.removeDistributor(distributor);
                }
                distributor.updateProductionCost(sortedProducers);
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
