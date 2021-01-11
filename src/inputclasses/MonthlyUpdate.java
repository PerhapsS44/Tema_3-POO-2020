package inputclasses;

import baseclasses.Consumer;

import java.util.ArrayList;

public final class MonthlyUpdate {
    private ArrayList<Consumer> newConsumers;
    private ArrayList<CostChange> costsChanges;

    public MonthlyUpdate() {
    }

    public MonthlyUpdate(final ArrayList<Consumer> newConsumers,
                         final ArrayList<CostChange> costsChanges) {
        this.newConsumers = newConsumers;
        this.costsChanges = costsChanges;
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<CostChange> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final ArrayList<CostChange> costsChanges) {
        this.costsChanges = costsChanges;
    }

}
