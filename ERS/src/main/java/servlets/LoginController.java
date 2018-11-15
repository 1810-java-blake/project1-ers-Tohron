package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import daos.UsersDao;
import database.GlobalData;
import dto.Credential;

public class LoginController {
	private Logger log = Logger.getRootLogger();
	private UsersDao ud = UsersDao.currentImplementation;
	private ObjectMapper om = new ObjectMapper();
	
	void process(HttpServletRequest req, HttpServletResponse resp, 
			HashMap<String, String> loggedEmployees, HashMap<String, String> loggedFManagers) throws IOException {
		String method = req.getMethod();
		switch (method) {
		case "GET":
			//processGet(req, resp);
			break;
		case "POST":
			processPost(req, resp, loggedEmployees, loggedFManagers);
			break;
		case "OPTIONS":
			return;
		default:
			resp.setStatus(404);
			break;
		}
	}
/*
	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		String uri = req.getRequestURI();
		String context = "LeagueOfLegendsApi";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		System.out.println(Arrays.toString(uriArray));
		if (uriArray.length == 1) {
			log.info("retreiving all users");
			List<User> users = ud.g
			ResponseMapper.convertAndAttach(users, resp);
			return;
		} else if (uriArray.length == 2) {
			try {
				int id = Integer.parseInt(uriArray[1]);
				log.info("retreiving user with id: " + id);
				AppUser user = us.findById(id);
				ResponseMapper.convertAndAttach(user, resp);
				return;
			} catch (NumberFormatException e) {
				resp.setStatus(400);
				return;
			}
		} else {
			resp.setStatus(404);
		}
		*-/
	}
*/
	private void processPost(HttpServletRequest req, HttpServletResponse resp, 
			HashMap<String, String> loggedEmployees, HashMap<String, String> loggedFManagers) throws JsonParseException, JsonMappingException, IOException {
		String uri = req.getRequestURI();
		String context = "LeagueOfLegendsApi";
		uri = uri.substring(context.length() + 2, uri.length());
		if ("users".equals(uri)) {
			log.info("saving new user");
		} else if ("users/login".equals(uri)) {
			log.info("attempting to log in");
			Credential cred = om.readValue(req.getReader(), Credential.class);
			int roleID = GlobalData.userRoles.get(cred.getRole());
			String session = req.getSession().getId();
			if(!ud.verifyLogin(cred.getUsername(), cred.getPassword(), roleID)) {
				resp.setStatus(403);
			} else if ("FINANCE".equals(cred.getRole())){
				loggedFManagers.put(session, cred.getUsername());
				System.out.println("Logged in as " + cred.getUsername());
			} else if ("EMPLOYEE".equals(cred.getRole())){
				loggedEmployees.put(session, cred.getUsername());
				System.out.println("Logged in as " + cred.getUsername());
			}
		} else {
			resp.setStatus(404);
			return;
		}
	}
}
