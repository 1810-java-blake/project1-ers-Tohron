package servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Responds to request to show past reimbursements,
 * receives new reimbursement requests, and updates
 * the client with changes to past reimbursements.
 * @author Aaron
 *
 */
public class EmployeeServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("initializing servlet");

		String configParam = config.getInitParameter("specifParam");
		System.out.println("config param: " + configParam);

		String contextParam = config.getServletContext().getInitParameter("sharedParam");
		System.out.println("context param: " + contextParam);
	}
}
