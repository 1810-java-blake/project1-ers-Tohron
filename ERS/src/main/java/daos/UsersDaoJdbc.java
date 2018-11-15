package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import beans.User;
import database.ConnectionUtil;

public class UsersDaoJdbc implements UsersDao {

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

	public boolean verifyLogin(String username, String password, int role_id) {
		String query = "SELECT ers_password, user_role_id FROM users WHERE username = ?";
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (password.equals(rs.getString("ers_password")) && role_id == rs.getInt("user_role_id")) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
