package changeAttend;

import java.sql.*;
import com.google.gson.JsonObject;

public class ChangeAttend {
	private final Connection conn;
	
	public ChangeAttend(Connection conn) {
		this.conn = conn;
	}
	
	public String manageAttend(String SSN, int EventNo, boolean checkBoolean) {
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select * from attend where eventno=? and ssn=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ps.setString(2, SSN);
			ResultSet rs = ps.executeQuery();
			Boolean attended = false;
			if (rs.next()) {
				attended = true;
			}
			
			// 0: 성공 1: 실패 2: 이미 출석/결석
			if(checkBoolean && (!attended)) {	// insert
				sql="insert into attend values (?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, SSN);
				ps.setInt(2, EventNo);
				int res = ps.executeUpdate();
				if (res == 1) {
					conn.commit();
					json.addProperty("checkBoolean", true);
				}else {
					json.addProperty("checkBoolean", false);
				}
			}else if((!checkBoolean) && attended) {	// delete
				sql="delete from attend where eventno=? and ssn=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, EventNo);
				ps.setString(2, SSN);
				int res = ps.executeUpdate();
				if (res == 1) {
					conn.commit();
					json.addProperty("checkBoolean", true);
				}else {
					json.addProperty("checkBoolean", false);
				}
			}else {
				json.addProperty("checkBoolean", false);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return json.toString();
	}
}
