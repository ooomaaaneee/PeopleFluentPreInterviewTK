package com.peoplefluent.checkout;

/**
 * This class is used to provide the main method that can be used to test the functionality of the ShoppingCart class
 */
public class ShoppingCartMain {
    /**
     * This method takes the products as program arguments and then lists out the total price
     * @param products The products are provided as a String array e.g., {"Apple", "Orange"}
     */
    public static void main(String[] products) {
        System.out.println("Welcome to the shopping cart application!!!");

        if(null == products || products.length <= 0) {
            System.out.println("Please provide valid products as input");
        } else {
            //Shopping Cart without offer
            ShoppingCart shoppingCart = new ShoppingCart();
            String totalPrice = shoppingCart.checkout(products);
            System.out.println("Total price of the products in the basket: " + totalPrice);

            //Shopping Cart with offer
            ShoppingCart shoppingCartWithOffer = new ShoppingCart(true, true);
            String totalPriceWithOffer = shoppingCartWithOffer.checkout(products);
            System.out.println("Total price of the products in the basket with OFFER: " + totalPriceWithOffer);
        }

        System.out.println("Exiting the shopping cart application...");
    }
}
