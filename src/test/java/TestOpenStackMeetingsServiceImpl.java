/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class TestOpenStackMeetingsServiceImpl {

	OpenStackMeetingsServiceImpl osmService = null;
	OpenStackMeetingsServiceImpl mockOsmService = mock(OpenStackMeetingsServiceImpl.class);
	
	@Before
	public void setUp() {
		osmService = new OpenStackMeetingsServiceImpl();
	}
	
	@Test
	public void testWelcome() { // test welcome call
		String welcome = osmService.welcome();
		assertEquals("Welcome to Open Stack meeting statistics calculation page. " + 
        		"Please provide project and year as query parameters.", welcome);
	}
	
	@Test
	public void testTryQuery() { // test tryQuery using try-catch for Jsoup dependency
		
		try {
			String link = "http://eavesdrop.openstack.org/meetings/";
			String value = "solum";
			Elements mockFile = mock(Elements.class);
			Document doc = mock(Document.class);
			
			when(Jsoup.connect(link).get()).thenReturn(doc);
			when(mockOsmService.tryQuery(link, value)).thenReturn(mockFile);
			
			Elements files = osmService.tryQuery(link, value);
			assertEquals(files, mockFile);
			verify(osmService).tryQuery(link, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
