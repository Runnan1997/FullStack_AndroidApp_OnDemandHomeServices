package com.uottawa.runnan.seg_deliberable1;

import com.uottawa.runnan.seg_deliberable1.Model.User;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void checkUserName(){
        User user = new User("Runnan", "1234","rguo100@gmail.ca","Administrator");
        assertEquals("check the name of user", "Runnan",user.getUsername());
    }

    public void checkUserPassword(){
        User user = new User("Runnan", "1234","rguo100@gmail.ca","Administrator");
        assertEquals("check the password of user", "1234",user.getPassword());
    }

    public void checjUserRole(){
        User user = new User("Runnan", "1234","rguo100@gmail.ca","Administrator");
        assertEquals("check the role of user", "Administrator", user.getRole());
    }
}