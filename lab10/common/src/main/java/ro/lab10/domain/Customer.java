package ro.lab10.domain;

public class Customer extends BaseEntity<Long> {
    private final String name;
    private final String email;

    public Customer(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this(0L, name, email);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
