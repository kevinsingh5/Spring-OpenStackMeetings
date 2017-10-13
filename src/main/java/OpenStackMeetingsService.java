import org.jsoup.select.Elements;

/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


public interface OpenStackMeetingsService {

	public String welcome();
	
	public Elements tryQuery(String link, String value);
	
	public int count_files(Elements values);
	
	
	
	
	
}


/*	public String composeEmail();
	
	public String getName();
	
	public String getResponseFromEavesDrop(String projectName);
	
	public void thisIsVoidFunction();
*/
