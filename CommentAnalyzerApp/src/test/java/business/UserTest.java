/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class UserTest extends TestCase {
    
    public UserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getName method, of class User.
     */
    public void testGetName() {
        System.out.println("getName");
        ArrayList<String> user = new ArrayList<String>();
        user.add("username");
        user.add("password");
        User instance = new User(user);
        String expResult = "username";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    public void testGetPassword() {
        System.out.println("getPassword");
        ArrayList<String> user = new ArrayList<String>();
        user.add("username");
        user.add("password");
        User instance = new User(user);
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class User.
     */
    public void testGetEmail() {
        System.out.println("getEmail");
        ArrayList<String> user = new ArrayList<String>();
        user.add("username");
        user.add("password");
        user.add("email");
        User instance = new User(user);
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class User.
     */
    public void testValidate() {
        System.out.println("validate");
        ArrayList<String> user = new ArrayList<String>();
        user.add("username");
        user.add("password");
        user.add("email");
        User instance = new User(user);
        boolean expResult = true;
        boolean result = instance.validate(new User(user));
        assertEquals(expResult, result);
    }
    
}
