/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import byChannelFrontEnd.Channel1;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class ChannelRetrieverTest extends TestCase {
    
    public ChannelRetrieverTest(String testName) {
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
     * Test of retrieve method, of class ChannelRetriever.
     */
    public void testRetrieve() throws Exception {
        System.out.println("retrieve");
        String fieldInput = "pewdiepie";
        ChannelRetriever instance = new ChannelRetriever();
        boolean expResult = false;
        ArrayList<Channel1> result = instance.retrieve(fieldInput);
        assertEquals(expResult, result.isEmpty());
    }
    
}
