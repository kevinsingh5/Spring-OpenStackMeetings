/*
 * @author Kevin Singh
 *   date: October 6, 2017
 * 
 */


import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenStackMeetingsController {
	
	private OpenStackMeetingsService osmService;
	
	// constructors 
	public OpenStackMeetingsController() {
		
	}
	
//	public OpenStackMeetingsController(OpenStackMeetingsService osmService) {
//		this.osmService = osmService;
//	}
	
	// setter method for osmService
	public void setOsmService(OpenStackMeetingsService osm) {
		this.osmService = osm;
		//return osmService;
	}
	
	// getter method for osmService
//	public OpenStackMeetingsService getOsmService(OpenStackMeetingsService osm) {
//		return osmService;
//	}
	
	@ResponseBody
    @RequestMapping(value = "/openstackmeetings")
    public String welcomeCall() {
        return osmService.welcome();
    }
	
	@ResponseBody
    @RequestMapping(value = "/openstackmeetings", params = {"project", "year"}, method=RequestMethod.GET)
    public String getParams(@RequestParam("project") String project, @RequestParam("year") String year) {	
		String link = "http://eavesdrop.openstack.org/meetings/";
		String ret = "";
		Elements projects = null;
		Elements years = null;
		int num_files = 0;
		
		// try loading selected project document
		if (project != null) {
			projects = osmService.tryQuery(link, project.toLowerCase());
		}
		if (projects == null) {
			ret = "Project with name \"" + project + "\" not found. ";
		}
		
		//try loading selected year argument
		if (year != null && (Integer.parseInt(year) > 1990 && Integer.parseInt(year) < 2100)) {
			years = osmService.tryQuery(link + "/" + project.toLowerCase(), year);
		}
		if(years == null) {
			ret += "Invalid year " + year + " for project name \"" + project + "\". ";
		}
		if(projects != null && years != null) {
			num_files = osmService.count_files(years);
			ret = "Number of files: " + String.valueOf(num_files);
		}
		return ret;
    }
	
}


/*
@Controller
public class EmailController {

	private EditorService editorService;
	
	public EmailController() {
		
	}
	
	public EmailController(EditorService editorService) {
		this.editorService = editorService;
	}
	
	@ResponseBody
    @RequestMapping(value = "/")
    public String helloWorld()
    {
        return "Hello world!";
    }
	
	@ResponseBody
	@RequestMapping(value = "/compose")
	public String getComposedEmail() {
		return editorService.composeEmail();
	}	
	
	@ResponseBody
    @RequestMapping(value = "/", params = {"action"}, method=RequestMethod.GET)
    public String getGreeting(@RequestParam("action") String action)
    {
		String ret = "";
		if (action != null && action.equalsIgnoreCase("compose") ||
				action.equalsIgnoreCase("create")) {
			ret = editorService.composeEmail();
			editorService.thisIsVoidFunction();
		}
		return ret;
    }
	
	public String getWithGhostEditor() {
		editorService = new EnglishEditorServiceImpl();
        return editorService.composeEmail();		
	}
	
	public void setEditorService(EditorService editorService) {
		this.editorService = editorService;
	}
}
*/
