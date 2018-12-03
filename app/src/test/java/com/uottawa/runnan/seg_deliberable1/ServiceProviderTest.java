package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.ServiceProvider;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceProviderTest {
    @Test
    public void checkAddress(){
        ServiceProvider sp = new ServiceProvider("171 lees ave", "6138995646", "","moving");
        assertEquals("Check the address of service provider", "171 lees ave", sp.get_address());
    }
    @Test
    public void checkPhoneNumber(){
        ServiceProvider sp = new ServiceProvider("171 lees ave", "6138995646", "","moving");
        assertEquals("check the phone number of the service provider", "6138995646", sp.get_phoneNumber());
    }

}