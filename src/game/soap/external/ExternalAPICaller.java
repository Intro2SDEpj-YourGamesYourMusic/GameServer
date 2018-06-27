package game.soap.external;

import game.soap.model.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class ExternalAPICaller {
	private String key = "";
	String listGamesURL = "https://api.steampowered.com/ISteamApps/GetAppList/v0002/";
	String gameURL = "https://store.steampowered.com/api/appdetails?appids=";
	
	ClientConfig clientConfig = new ClientConfig();
	Client client = ClientBuilder.newClient(clientConfig);
	WebTarget service;
	List<Game> fullGameList = new ArrayList<Game>();
	
	String gameListJSON = "";
	
	public Game getGame(Game g) throws Exception {
		String gameJSON = sendGet(gameURL+g.getAppId());
		//gameJSON = 
		g = JsonToGame(g, gameJSON);
		return g;
	}
	
	public List<Game> init(){
		//String gameJSON = "";
		try {
			gameListJSON = sendGet(listGamesURL);
			List<Game> games = JsonToGames(gameListJSON);
			for(Game g : games) {
				//gameJSON = sendGet(gameURL+g.getAppId());
				//g = JsonToGame(g, gameJSON);
				fullGameList.add(getGame(g));
				System.out.println(g.getAppId()+" "+g.getName());
			}
			return fullGameList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Game> initRange(int i, int n){
		//String gameJSON = "";
		try {
			gameListJSON = sendGet(listGamesURL);
			List<Game> games = JsonToGames(gameListJSON);
			for(int j = i; (j < n && j < games.size()); j++) {
				Game g = games.get(j);
				//gameJSON = sendGet(gameURL+g.getAppId());
				//g = JsonToGame(g, gameJSON);
				fullGameList.add(getGame(g));
				System.out.println(g.getAppId()+" "+g.getName());
			}
			return fullGameList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// HTTP GET request
	private String sendGet(String url) throws Exception {
		service = client.target(url);
		Response response;
	    response=service.request().accept(MediaType.APPLICATION_JSON).get();
	    System.out.println(response.getStatus());
		String s = response.readEntity(String.class).toString();
		
		/*URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer s = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			inputLine = cleanTextContent(inputLine);
			System.out.println(inputLine);
			s.append(inputLine);
		}
		in.close();
	    
		return s.toString();*/
		return s;
	}
	
	/*public static void main(String[] args) throws Exception {
		
	}*/
	
	private String cleanTextContent(String text)
	{
	    // strips off all non-ASCII characters
	    text = text.replaceAll("[^\\x00-\\x7F]", "");
	 
	    // erases all the ASCII control characters
	    text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
	     
	    // removes non-printable characters from Unicode
	    text = text.replaceAll("\\p{C}", "");
	 
	    return text.trim();
	}
	
	Game JsonToGame(Game g, String json) {
		ObjectMapper mapper = new ObjectMapper();
		//id - data - price_overview - /initial /final
		//id - data - categories - gameinfo?
		//id - data - genres - array /description
		//id - data - release_date - /date
		
		/*System.out.println(json);
		json = cleanTextContent(json);
		System.out.println(json);*/
		
		try {
			JsonNode root = mapper.readTree(json).get(g.getAppId()+"");
			
			JsonNode data = root.get("data");
			JsonNode price = data.get("price_overview");
			JsonNode genres = data.get("genres");
			JsonNode categories = data.get("categories");

			//System.out.println(price);
			g.setFullPrice(price.get("initial").asLong()/100f);
			g.setPrice(price.get("final").asLong()/100f);
			
			
			g.setRating(0f);
			String[] genreList = new String[genres.size()];
			for(int i = 0; i < genres.size(); i++) {
				JsonNode genre = (JsonNode) genres.get(i);
				String s = genre.get("description").asText();
				genreList[i]=s;
			}
			g.setGenere(genreList);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return g;
		}
	}
	
 	List<Game> JsonToGames(String json) {
		List<Game> games = new ArrayList<Game>();
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode applist;
		try {
			applist = mapper.readTree(json).get("applist");
		
		
		JsonNode apps = applist.get("apps");

		System.out.println(apps.size());
		
		for(int i = 0; i < apps.size(); i++) {
			Game g = new Game();
			JsonNode j = (JsonNode) apps.get(i);
			long id = j.get("appid").asLong();
			String name = j.get("name").asText();
			g.setAppId(id);
			g.setName(name);
			games.add(g);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Games found: "+games.size());
		return games;
	}
}
