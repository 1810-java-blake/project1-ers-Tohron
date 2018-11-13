package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import beans.User;

public class UsersDaoImpl implements UsersDao {

	public String getFirstName(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastName(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifyLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getRole(int userID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public HashMap<Integer, User> getCurrentUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
