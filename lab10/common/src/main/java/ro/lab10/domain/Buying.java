package ro.lab10.domain;

import java.util.Date;

public class Buying extends BaseEntity<Long> {
    private final Date buyDate;
    private final String review;

    public Buying(Long id, Date buyDate, String review) {
        super(id);
        this.buyDate = buyDate;
        this.review = review;
    }

    public Date getBuyDate() {
        // buyDate.toInstant().toEpochMilli();
        return buyDate;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Buying{" +
                "buyDate=" + buyDate +
                ", review='" + review + '\'' +
                '}';
    }
}
