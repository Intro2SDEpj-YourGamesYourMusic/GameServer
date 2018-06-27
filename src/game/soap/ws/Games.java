package game.soap.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import game.soap.model.Game;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Games {
//1
    @WebMethod(operationName="getGameList")
    @WebResult(name="games") 
    public List<Game> getGameList();
//2
    @WebMethod(operationName="getGame")
    @WebResult(name="game") 
    public Game getGame(@WebParam(name="appId") long appId, @WebParam(name="name") String name);
//3
    @WebMethod(operationName="init")
    @WebResult(name="games") 
    public List<Game> init();
}