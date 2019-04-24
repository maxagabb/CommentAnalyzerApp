/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import business.Video1;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author mgabb2015
 */
public class VideoRetrieverTest extends TestCase {
    
    public VideoRetrieverTest(String testName) {
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
     * Test of retrieve method, of class VideoRetriever.
     * @throws java.lang.Exception
     */
    public void testRetrieve() throws Exception {
        System.out.println("retrieve");
        String fieldInput = "soccor";
        VideoRetriever instance = new VideoRetriever();
        boolean expResult = false;
        ArrayList<Video1> result = instance.retrieve(fieldInput);
        assertEquals(expResult, result.isEmpty());
    }

    /**
     * Test of retrieveFromChannel method, of class VideoRetriever.
     * @throws java.lang.Exception
     */
    public void testRetrieveFromChannel() throws Exception {
        System.out.println("retrieveFromChannel");
        String fieldInput = "pewdiepie";
        VideoRetriever instance = new VideoRetriever();
        boolean expResult = false;
        ArrayList<Video1> result = instance.retrieveFromChannel(fieldInput);
        assertEquals(expResult, result.isEmpty());
    }

    /**
     * Test of getBannerURL method, of class VideoRetriever.
     * @throws java.io.IOException
     */
    public void testGetBannerURL() throws IOException {
        System.out.println("getBannerURL");
        VideoRetriever instance = new VideoRetriever();
        instance.retrieveFromChannel("pewdiepie");
        boolean expResult = false;
        String result = instance.getBannerURL();
        assertEquals(expResult, result.equals(null));
    }
}
