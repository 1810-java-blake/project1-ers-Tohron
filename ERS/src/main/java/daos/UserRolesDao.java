package daos;

import java.util.HashMap;

public interface UserRolesDao {
	UserRolesDao currentImplementation = new UserRolesDaoImpl();
	/**
	 * Retrieves all user roles from the database.
	 * @return A way to get a given role using its ID.
	 */
	public HashMap<Integer, String> getAllUserRoles();
}
