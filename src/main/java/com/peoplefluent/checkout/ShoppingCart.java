package com.peoplefluent.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * ShoppingCart class that maintains the product catalog and provides a checkout function which is used to calculate the total price in Pounds
 */
public class ShoppingCart {

    public Map<String, BigDecimal> productCatalog;
    public static final String POUND = "\u00a3";

    public ShoppingCart() {
        initializeProductCatalog();
    }

    /**
     * This method is used to initialize the Product Catalog with the product as key and the price as the value
     */
    public void initializeProductCatalog() {
        productCatalog = new HashMap<String, BigDecimal>();
        productCatalog.put("Apple", new BigDecimal("0.60").setScale(2, RoundingMode.CEILING));
        productCatalog.put("Orange", new BigDecimal("0.25").setScale(2, RoundingMode.CEILING));
    }

    /**
     * This method takes the list of products as a String array and then calculates the total price utilizing the product catalog
     * @param products String array of products e.g; {"Apple", "Orange", "Apple"}
     * @return the total price in the following format
     */
    public String checkout(String[] products) {
        BigDecimal totalPrice = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);

        if (null != products && products.length > 0) {
            for (int i = 0; i < products.length; i++) {
                if (productCatalog.containsKey(products[i]))
                    totalPrice = totalPrice.add(productCatalog.get(products[i]));
            }
        }

        return POUND+totalPrice;
    }
}