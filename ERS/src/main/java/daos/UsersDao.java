package daos;

import java.util.HashMap;

import beans.User;

public interface UsersDao {
	public String getFirstName(int userId);
	public String getLastName(int userId);
	public String getEmail(int userId);
	
	/**
	 * Determines whether the password for the provided username matches the provided password.
	 * @param username The user to find in the database.
	 * @param password The String to compare with the database return  (all passwords are hashed)
	 * @return true if passwords match, false otherwise
	 */
	public boolean verifyLogin(String username, String password);
	
	/**
	 * Gets the given user's role
	 * @param userID ID of the user to check for in the database
	 * @return ID of this user's user role
	 */
	public int getRole(int userID);
	
	/**
	 * Retrieves a map of all current users from the database.
	 * @return HashMap of all current users, with IDs as keys
	 */
	public HashMap<Integer, User> getCurrentUsers();
}
