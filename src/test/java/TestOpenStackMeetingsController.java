/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.jsoup.select.Elements;


public class TestOpenStackMeetingsController {
	
	OpenStackMeetingsController osmController = new OpenStackMeetingsController();
	OpenStackMeetingsService mockOsmService = null;
	
	@Before
	public void setUp1() {		
		mockOsmService = mock(OpenStackMeetingsService.class);		
		osmController.setOsmService(mockOsmService);
	}
	
	@Test
	public void thisAlwaysPasses() {
    }
		
	@Test
	public void testWelcomeCall() {
		when(mockOsmService.welcome()).thenReturn("Welcome to Open Stack meeting statistics calculation page. " 
				+ "Please provide project and year as query parameters.");
		String reply = osmController.welcomeCall();
		assertEquals("Welcome to Open Stack meeting statistics calculation page. " 
				+ "Please provide project and year as query parameters.", reply);
		verify(mockOsmService).welcome();
	}
	
	@Test
	public void testGetParams1() {	// test correct input
		String link = "http://eavesdrop.openstack.org/meetings/";
		Elements projects = mock(Elements.class);
		Elements years = mock(Elements.class);
		String project = "heat";
		String year = "2017";
		when(mockOsmService.tryQuery(link, project)).thenReturn(projects);
		when(mockOsmService.tryQuery(link + "/" + project, year)).thenReturn(years);
		String result = osmController.getParams(project, year);
		assertEquals("Number of files: 0", result);
		verify(mockOsmService).tryQuery(link, project);
	}
	
	@Test
	public void testGetParams2() {	// test bad year
		String link = "http://eavesdrop.openstack.org/meetings/";
		Elements projects = mock(Elements.class);
		Elements years = mock(Elements.class);
		String project = "heat";
		String year = "207";
		when(mockOsmService.tryQuery(link, project)).thenReturn(projects);
		when(mockOsmService.tryQuery(link + "/" + project, year)).thenReturn(years);
		String result = osmController.getParams(project, year);
		assertEquals("Invalid year 207 for project name \"heat\". ", result);
		verify(mockOsmService).tryQuery(link, project);
	}
	
	@Test
	public void testGetParams3() { // test bad project name
		String link = "http://eavesdrop.openstack.org/meetings/";
		Elements years = mock(Elements.class);
		String project = "abcd";
		String year = "2017";
		when(mockOsmService.tryQuery(link, project)).thenReturn(null);
		when(mockOsmService.tryQuery(link + "/" + project, year)).thenReturn(years);
		String result = osmController.getParams(project, year);
		assertEquals("Project with name \"" + project + "\" not found. ", result);
		verify(mockOsmService).tryQuery(link, project);
	}
	
	@Test
	public void testGetParams4() {	// test bad project name and year.
		String link = "http://eavesdrop.openstack.org/meetings/";
		String project = "hello";
		String year = "207";
		when(mockOsmService.tryQuery(link, project)).thenReturn(null);
		when(mockOsmService.tryQuery(link + "/" + project, year)).thenReturn(null);
		String result = osmController.getParams(project, year);
		assertEquals("Project with name \"" + project + "\" not found. " + 
				"Invalid year 207 for project name \"" + project + "\". ", result);
		verify(mockOsmService).tryQuery(link, project);
	}
	
}
