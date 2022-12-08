package vote;

import java.sql.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Vote {
	private final Connection conn;

	public Vote(Connection conn) {
		this.conn = conn;
	}
	
	public String getVoteList(int EventNo) {
		JsonObject json = new JsonObject();
		JsonArray VoteList = new JsonArray();		
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select * from vote where eventno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				JsonObject VoteInfo = new JsonObject();
				VoteInfo.addProperty("VOTENO", rs.getInt(2));
				VoteInfo.addProperty("AGENDA", rs.getString(3));
				VoteInfo.addProperty("CONTENT", rs.getString(4));
				
				int status = 0;
				if(rs.getDate(5) != null) {
					//VoteInfo.addProperty("STARTTIME", rs.getDate(5).toString());
					status = 1;
				}
				if(rs.getDate(6) != null) {
					//VoteInfo.addProperty("FINISHTIME", rs.getDate(6).toString());
					status = 2;
				}
				VoteInfo.addProperty("STATUS", status);
				VoteList.add(VoteInfo);
			}
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json.add("items", VoteList);

		return json.toString();
	}
	
	public String getVoteInfo(int EventNo, int VoteNo) {
		JsonObject VoteInfo = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select * from vote where eventno=? and voteno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ps.setInt(2, VoteNo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				VoteInfo.addProperty("VOTENO", rs.getInt(2));
				VoteInfo.addProperty("AGENDA", rs.getString(3));
				VoteInfo.addProperty("CONTENT", rs.getString(4));
				
				int status = 0;
				if(rs.getDate(5) != null) {
					//VoteInfo.addProperty("STARTTIME", rs.getDate(5).toString());
					status = 1;
				}
				if(rs.getDate(6) != null) {
					//VoteInfo.addProperty("FINISHTIME", rs.getDate(6).toString());
					status = 2;
				}
				VoteInfo.addProperty("STATUS", status);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return VoteInfo.toString();
	}
}
