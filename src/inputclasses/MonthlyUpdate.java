package inputclasses;

import baseclasses.Consumer;

import java.util.ArrayList;

public final class MonthlyUpdate {
    private ArrayList<Consumer> newConsumers;
    private ArrayList<DistributorChange> distributorChanges;
    private ArrayList<ProducerChange> producerChanges;

    public MonthlyUpdate() {
    }

    public MonthlyUpdate(final ArrayList<Consumer> newConsumers,
                         final ArrayList<DistributorChange> distributorChanges,
                         final ArrayList<ProducerChange> producerChanges) {
        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChange> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final ArrayList<DistributorChange> costsChanges) {
        this.distributorChanges = costsChanges;
    }

    public ArrayList<ProducerChange> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChange> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
