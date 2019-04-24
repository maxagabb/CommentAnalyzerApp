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
public class ChannelPanelTest extends TestCase {
    
    public ChannelPanelTest(String testName) {
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
     * Test of getChannelID method, of class ChannelPanel.
     */
    public void testGetChannelID() {
        System.out.println("getChannelID");
        ChannelPanel instance = new ChannelPanel(new Channel1("message"));
        String expResult = null;
        String result = instance.getChannelID();
        assertEquals(expResult, result);
    }
    
}
