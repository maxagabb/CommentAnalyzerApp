/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import commentsFrontEnd.CommentPanel;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class ContentPanelTest extends TestCase {
    
    public ContentPanelTest(String testName) {
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
     * Test of getPanelText method, of class ContentPanel.
     */
    public void testGetPanelText() {
        System.out.println("getPanelText");
        ContentPanel instance = new CommentPanel(new Comment("message"));
        String expResult = "message";
        String result = instance.getPanelText();
        assertEquals(expResult, result);
    }
    
}
