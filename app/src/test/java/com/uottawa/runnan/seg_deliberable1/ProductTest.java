package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.Product;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;


public class ProductTest {
    @Test
    public void checkProductName(){
        Product product = new Product("1", "Appliance install", 120);
        assertEquals("check the name of product", "Appliance install", product.getProductName());
    }

    @Test
    public void checkProductID(){
        Product product = new Product("1", "Appliance install", 120);
        assertEquals("check the id of product","1",product.getId());
    }




}