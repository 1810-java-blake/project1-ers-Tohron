package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Reimbursement;
import database.ConnectionUtil;
import util.GlobalData;

public class ReimbursementDaoJdbc implements ReimbursementDao {

	@Override
	public List<Reimbursement> getAllReimbursements() {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM reimbursement";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"), 
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), 
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsWithStatus(int statusId) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM reimbursement WHERE reimb_status_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, statusId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"), 
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), 
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getUserReimbursements(int userId) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM reimbursement WHERE reimb_author = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"), 
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), 
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reimbursements;
	}

	@Override
	public boolean submitReimbursement(Reimbursement r) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_description, "
					+ "reimb_author, reimb_status_id, reimb_type_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDouble(1, r.getAmount());
			stmt.setTimestamp(2, r.getSubmitted());
			stmt.setString(3, r.getDescription());
			stmt.setInt(4, r.getAuthor());
			stmt.setInt(5, r.getStatusID());
			stmt.setInt(6, r.getTypeID());
			int result = stmt.executeUpdate();
			return result > 0; // true if row affected, false otherwise
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean approveReimbursement(int reimbID, int resolverID) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "UPDATE reimbursement SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
			stmt.setInt(2, resolverID);
			stmt.setInt(3, GlobalData.reimbursementStatuses.get("APPROVED"));
			int result = stmt.executeUpdate();
			return result > 0; // true if row affected, false otherwise
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean denyReimbursement(int reimbID, int resolverID) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "UPDATE reimbursement SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
			stmt.setInt(2, resolverID);
			stmt.setInt(3, GlobalData.reimbursementStatuses.get("REJECTED"));
			int result = stmt.executeUpdate();
			return result > 0; // true if row affected, false otherwise
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
