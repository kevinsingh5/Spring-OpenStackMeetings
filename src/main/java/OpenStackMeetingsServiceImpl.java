/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


import java.io.IOException;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OpenStackMeetingsServiceImpl implements OpenStackMeetingsService {
	
//	public OpenStackMeetingsServiceImpl() {
//		
//	}
	
	public String welcome() {
		return "Welcome to Open Stack meeting statistics calculation page. " + 
        		"Please provide project and year as query parameters.";
	}
	
	// try loading a query into a given link using Jsoup
	public Elements tryQuery(String link, String value) {
		Document doc = null;
		// try to load the project page
		try {
			doc = Jsoup.connect(link + "/" + value.toLowerCase()).get();
		} catch (IOException e) {
			//writer.println("Project with name " + project + " not found");
			e.printStackTrace();
			return null;
		}
		// get the file elements
		if (doc != null) {
			Elements files = doc.select("tr td a[href]");
		    return files;			
		} else {
			return null;
		}
	}
	
	// count the files from 
	public int count_files(Elements values) {
	    int counter = 0;
		ListIterator<Element> iterator = values.listIterator();		    	
	    while(iterator.hasNext()) {
	    	Element e = (Element) iterator.next();
    		String s = e.html();
    		if (s.equalsIgnoreCase("Parent Directory"))
    			continue;
    		counter++;
	    }
	    return counter;
	}
	
	
}


/*
public class EnglishEditorServiceImpl implements OpenStackMeetingsService {

	String name = "EnglishEditor";
	URL eavesdropURL = null;
	String baseURL = "http://eavesdrop.openstack.org/";
	
	public EnglishEditorServiceImpl() {
		try {
			eavesdropURL = new URL(baseURL);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String composeEmail() {
		return "Hello world.";
	}

	public String getName() {
		return this.name;
	}

	public String getResponseFromEavesDrop(String projectName) {
		String retVal = "";		
		baseURL += "/meetings/" + projectName;		
		try {			
			eavesdropURL = new URL(baseURL);
			URLConnection connection = eavesdropURL.openConnection();
			String readData = readDataFromEavesdrop(connection);
			retVal = parseOutput(readData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	protected String readDataFromEavesdrop(URLConnection connection) {
		String retVal = "";
		try {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			retVal += inputLine;
		}
		in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	protected String parseOutput(String inputString) {
		String retVal = "";
		
		return retVal;
	}

	public void thisIsVoidFunction() {
		
	}
}

*/
