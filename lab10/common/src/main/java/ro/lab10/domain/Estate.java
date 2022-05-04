package ro.lab10.domain;

public class Estate extends BaseEntity<Long> {
    private final String address;
    private final double surface;
    private final double price;

    public Estate(Long id, String address, double surface, double price) {
        super(id);
        this.address = address;
        this.surface = surface;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public double getSurface() {
        return surface;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Estate{" +
                "address='" + address + '\'' +
                ", surface=" + surface +
                ", price=" + price +
                '}';
    }
}
