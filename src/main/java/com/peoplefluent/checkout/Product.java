package com.peoplefluent.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private BigDecimal price;
    private boolean offerEnabled;

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.CEILING);
    }

    public void setOfferEnabled(boolean offerEnabled) {
        this.offerEnabled = offerEnabled;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isOfferEnabled() {
        return offerEnabled;
    }
}
