/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byChannelFrontEnd;

import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class Channel1Test extends TestCase {
    
    public Channel1Test(String testName) {
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
     * Test of getID method, of class Channel1.
     */
    public void testGetID() {
        System.out.println("getID");
        Channel1 instance = new Channel1("message");
        String expResult = null;
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getthumbnailURL method, of class Channel1.
     */
    public void testGetthumbnailURL() {
        System.out.println("getthumbnailURL");
        Channel1 instance = new Channel1("message");
        String expResult = "iconfinderSignError299045.png";
        String result = instance.getthumbnailURL();
        assertEquals(expResult, result);
    }
    
}
