package ro.lab10.domain;

public class Offer extends BaseEntity<Pair<Long, Long>> {
    private final double price;

    public Offer(Long agencyId, Long estateId, double price) {
        super(new Pair<>(agencyId, estateId));
        this.price = price;
    }

    public Long getAgencyId() {
        return id.getLeft();
    }

    public Long getEstateId() {
        return id.getRight();
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "agencyId=" + id.getLeft() +
                ", estateId=" + id.getRight() +
                ", price=" + price +
                '}';
    }
}
