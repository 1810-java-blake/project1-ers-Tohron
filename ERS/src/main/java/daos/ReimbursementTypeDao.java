package daos;

import java.util.HashMap;

public interface ReimbursementTypeDao {
	ReimbursementTypeDao currentImplementation = new ReimbursementTypeDaoImpl();
	/**
	 * Retrieves all reimbursement types from the database.
	 * @return A way to get a given type using its ID.
	 */
	public HashMap<Integer, String> getAllReimbursementTypes();
}
