package com.rodrigopeleias.products.domain;

import org.junit.Before;

import java.util.ArrayList;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        this.product = new Product();
        product.setName("Product");
        product.setDescription("DEscription");
        product.setImages(new ArrayList<>());
    }

    public void shoudAddImage() {

    }
}
