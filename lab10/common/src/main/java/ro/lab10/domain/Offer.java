package ro.lab10.domain;

public class Offer {
    private final Long agencyId;
    private final Long estateId;
    private final double price;

    public Offer(Long agencyId, Long estateId, double price) {
        this.agencyId = agencyId;
        this.estateId = estateId;
        this.price = price;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public Long getEstateId() {
        return estateId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "agencyId=" + agencyId +
                ", estateId=" + estateId +
                ", price=" + price +
                '}';
    }
}
