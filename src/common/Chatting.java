package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import main.MainService;

@ServerEndpoint(value = "/chtting.do", configurator = HttpSessionConfigurator.class)
public class Chatting {

	private MainService mainService;
	
	private static Map<String, List<Session>> rooms = (Map<String, List<Session>>) Collections.synchronizedMap(new HashMap<String, List<Session>>());
	
	private Map<Session, EndpointConfig> configs = Collections.synchronizedMap(new HashMap<>());
	
	public Chatting() {
		mainService = MainService.getMainService();
	}
	
	@OnOpen
	public void handleOpen(Session userSession, EndpointConfig config) {
		if(!configs.containsKey(userSession)) {
			configs.put(userSession, config);
		}
		
		HttpSession session = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
		
		String id = (String) session.getAttribute("loginUser");

		if(id.equals("admin@gmail.com")) {
			String connect = (String) session.getAttribute("connect");
			if(rooms.containsKey(connect))
				rooms.get(connect).add(userSession);
			
		}else{
			if(!rooms.containsKey(id))
				rooms.put(id, new ArrayList<Session>());
			rooms.get(id).add(userSession);
		}
		
	}
	
	@OnMessage
	public void handleMessage(String message, Session userSession) {
		
		HttpSession session = null;
		
		if (configs.containsKey(userSession)) {
			EndpointConfig config = configs.get(userSession);
			
			session = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
		}
		
		String id = (String) session.getAttribute("loginUser");
		
		if(id.equals("admin@gmail.com")) {
			id = (String) session.getAttribute("connect");
			
			mainService.insertChatting("admin@gmail.com", id, message);
		}else {
			mainService.insertChatting( id, "admin@gmail.com", message);
		}
			

		List<Session> room = rooms.get(id);
		
		for(Session user : room) {
			try {
				if(userSession!=user)
					user.getBasicRemote().sendText(id + "/" + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		
		String id = "";
		
		if (configs.containsKey(userSession)) {
			
			EndpointConfig config = configs.get(userSession);
			
			HttpSession session = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
			
			id = (String) session.getAttribute("loginUser");

			configs.remove(userSession);
		}
		
		List<Session> room = rooms.get(id);
		
		if(!id.equals("admin@gmail.com")) {
			room.remove(userSession);
			
			if(room.size()==0) {
				rooms.remove(id);
			}			
		}
	}

}
