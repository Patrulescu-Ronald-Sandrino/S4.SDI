package ro.lab11.domain;

import java.io.Serializable;

public class OfferPK implements Serializable {
    private final Long agencyId;
    private final Long estateId;

    public OfferPK(Long agencyId, Long estateId) {
        this.agencyId = agencyId;
        this.estateId = estateId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public Long getEstateId() {
        return estateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferPK offerPK = (OfferPK) o;

        if (!agencyId.equals(offerPK.agencyId)) return false;
        return estateId.equals(offerPK.estateId);
    }

    @Override
    public int hashCode() {
        int result = agencyId.hashCode();
        result = 31 * result + estateId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OfferPK{" +
                "agencyId=" + agencyId +
                ", estateId=" + estateId +
                '}';
    }
}
