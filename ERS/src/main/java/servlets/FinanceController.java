package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Reimbursement;
import daos.ReimbursementDao;
import dto.ReimbursementResolution;
import util.ResponseMapper;

public class FinanceController {
	private Logger log = Logger.getRootLogger();
	private ReimbursementDao rd = ReimbursementDao.currentImplementation;
	private ObjectMapper om = new ObjectMapper();
	
	void process(HttpServletRequest req, HttpServletResponse resp, String username) throws IOException {
		String method = req.getMethod();
		log.trace("request made to fincance controller with method: " + req.getMethod());
		System.out.println("Request Method: " + method); // -------------- Method is also wrongly set to OPTIONS here!
		switch (method) {
		case "GET":
			processGet(req, resp);
			break;
		case "POST":
			processPost(req, resp);
			break;
		case "OPTIONS":
			
			return;
		default:
			resp.setStatus(404);
			break;
		}
	}
	
	/**
	 * Get can receive requests for all the reimbursements a given employee has submitted
	 * @param req
	 * @param resp Sends back List of reimbursements as a JSON
	 * @throws IOException
	 */
	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "ERS";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		System.out.println(Arrays.toString(uriArray));
		if (uriArray.length == 1) {
			//int userId = Integer.parseInt(uriArray[1]); // Set from GET parameters
			List<Reimbursement> userReimbursements = ReimbursementDao.currentImplementation.getAllReimbursements();
			ResponseMapper.convertAndAttach(userReimbursements, resp);
			return;
		} // ------------- Needs ELSE for various filter terms 
		else {
			resp.setStatus(404);
			return;
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException {
		String uri = req.getRequestURI();
		String context = "ERS";
		uri = uri.substring(context.length() + 2, uri.length());
		if ("employee/add_reimb".equals(uri)) {
			ReimbursementResolution r = om.readValue(req.getReader(), ReimbursementResolution.class);
			System.out.println("Status: " + r.getStatus());
			
			//boolean success = rd.
			// --------------- Need to set resp if success == false
		} else {
			resp.setStatus(404);
			return;
		}
	}
}
