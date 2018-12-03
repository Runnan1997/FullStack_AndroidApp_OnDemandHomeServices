package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.Rate;

import org.junit.Test;

import static org.junit.Assert.*;

public class RateTest {

    @Test
    public void getId() {
        Rate r = new Rate ("asdf", "Chris", 5, "great");
        assertEquals("check the id", "asdf", r.getId());
    }

    @Test
    public void getSpName() {
        Rate r = new Rate ("asdf", "Chris", 5, "great");
        assertEquals("check the name", "Chris", r.getSpName());
    }

    @Test
    public void getRating() {
        Rate r = new Rate ("asdf", "Chris", 5, "great");
        assertEquals("check the rating", 5, r.getRating());
    }

    @Test
    public void getComment() {
        Rate r = new Rate ("asdf", "Chris", 5, "great");
        assertEquals("check the comment", "great", r.getComment());
    }
}