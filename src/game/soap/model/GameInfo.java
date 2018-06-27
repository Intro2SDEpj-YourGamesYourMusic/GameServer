package game.soap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GameInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2906057134833866514L;
	private boolean singlePlayer;
	private boolean multiPlayer;
	private boolean multiPlayerOnline;
	private boolean multiPlayerLocal;
	private boolean coop;
	private boolean coopOnline;
	
	public GameInfo() {}
	
	public GameInfo(boolean... values) {
		singlePlayer = values[0];
		multiPlayer = values[1];
		multiPlayerOnline = values[2];
		multiPlayerLocal = values[3];
		coop = values[4];
		coopOnline = values[5];
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}

	public boolean isMultiPlayer() {
		return multiPlayer;
	}

	public void setMultiPlayer(boolean multiPlayer) {
		this.multiPlayer = multiPlayer;
	}

	public boolean isMultiPlayerOnline() {
		return multiPlayerOnline;
	}

	public void setMultiPlayerOnline(boolean multiPlayerOnline) {
		this.multiPlayerOnline = multiPlayerOnline;
	}

	public boolean isMultiPlayerLocal() {
		return multiPlayerLocal;
	}

	public void setMultiPlayerLocal(boolean multiPlayerLocal) {
		this.multiPlayerLocal = multiPlayerLocal;
	}

	public boolean isCoop() {
		return coop;
	}

	public void setCoop(boolean coop) {
		this.coop = coop;
	}

	public boolean isCoopOnline() {
		return coopOnline;
	}

	public void setCoopOnline(boolean coopOnline) {
		this.coopOnline = coopOnline;
	}
}
