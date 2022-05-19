package ro.lab11.domain;

public class Estate extends BaseEntity<Long> {
    private final String address;
    private final double surface;

    public Estate(Long id, String address, double surface) {
        super(id);
        this.address = address;
        this.surface = surface;
    }

    public Estate(String address, double surface) {
        this(0L, address, surface);
    }

    public String getAddress() {
        return address;
    }

    public double getSurface() {
        return surface;
    }

    @Override
    public String toString() {
        return "Estate{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", surface=" + surface +
                '}';
    }
}
