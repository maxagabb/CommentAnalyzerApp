/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byVideoFrontEnd;

import business.Video1;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class VideoPanelTest extends TestCase {
    
    public VideoPanelTest(String testName) {
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
     * Test of getVideoID method, of class VideoPanel.
     */
    public void testGetVideoID() {
        System.out.println("getVideoID");
        VideoPanel instance = new VideoPanel(new Video1("message"));
        String expResult = null;
        String result = instance.getVideoID();
        assertEquals(expResult, result);;
    }
    
}
