package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.BookedService;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookedServiceTest {

    @Test
    public void getHoName() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        assertEquals("check the name of the homwowner", "Zack",bs.getHoName());
    }

    @Test
    public void setHoName() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        bs.setHoName("BiuBiu");
        assertEquals("check the name of the homwowner", "BiuBiu",bs.getHoName());

    }

    @Test
    public void getSpName() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        assertEquals("check the name of the serviceprovider", "Chris",bs.getSpName());
    }

    @Test
    public void setSpName() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        bs.setSpName("BiuBiu");
        assertEquals("check the name of the serviceprovider", "BiuBiu",bs.getSpName());
    }


    @Test
    public void getId() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        assertEquals("check the id", "asdf",bs.getId());
    }

    @Test
    public void setId() {
        BookedService bs = new BookedService("Zack", "Chris", "asdf");
        bs.setId("ghjk");
        assertEquals("check the id", "ghjk",bs.getId());
    }
}