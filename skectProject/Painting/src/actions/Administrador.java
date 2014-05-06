package actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.primefaces.context.RequestContext;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import entities.Person;

@ManagedBean(name = "administrarActionS")
@SessionScoped
public class Administrador implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	public String nombre;
	public String nombre2;

	private Person person;
	private String jsonText;

	private Twitter twitter;
	private List<Status> timeline;
	
	private String search;
	private List<Status> results;
	
	public List<Status> getResults() {
		return results;
	}

	public void setResults(List<Status> results) {
		this.results = results;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Administrador() {
		person = new Person();
		jsonText = "";
		initilizeTwitter();
	}
	
	public void twitterTest(){
		try{
		    timeline = twitter.getHomeTimeline();
		    
		}catch(Exception ex){
			showMessage(ex.getMessage());
		}
	}
	
	public void twitterSearch() {
		try {
			Query query = new Query(search);
			QueryResult result = twitter.search(query);
			results = result.getTweets();
			
		} catch (Exception ex) {
			showMessage(ex.getMessage());
		}
	}
	
	public void youtubeSearch(){
		
	}
	
	public String getImage(Status tweet){
		return tweet.getUser().getProfileImageURL();
	}
	
	public List<Status> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<Status> timeline) {
		this.timeline = timeline;
	}

	private void initilizeTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("NPdpOFNXRjAAbHCzxJUT3FU2I")
		  .setOAuthConsumerSecret("UEvVonQa3Y3pyWeLOAukw4TXkRoIGWWFsy7550OwptPgdygH7G")
		  .setOAuthAccessToken("142678847-0myJn14qdbos7bODl76ayxNRAhDm6zwsBeJrgYQw")
		  .setOAuthAccessTokenSecret("q2dEekQD2aHUszWm0aK84z2jHHQ4JqQ1JSin3vThaCCfy");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String nombreOb() {
		
		nombre2 = nombre;
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);

		Person p2 = new Person();
		p2.setAge(12);
		p2.setDirection("New York");
		p2.setName("Luis");

		personList.add(p2);
		try {
            RequestContext.getCurrentInstance().execute("alert('This onload script is added from backing bean.')");

            org.primefaces.json.JSONObject jsonObject = new org.primefaces.json.JSONObject(person);
			
			
			jsonText = "{\"first\": \"123\", \"second\": [\"4\", \"5\", \"6\"], \"third\": 789}";
			
			Object personJson = JSONValue.parse(jsonText);
			HashMap<String, Object> map = (HashMap<String, Object>) JSONValue.parse(jsonText);
			JSONArray array = (JSONArray) map.get("second");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Information",
							(String)array.get(0)));
			jsonText = jsonObject.toString();
			
		} catch (JSONException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Exception",
							ex.getMessage()));
		}

		return "";
	}
	
	private void showMessage(String message){
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exception",
						message));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
