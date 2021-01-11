package baseclasses;

public class Player {
    private long id;
    private long initialBudget;
    private boolean isBankrupt = false;

    public Player() {
    }

    public  Player (final int id){
        this.id = id;
    }

    public Player(final int id, final long initialBudget) {
        this.id = id;
        this.initialBudget = initialBudget;
    }

    public final long getId() {
        return id;
    }

    public final void setId(final long id) {
        this.id = id;
    }

    public final long getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final long initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
