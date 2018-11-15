package daos;

import java.util.List;

import beans.Reimbursement;

public interface ReimbursementDao {
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getReimbursementsWithStatus(int statusId);
	
	public List<Reimbursement> getUserReimbursements(int userId);
	
	/**
	 * Submits a reimbursement with the given info to the database
	 * @param r the Reimbursement containing the info that will be submitted
	 * 			to the database as a reimbursement
	 * @return true if database operation completed, false otherwise
	 */
	public boolean submitReimbursement(Reimbursement r);
	
	/**
	 * Approves the reimbursement with the given ID.
	 * @param reimbID the ID of the reimbursement to approve
	 * @return true if database operation completed, false otherwise
	 */
	public boolean approveReimbursement(int reimbID, int resolverID);
	/**
	 * Denies the reimbursement with the given ID.
	 * @param reimbID the ID of the reimbursement to deny
	 * @return true if database operation completed, false otherwise
	 */
	public boolean denyReimbursement(int reimbID, int resolverID);
}
