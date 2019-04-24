/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class Video1Test extends TestCase {
    
    public Video1Test(String testName) {
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
     * Test of getID method, of class Video1.
     */
    public void testGetID() {
        System.out.println("getID");
        Video1 instance = new Video1("message");
        String expResult = null;
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getthumbnailURL method, of class Video1.
     */
    public void testGetthumbnailURL() {
        System.out.println("getthumbnailURL");
        Video1 instance = new Video1("message");
        String expResult = "iconfinderSignError299045.png";
        String result = instance.getthumbnailURL();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Video1.
     */
    public void testToString() {
        System.out.println("toString");
        Video1 instance = new Video1("message");
        String expResult = "no videoID, message";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
