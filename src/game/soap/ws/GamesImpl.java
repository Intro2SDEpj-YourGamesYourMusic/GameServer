package game.soap.ws;

import java.util.List;

import javax.jws.WebService;

import game.soap.external.ExternalAPICaller;
import game.soap.model.Game;

@WebService(endpointInterface = "game.soap.ws.Games",
    serviceName="GamesService")
public class GamesImpl implements Games{

	ExternalAPICaller api = new ExternalAPICaller();
	
	@Override
	public List<Game> getGameList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game getGame(long appId, String name) {
		Game g = new Game();
		g.setAppId(appId);
		g.setName(name);
		try {
			g = api.getGame(g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public List<Game> init() {
		return api.init();
	}

}
