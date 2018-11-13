package database;

import java.util.HashMap;

import daos.ReimbursementStatusDao;
import daos.ReimbursementTypeDao;
import daos.UserRolesDao;

public class GlobalData {
	private static GlobalData currentImplementation = null; 
	
	public static final HashMap<Integer, String> reimbursementStatuses 
			= ReimbursementStatusDao.currentImplementation.getAllReimbursementStatuses();
	public static final HashMap<Integer, String> reimbursementTypes 
			= ReimbursementTypeDao.currentImplementation.getAllReimbursementTypes();
	public static final HashMap<Integer, String> userRoles 
			= UserRolesDao.currentImplementation.getAllUserRoles();
	
	private GlobalData() {
		
	}
	
	public static GlobalData getImplementation() {
		if (currentImplementation == null) {
			currentImplementation = new GlobalData();
		}
		return currentImplementation;
	}
}
