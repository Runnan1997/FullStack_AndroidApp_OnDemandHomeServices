package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.ServiceProvider;

import org.junit.Test;

import static org.junit.Assert.*;

public class SP_profileTest {

    @Test
    public void phonenumValidation() {
        ServiceProvider sp = new ServiceProvider("171 lees ave", "6138995646", "","moving");
        String pattern ="^[+]?[0-9]{10,13}$";
        assertEquals("phone number validation", true, sp.get_phoneNumber().matches(pattern));
    }
}