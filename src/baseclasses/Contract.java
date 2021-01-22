package baseclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Contract {

    private long remainedContractMonths;
    private long price;
    @JsonIgnore
    private Consumer client;
    @JsonIgnore
    private Distributor distributor;
    private long consumerId = -1;

    public Contract() {
    }

    public Contract(final long remainedContractMonths, final long price,
                    final Consumer client, final Distributor distributor) {
        this.remainedContractMonths = remainedContractMonths;
        this.price = price;
        this.client = client;
        if (client != null) {
            this.consumerId = client.getId();
        }
        this.distributor = distributor;
    }

    public Contract(final Contract contract) {
        this.price = contract.price;
        this.remainedContractMonths = contract.remainedContractMonths;
        this.client = contract.client;
        if (client != null) {
            this.consumerId = client.getId();
        }
        this.distributor = contract.distributor;
    }

    public long getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final long remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(final long price) {
        this.price = price;
    }

    public Consumer getClient() {
        return client;
    }

    /**
     * Seteaza pentru contractul curent consumatorul care l-a incheiat
     *
     * @param client
     */
    public void setClient(final Consumer client) {
        this.client = client;
        this.consumerId = client.getId();
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(final Distributor distributor) {
        this.distributor = distributor;
    }

    public long getConsumerId() {
        return consumerId;
    }

//    @Override
//    public String toString() {
//        return "Contract{" +
//                "remainedContractMonths=" + remainedContractMonths +
//                ", price=" + price +
//                ", distributorId=" + distributor.getId() +
//                ", consumerId=" + consumerId +
//                '}';
//    }
}
