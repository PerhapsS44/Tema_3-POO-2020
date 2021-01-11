package outputclasses;

import baseclasses.Contract;
import baseclasses.Distributor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class DistributorOutput {
    private long id;
    private long budget;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts;


    public DistributorOutput() {
    }

    public DistributorOutput(final Distributor distributor) {
        this.id = distributor.getId();
        this.isBankrupt = distributor.isBankrupt();
        this.budget = distributor.getInitialBudget();
        this.contracts = distributor.getContracts();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }

    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}
