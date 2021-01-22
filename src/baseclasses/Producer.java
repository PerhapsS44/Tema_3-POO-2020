package baseclasses;

import entities.EnergyType;
import outputclasses.MonthlyStat;

import java.util.ArrayList;
import java.util.Comparator;

public final class Producer extends Player {
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    private ArrayList<Distributor> distributors;
    private int noDistributors;

    private ArrayList<MonthlyStat> monthlyStats;

    public Producer() {
        distributors = new ArrayList<>();
        noDistributors = 0;
        monthlyStats = new ArrayList<>();
    }

    public Producer(final int id, EnergyType energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        super(id);
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        distributors = new ArrayList<>();
        noDistributors = 0;
        monthlyStats = new ArrayList<>();
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * fac set normal, doar ca apelez si Distribuitor::setNeedProdUpdate, adica apelez updateState
     * pentur fiecare observator
     *
     * @param energyPerDistributor
     */
    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
        distributors.forEach(Distributor::setNeedProdUpdate);
    }

    public ArrayList<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * Creez monthlyStats pt ca se cere in enunt
     *
     * @param month
     */
    public void setMonthlyStats(final int month) {
        if (monthlyStats == null) {
            monthlyStats = new ArrayList<>();
        }
        if (distributors == null) {
            distributors = new ArrayList<>();
        }
        ArrayList<Long> distributorsIds = new ArrayList<>();
        for (Distributor distributor : distributors) {
            distributorsIds.add(distributor.getId());
        }
        distributorsIds.sort(Comparator.comparingLong(d -> d));

        this.monthlyStats.add(new MonthlyStat(month + 1, distributorsIds));
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    /**
     * adaug un distribuitor la lista de distribuitori
     * imi trebuie ca sa fac monthlyStats, dar si pentru mecanismul de updateState
     *
     * @param distributor
     */
    public void addDistributor(final Distributor distributor) {
        if (distributors == null) {
            distributors = new ArrayList<>();
        }
        distributors.add(distributor);
        noDistributors++;
    }

    /**
     * verifica daca mai este loc pentru distribuitori
     *
     * @return
     */
    public boolean isNotFull() {
        return noDistributors < maxDistributors;
    }

    /**
     * Stergem 'distribuitor' din lista de distribuitori
     *
     * @param distributor
     */
    public void removeDistributor(Distributor distributor) {
        if (distributors == null) {
            distributors = new ArrayList<>();
        }
        if (distributors.remove(distributor)) {
            noDistributors--;
        }
    }
}
