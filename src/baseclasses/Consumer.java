package baseclasses;

import constants.Constants;

public final class Consumer extends Player {
    private long monthlyIncome;
    private boolean inDebt = false;
    private long oldTaxes = 0;
    private Distributor oldDistribuitor;
    private Contract currentContract;

    public Consumer() {
    }

    public Consumer(final int id, final long initialBudget, final long monthlyIncome) {
        super(id, initialBudget);
        this.monthlyIncome = monthlyIncome;
        this.currentContract = null;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public boolean isInDebt() {
        return inDebt;
    }

    public void setInDebt(final boolean inDebt) {
        this.inDebt = inDebt;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(final Contract currentContract) {
        this.currentContract = currentContract;
    }

    /**
     * Un consumator primeste monthlyIncome
     */
    public void getSalary() {
        this.setInitialBudget(this.getInitialBudget() + monthlyIncome);
    }

    /**
     * Un consumator plateste taxele catre dsitribuitor(i)
     */
    public void payTaxes() {
        if (!inDebt) {
            if (getInitialBudget() - currentContract.getPrice() >= 0) {
                setInitialBudget(getInitialBudget() - currentContract.getPrice());
                currentContract.getDistributor().getPayment(currentContract.getPrice());
            } else {
                inDebt = true;
                oldTaxes = Math.round(Math.floor(currentContract.getPrice()
                        * Constants.DEBT_PERCENT));
                oldDistribuitor = currentContract.getDistributor();
            }
        } else {
            if (getInitialBudget() - currentContract.getPrice() - oldTaxes >= 0) {
                setInitialBudget(getInitialBudget() - currentContract.getPrice() - oldTaxes);
                currentContract.getDistributor().getPayment(currentContract.getPrice());
                oldDistribuitor.getPayment(oldTaxes);
                inDebt = false;
            } else {
                this.setBankrupt(true);
            }
        }
        currentContract.setRemainedContractMonths(currentContract.getRemainedContractMonths() - 1);
    }

//    @Override
//    public String toString() {
//        return "Consumer{" +
//                "budget=" + getInitialBudget() +
//                ", monthlyIncome=" + monthlyIncome +
//                ", inDebt=" + inDebt +
//                ", oldTaxes=" + oldTaxes +
//                ", oldDistribuitor=" + oldDistribuitor +
//                ", currentContract=" + currentContract +
//                '}';
//    }
}
