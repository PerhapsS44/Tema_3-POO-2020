package inputclasses;

import baseclasses.Consumer;
import baseclasses.Distributor;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;

    public InitialData() {
    }

    public InitialData(final ArrayList<Consumer> consumers,
                       final ArrayList<Distributor> distributors) {
        this.consumers = consumers;
        this.distributors = distributors;
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
}
