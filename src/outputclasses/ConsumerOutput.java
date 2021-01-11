package outputclasses;

import baseclasses.Consumer;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ConsumerOutput {
    private long id;

    private boolean isBankrupt;
    private long budget;

    public ConsumerOutput() {
    }

    public ConsumerOutput(final Consumer consumer) {
        this.id = consumer.getId();
        this.isBankrupt = consumer.isBankrupt();
        this.budget = consumer.getInitialBudget();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }
}
