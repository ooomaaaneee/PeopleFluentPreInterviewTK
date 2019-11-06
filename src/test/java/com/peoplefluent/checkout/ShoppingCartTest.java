package com.peoplefluent.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTest {

    ShoppingCart shoppingCart;

    @Test
    public void testWithNullOrEmptyInputShouldReturnZeroForNoOffer() {
        String[] products = null;
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(null);
        assertEquals(ShoppingCart.POUND+"0.00",totalPrice);
    }

    @Test
    public void testWithOnlyApplesNoOffer() {

        String[] products = {"Apple", "Apple", "Apple"};
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.80",totalPrice);
    }

    @Test
    public void testWithOnlyOrangesNoOffer() {
        String[] products = {"Orange", "Orange", "Orange"};
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"0.75",totalPrice);
    }

    @Test
    public void testWithApplesAndOrangesNoOffer() {
        String[] products = {"Orange", "Orange", "Apple", "Orange", "Apple"};
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.95",totalPrice);
    }

    @Test
    public void testWithInvalidProductsNoOffer() {
        String[] products = {"Orange", "Orange", "Mango", "Orange", "Apple", "PineApple"};
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.35",totalPrice);
    }

    @Test
    public void testWithLargeBasketWithNoOffer() {
        String[] products = {"Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple"};
        shoppingCart = new ShoppingCart(false, false);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"5.85",totalPrice);
    }

    @Test
    public void testWithNullOrEmptyInputShouldReturnZeroForWithOffer() {
        String[] products = null;
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(null);
        assertEquals(ShoppingCart.POUND+"0.00",totalPrice);
    }

    @Test
    public void testWithOnlyApplesWithOffer() {

        String[] products = {"Apple", "Apple", "Apple", "Apple"};
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.20",totalPrice);
    }

    @Test
    public void testWithOnlyOrangesWithOffer() {
        String[] products = {"Orange", "Orange", "Orange", "Orange"};
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"0.75",totalPrice);
    }

    @Test
    public void testWithApplesAndOrangesWithOffer() {
        String[] products = {"Orange", "Orange", "Apple", "Orange", "Apple", "Orange"};
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.35",totalPrice);
    }

    @Test
    public void testWithInvalidProductsWithOffer() {
        String[] products = {"Orange", "Orange", "Mango", "Orange", "Apple", "Apple", "PineApple"};
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"1.10",totalPrice);
    }

    @Test
    public void testWithLargeBasketOfProductsWithOffer() {
        String[] products = {"Orange", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Apple", "Apple"};
        shoppingCart = new ShoppingCart(true, true);
        String totalPrice = shoppingCart.checkout(products);
        assertEquals(ShoppingCart.POUND+"3.30",totalPrice);
    }

}
