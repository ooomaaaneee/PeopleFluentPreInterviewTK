package com.peoplefluent.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * ShoppingCart class that maintains the product catalog and provides a checkout function which is used to calculate the total price in Pounds
 */
public class ShoppingCart {

    public Map<String, Product> productCatalog;
    public static final String POUND = "\u00a3";

    /**
     * Constructor to initialize the Shopping Cart with the appropriate Product Catalog and Offers
     * Note: For enterprise grade applications, this offer flag can be provided using properties flag
     * @param appleOfferEnabled - If offer for Apples is enabled
     * @param orangeOfferEnabled - If offer for Oranges is enabled
     */
    public ShoppingCart(boolean appleOfferEnabled, boolean orangeOfferEnabled) {
        initializeProductCatalog(appleOfferEnabled, orangeOfferEnabled);
    }

    /**
     * Default constructor initializes the product catalog with no offers
     */
    public ShoppingCart() {
        initializeProductCatalog(false, false);
    }

    /**
     * This method is used to initialize the Product Catalog with the product as key and the price as the value.
     * Note: For enterprise grade applications, the product catalog can be loaded from a cache or from DB
     */
    public void initializeProductCatalog(boolean appleOfferEnabled, boolean orangeOfferEnabled) {
        productCatalog = new HashMap<String, Product>();

        Product apple = new Product();
        apple.setPrice(new BigDecimal("0.60").setScale(2, RoundingMode.CEILING));
        apple.setOfferEnabled(appleOfferEnabled);

        Product orange = new Product();
        orange.setPrice(new BigDecimal("0.25").setScale(2, RoundingMode.CEILING));
        orange.setOfferEnabled(orangeOfferEnabled);

        productCatalog.put("Apple", apple);
        productCatalog.put("Orange", orange);
    }

    /**
     * This method takes the list of products as a String array and then calculates the total price based on offers
     * @param products String array of products e.g; {"Apple", "Orange", "Apple"}
     * @return the total price in String format
     */
    public String checkout(String[] products) {
        BigDecimal totalPrice = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);
        BigDecimal totalApples = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);
        BigDecimal totalOranges = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);

        if (null != products && products.length > 0) {
            //Loop to calculate the Total Apples and Oranges
            for (int i = 0; i < products.length; i++) {
                if (productCatalog.containsKey(products[i])) {
                    if("Apple".equalsIgnoreCase(products[i])) {
                        totalApples = totalApples.add(new BigDecimal("1.00").setScale(2, RoundingMode.CEILING));
                    } else if("Orange".equalsIgnoreCase(products[i])) {
                        totalOranges = totalOranges.add(new BigDecimal("1.00").setScale(2, RoundingMode.CEILING));
                    }
                }
            }

            //Calculate the total price of Apples in the shopping cart
            BigDecimal priceOfApple = productCatalog.get("Apple").getPrice();
            boolean offerForAppleEnabled = productCatalog.get("Apple").isOfferEnabled();
            BigDecimal totalPriceOfApples = priceOfApple.multiply(totalApples).setScale(2, RoundingMode.CEILING);

            //Apply the buy 1 get 1 free offer for Apples
            if(offerForAppleEnabled) {
                BigDecimal buyOneGetOneFreeFactor = totalApples.divide(new BigDecimal("2.00"), 2, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
                BigDecimal discountForApples = priceOfApple.multiply(buyOneGetOneFreeFactor).setScale(2, RoundingMode.CEILING);

                totalPriceOfApples = totalPriceOfApples.subtract(discountForApples).setScale(2, RoundingMode.CEILING);
            }

            //Calculate the total price of Oranges in the shopping cart
            BigDecimal priceOfOrange = productCatalog.get("Orange").getPrice();
            boolean offerForOrangeEnabled = productCatalog.get("Orange").isOfferEnabled();
            BigDecimal totalPriceOfOranges = priceOfOrange.multiply(totalOranges).setScale(2, RoundingMode.CEILING);

            //Apply the buy 3 for the price of 2 offer for Oranges
            if(offerForOrangeEnabled) {
                BigDecimal buyThreeForThePriceOfTwoFactor = totalOranges.divide(new BigDecimal("3.00"), 2, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
                BigDecimal discountForOranges = priceOfOrange.multiply(buyThreeForThePriceOfTwoFactor).setScale(2, RoundingMode.CEILING);

                totalPriceOfOranges = totalPriceOfOranges.subtract(discountForOranges).setScale(2, RoundingMode.CEILING);
            }

            totalPrice = totalPriceOfApples.add(totalPriceOfOranges).setScale(2, RoundingMode.CEILING);
        }

        return POUND+totalPrice;
    }
}