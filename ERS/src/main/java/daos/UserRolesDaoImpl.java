package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import database.ConnectionUtil;
import database.GlobalData;

public class UserRolesDaoImpl implements UserRolesDao {
	
	public HashMap<Integer, String> getAllUserRoles() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM user_roles";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			while (rs.next()) {
				map.put(rs.getInt("user_role_id"), rs.getString("user_role"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
