package game.soap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7089407725360837559L;
	private long appId;
	private String name;
	private float price;
	private float fullPrice;
	private float rating;
	@XmlElementWrapper(name="genres")
	@XmlElement(name="genre")
	private String[] genres;
	private String date;
	@XmlElement(name="GameInfo")
	private GameInfo gameInfo;
	
	public Game() {}
	
	public Game(long appId, String name, float price, float fullPrice, float rating, String[] genres, String date, GameInfo gameInfo)
	{
		this.appId = appId;
		this.name = name;
		this.price = price;
		this.fullPrice = fullPrice;
		this.rating = rating;
		this.genres = genres;
		this.date = date;
		this.gameInfo = gameInfo;
	}
	
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(float fullPrice) {
		this.fullPrice = fullPrice;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String[] getGenere() {
		return genres;
	}
	public void setGenere(String[] genres) {
		this.genres = genres;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
}
