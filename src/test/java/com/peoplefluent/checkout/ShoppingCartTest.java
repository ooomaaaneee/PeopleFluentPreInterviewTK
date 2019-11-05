package com.peoplefluent.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTest {

    ShoppingCart shoppingCart;

    @BeforeEach
    public void initialize() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testWithNullOrEmptyInputShouldReturnZero() {
        String[] products = null;
        String totalPrice = shoppingCart.checkout(null);
        assertEquals(shoppingCart.POUND+"0.00",totalPrice);
    }

    @Test
    public void testWithOnlyApples() {
        String[] products = {"Apple", "Apple", "Apple"};
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(shoppingCart.POUND+"1.80",totalPrice);
    }

    @Test
    public void testWithOnlyOranges() {
        String[] products = {"Orange", "Orange", "Orange"};
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(shoppingCart.POUND+"0.75",totalPrice);
    }

    @Test
    public void testWithApplesAndOranges() {
        String[] products = {"Orange", "Orange", "Apple", "Orange", "Apple"};
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(shoppingCart.POUND+"1.95",totalPrice);
    }

    @Test
    public void testWithInvalidProducts() {
        String[] products = {"Orange", "Orange", "Mango", "Orange", "Apple", "PineApple"};
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(shoppingCart.POUND+"1.35",totalPrice);
    }
}
