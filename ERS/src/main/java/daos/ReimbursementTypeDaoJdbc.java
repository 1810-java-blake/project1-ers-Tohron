package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import database.ConnectionUtil;

public class ReimbursementTypeDaoJdbc implements ReimbursementTypeDao {

	public HashMap<String, Integer> getAllReimbursementTypes() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM reimbursement_type";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			while (rs.next()) {
				map.put(rs.getString("reimb_type"), rs.getInt("reimb_type_id"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
