package ro.lab10.domain;

public class Customer extends BaseEntity<Long> {
    private final String name;
    private final String email;

    public Customer(Long aLong, String name, String email) {
        super(aLong);
        this.name = name;
        this.email = email;
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
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
