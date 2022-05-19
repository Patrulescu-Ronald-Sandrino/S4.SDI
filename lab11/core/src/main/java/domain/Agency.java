package domain;

public class Agency extends BaseEntity<Long>{
    private final String name;
    private final String address;

    public Agency(Long id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public Agency(String name, String address) {
        this(0L, name, address);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
