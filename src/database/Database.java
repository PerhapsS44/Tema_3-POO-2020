package database;

import baseclasses.Consumer;
import baseclasses.Distributor;
import baseclasses.Producer;
import systemio.InputData;
import systemio.OutputData;

import java.util.ArrayList;

public final class Database {
    private static Database instance;
    private InputData inputData;
    private OutputData output;

    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;

    private Database() {
        inputData = new InputData();
        output = new OutputData();
    }

    /**
     * Singleton get-method
     * @return
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Singleton reset-method
     * (pentru rularea multor teste intr-un singur run)
     */
    public static void resetInstance() {
        instance = new Database();
    }

    public InputData getInputData() {
        return inputData;
    }

    /**
     * Seteaza vectorii din baza de date
     * @param inputData datele citite din fisier
     */
    public void setInputData(final InputData inputData) {
        this.inputData = inputData;
        this.consumers = new ArrayList<>(inputData.getInitialData().getConsumers());
        this.distributors = new ArrayList<>(inputData.getInitialData().getDistributors());
        this.producers = new ArrayList<>(inputData.getInitialData().getProducers());
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    public OutputData getOutput() {
        return output;
    }

    public void setOutput(final OutputData output) {
        this.output = output;
    }

    /**
     * Creaza cobiectul ce trebuie afisat la output
     */
    public void prepareOutput() {
        output.setConsumers(consumers);
        output.setDistributors(distributors);
        output.setEnergyProducers(producers);
    }

    /**
     * Parcurge vectorul de distribuitori si il intoarce pe cel cu
     * pretul contractului cel mai mic
     * @return
     */
    public Distributor getLowestPrice() {
        Distributor lowest = null;
//        System.out.println(distributors);
        for (Distributor distributor : distributors) {
            if (distributor.isBankrupt()) {
                continue;
            }
            if (lowest == null) {
                lowest = distributor;
                continue;
            }
            if (distributor.getBaseContract().getPrice() < lowest.getBaseContract().getPrice()) {
                lowest = distributor;
            }
        }
        return lowest;
    }

    public boolean areDistributorsBankrupt(){
        for (Distributor distributor : distributors){
            if (!distributor.isBankrupt()) {
                return false;
            }
        }
        return true;
    }


}
