/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentsFrontEnd;

import business.Comment;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class CommentPanelTest extends TestCase {
    
    public CommentPanelTest(String testName) {
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
     * Test of contains method, of class CommentPanel.
     */
    public void testContains() {
        System.out.println("contains");
        String keyword = "message";
        CommentPanel instance = new CommentPanel(new Comment("message"));
        boolean expResult = true;
        boolean result = instance.contains(keyword);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class CommentPanel.
     */
    public void testClone() {
        System.out.println("clone");
        CommentPanel instance = new CommentPanel(new Comment("message"));
        CommentPanel expResult = new CommentPanel(new Comment("message"));
        CommentPanel result = instance.clone();
        assertEquals(expResult.getPanelText(),result.getPanelText());
    }
    
}
