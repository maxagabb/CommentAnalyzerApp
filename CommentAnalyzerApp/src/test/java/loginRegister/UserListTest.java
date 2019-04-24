/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginRegister;

import business.User;
import java.io.File;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class UserListTest extends TestCase {
    
    public UserListTest(String testName) {
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
     * Test of validate method, of class UserList.
     */
    public void testValidate() {
        System.out.println("validate");
        ArrayList<String> inputinfo = new ArrayList();
        inputinfo.add("1");
        inputinfo.add("1");
        User user = new User(inputinfo);
        UserList instance = new UserList(new File("users.csv"));
        boolean expResult = true;
        boolean result = instance.validate(inputinfo);
        assertEquals(expResult, result);
    }
    
}
