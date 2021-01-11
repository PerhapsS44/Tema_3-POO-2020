package baseclasses;

import constants.Constants;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class Distributor extends Player {
    private long contractLength;
    private long initialInfrastructureCost;
    private long initialProductionCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private Contract baseContract;

    private ArrayList<Contract> contracts;

    private long expenses;

    public Distributor() {
    }

    public Distributor(final int id, final int initialBudget, final int contractLength,
                       final int initialInfrastructureCost, final int energyNeededKW,
                       final EnergyChoiceStrategyType producerStrategy) {
        super(id, initialBudget);
        this.contractLength = contractLength;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        contracts = new ArrayList<>();
    }

    public long getContractLength() {
        return contractLength;
    }

    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    public long getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final long initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public long getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(final long initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public Contract getBaseContract() {
        return baseContract;
    }

    public void setBaseContract(final Contract baseContract) {
        this.baseContract = baseContract;
    }

    private long contractsSize() {
        long contor = 0;
        for (Contract contract : contracts) {
            if (!contract.getClient().isBankrupt()) {
                contor++;
            }
        }
        return contor;
    }

    /**
     * (Re)Calculeaza pretul unui contract pentru situatia in care un consumator
     * vrea sa isi faca un contract in aceasta luna
     * Calculul se realizeaza la inceputul fiecarei luni
     */
    public void createBaseContract() {
        long price = 0;
        long profit = Math.round(Math.floor(Constants.PROFIT_PERCENT * initialProductionCost));

        long noContracts = contractsSize();
        if (noContracts != 0) {
            price = Math.round(Math.floor((double) (initialInfrastructureCost / noContracts))
                    + initialProductionCost + profit);
        } else {
            price = initialInfrastructureCost + initialProductionCost + profit;
        }
        if (baseContract == null) {
            baseContract = new Contract(contractLength, price, null, this);
        } else {
            baseContract.setPrice(price);
        }
    }

    /**
     * Se calculeaza taxele ce trebuiesc platite de catre un distribuitor
     */
    public void updateExpenses() {
        expenses = initialInfrastructureCost + initialProductionCost * contracts.size();
    }

    /**
     * Se adauga la buget taxele colectate de la un consumator
     * @param income
     */
    public void getPayment(final long income) {
        this.setInitialBudget(this.getInitialBudget() + income);
    }

    /**
     * Se platesc taxele si, daca nu si le permite, distribuitorul devine
     * bankrupt
     */
    public void payTaxes() {
        this.setInitialBudget(this.getInitialBudget() - expenses);
        if (this.getInitialBudget() < 0) {
            this.setBankrupt(true);
            for (Contract contract : contracts) {
                // sa mut consumatorii la altul
                contract.getClient().setCurrentContract(null);
            }
        }
    }
}
