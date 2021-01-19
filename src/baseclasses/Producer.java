package baseclasses;

import entities.EnergyType;
import outputclasses.MonthlyStat;

import java.util.ArrayList;
import java.util.Comparator;

public class Producer extends Player{
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    private ArrayList<Distributor> distributors;
    private int noDistributors;

    private ArrayList<MonthlyStat> monthlyStats;

    public Producer(){
        distributors = new ArrayList<>();
        noDistributors = 0;
        monthlyStats = new ArrayList<>();
    }

    public Producer(int id,EnergyType energyType, int maxDistributors, double priceKW, int energyPerDistributor) {
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

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public ArrayList<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    public void setMonthlyStats(int month) {
        if (monthlyStats == null){
            monthlyStats = new ArrayList<>();
        }
        if (distributors == null){
            distributors = new ArrayList<>();
        }
        ArrayList<Long> distributorsIds = new ArrayList<>();
        for (Distributor distributor : distributors){
            distributorsIds.add(distributor.getId());
        }
        distributorsIds.sort(Comparator.comparingLong(d -> d));

        this.monthlyStats.add(new MonthlyStat(month+1, distributorsIds));
    }

    public void addDistributor(Distributor distributor){
        if (distributors == null){
            distributors = new ArrayList<>();
        }
        distributors.add(distributor);
        noDistributors++;
    }

    public void resetDistributors(){
        if (distributors == null){
            distributors = new ArrayList<>();
        }
        for (Distributor distributor : distributors){
            distributor.setNeedProdUpdate();
        }
        distributors = new ArrayList<>();
        noDistributors = 0;
    }

    public boolean isNotFull(){
        if (noDistributors < maxDistributors) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + getId() +
                ", energyType=" + energyType +
                ", maxDistributors=" + maxDistributors +
                ", priceKW=" + priceKW +
                ", energyPerDistributor=" + energyPerDistributor +
                ", noDistributors=" + noDistributors +
                ", monthlyStats=" + monthlyStats +
                '}';
    }

    public void removeDistributor(Distributor distributor) {
        if (distributors == null){
            distributors = new ArrayList<>();
        }
        if (distributors.remove(distributor)) {
            noDistributors--;
        }
    }
}
