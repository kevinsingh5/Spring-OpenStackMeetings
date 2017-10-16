/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


import org.jsoup.select.Elements;


public interface OpenStackMeetingsService {

	public String welcome();
	
	public Elements tryQuery(String link, String value);
	
	public int count_files(Elements values);
	
}