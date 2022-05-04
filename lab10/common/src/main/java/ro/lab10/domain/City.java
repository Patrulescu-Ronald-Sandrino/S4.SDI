package ro.lab10.domain;

public class City extends BaseEntity<Long> {
    private final String name;
    private final String country;
    private final double costOfLiving;

    public City(Long id, String name, String country, double costOfLiving) {
        super(id);
        this.name = name;
        this.country = country;
        this.costOfLiving = costOfLiving;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getCostOfLiving() {
        return costOfLiving;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", costOfLiving=" + costOfLiving +
                '}';
    }
}
