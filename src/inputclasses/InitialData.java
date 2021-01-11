package inputclasses;

import baseclasses.Consumer;
import baseclasses.Distributor;
import baseclasses.Producer;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;

    public InitialData() {
    }

    public InitialData(final ArrayList<Consumer> consumers,
                       final ArrayList<Distributor> distributors,
                       final ArrayList<Producer> producers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
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
}
