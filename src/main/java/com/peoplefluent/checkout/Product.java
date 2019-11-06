package com.peoplefluent.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private boolean offerEnabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                offerEnabled == product.offerEnabled &&
                name.equals(product.name) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, offerEnabled);
    }
}
