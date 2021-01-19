package Utils;

import baseclasses.Producer;

import java.util.ArrayList;

public class SortedProducers {
    private ArrayList<Producer> sortedGreenProducers;
    private ArrayList<Producer> sortedPriceProducers;
    private ArrayList<Producer> sortedQuantityProducers;

    public SortedProducers(ArrayList<Producer> producers){
        sortedGreenProducers = new ArrayList<>(producers);
        sortedPriceProducers = new ArrayList<>(producers);
        sortedQuantityProducers = new ArrayList<>(producers);

        sortedGreenProducers.sort((p1,p2) -> {
            if (p1.getEnergyType().isRenewable() == p2.getEnergyType().isRenewable()){
                if (p1.getPriceKW() == p2.getPriceKW()){
                    if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()){
                        return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
                    }
                    return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
                }
                return Double.compare(p1.getPriceKW(), p2.getPriceKW());
            }
            else {
                if (p1.getEnergyType().isRenewable()){
                    return -1;
                }
                return 1;
            }
        });

        sortedPriceProducers.sort((p1, p2) -> {
            if (p1.getPriceKW() == p2.getPriceKW()){
                if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()){
                    return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
                }
                return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
            }
            return Double.compare(p1.getPriceKW(), p2.getPriceKW());
        });

        sortedQuantityProducers.sort((p1, p2) -> {
            if (p1.getEnergyPerDistributor() == p2.getEnergyPerDistributor()){
                return Integer.compare(producers.indexOf(p1), producers.indexOf(p2));
            }
            return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
        });

//        System.out.println(sortedGreenProducers);
//        System.out.println("==============================");
//        System.out.println(sortedPriceProducers);
//        System.out.println("==============================");
//        System.out.println(sortedQuantityProducers);
//        System.out.println("==============================");
//        System.out.println();
//        System.out.println();
    }

    public ArrayList<Producer> getSortedGreenProducers() {
        return sortedGreenProducers;
    }

    public void setSortedGreenProducers(ArrayList<Producer> sortedGreenProducers) {
        this.sortedGreenProducers = sortedGreenProducers;
    }

    public ArrayList<Producer> getSortedPriceProducers() {
        return sortedPriceProducers;
    }

    public void setSortedPriceProducers(ArrayList<Producer> sortedPriceProducers) {
        this.sortedPriceProducers = sortedPriceProducers;
    }

    public ArrayList<Producer> getSortedQuantityProducers() {
        return sortedQuantityProducers;
    }

    public void setSortedQuantityProducers(ArrayList<Producer> sortedQuantityProducers) {
        this.sortedQuantityProducers = sortedQuantityProducers;
    }
}
