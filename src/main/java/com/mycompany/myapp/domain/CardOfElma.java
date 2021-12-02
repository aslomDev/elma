package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.HUMO;
import com.mycompany.myapp.domain.enumeration.credit;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CardOfElma.
 */
@Table("card_of_elma")
public class CardOfElma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("pan")
    private String pan;

    @Column("masked_pan")
    private String maskedPan;

    @Column("type_card")
    private HUMO typeCard;

    @Column("card_type_elma")
    private credit cardTypeElma;

    @Column("expire_date")
    private LocalDate expireDate;

    @Column("balance")
    private BigDecimal balance;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CardOfElma id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPan() {
        return this.pan;
    }

    public CardOfElma pan(String pan) {
        this.setPan(pan);
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getMaskedPan() {
        return this.maskedPan;
    }

    public CardOfElma maskedPan(String maskedPan) {
        this.setMaskedPan(maskedPan);
        return this;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public HUMO getTypeCard() {
        return this.typeCard;
    }

    public CardOfElma typeCard(HUMO typeCard) {
        this.setTypeCard(typeCard);
        return this;
    }

    public void setTypeCard(HUMO typeCard) {
        this.typeCard = typeCard;
    }

    public credit getCardTypeElma() {
        return this.cardTypeElma;
    }

    public CardOfElma cardTypeElma(credit cardTypeElma) {
        this.setCardTypeElma(cardTypeElma);
        return this;
    }

    public void setCardTypeElma(credit cardTypeElma) {
        this.cardTypeElma = cardTypeElma;
    }

    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    public CardOfElma expireDate(LocalDate expireDate) {
        this.setExpireDate(expireDate);
        return this;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public CardOfElma balance(BigDecimal balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance != null ? balance.stripTrailingZeros() : null;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardOfElma)) {
            return false;
        }
        return id != null && id.equals(((CardOfElma) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CardOfElma{" +
            "id=" + getId() +
            ", pan='" + getPan() + "'" +
            ", maskedPan='" + getMaskedPan() + "'" +
            ", typeCard='" + getTypeCard() + "'" +
            ", cardTypeElma='" + getCardTypeElma() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", balance=" + getBalance() +
            "}";
    }
}
