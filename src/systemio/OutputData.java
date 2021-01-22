package systemio;

import baseclasses.Consumer;
import baseclasses.Distributor;
import baseclasses.Producer;
import outputclasses.ConsumerOutput;
import outputclasses.DistributorOutput;
import outputclasses.ProducerOutput;

import java.util.ArrayList;

public final class OutputData {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;
    private ArrayList<ProducerOutput> energyProducers;

    public OutputData() {
    }

    public OutputData(final ArrayList<ConsumerOutput> consumers,
                      final ArrayList<DistributorOutput> distributors,
                      final ArrayList<ProducerOutput> energyProducers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    /**
     * Creeaza un vector de ConsumerOutput dintr-un vector de Consumer
     *
     * @param consumers
     */
    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = new ArrayList<>();
        for (Consumer c : consumers) {
            this.consumers.add(new ConsumerOutput(c));
        }
    }

    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    /**
     * Creeaza un vector de DistributorOutput dintr-un vector de Distributor
     *
     * @param distributors
     */
    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = new ArrayList<>();
        for (Distributor d : distributors) {
            this.distributors.add(new DistributorOutput(d));
        }
    }

    public ArrayList<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }

    /**
     * Creeaza un vector de ProducerOutput dintr-un vector de Producer
     *
     * @param energyProducers
     */
    public void setEnergyProducers(ArrayList<Producer> energyProducers) {
        this.energyProducers = new ArrayList<>();
        for (Producer p : energyProducers) {
            this.energyProducers.add(new ProducerOutput(p));
        }
    }
}
