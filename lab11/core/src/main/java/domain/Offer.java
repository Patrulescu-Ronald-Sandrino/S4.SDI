package domain;

public class Offer extends BaseEntity<OfferPK> {
    private final double price;

    public Offer(Long agencyId, Long estateId, double price) {
        super(new OfferPK(agencyId, estateId));
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
