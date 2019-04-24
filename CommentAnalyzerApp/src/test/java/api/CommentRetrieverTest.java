/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import business.Comment;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class CommentRetrieverTest extends TestCase {
    
    public CommentRetrieverTest(String testName) {
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
     * Test of retrieve method, of class CommentRetriever.
     */
    public void testRetrieve() throws Exception {
        System.out.println("retrieve");
        String fieldInput = "an invalid ID";
        CommentRetriever instance = new CommentRetriever();
        boolean expResult = false;
        ArrayList<Comment> result = instance.retrieve(fieldInput);
        assertEquals(expResult, result.isEmpty());
    }
}
