package checkAttend;

import java.sql.*;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class CheckAttend {
	private final Connection conn;

	public CheckAttend(Connection conn) {
		this.conn = conn;
	}
	
	public String checkAttend(String SSN, int EventNo) {
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select * from attend where eventno=? and ssn=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ps.setString(2, SSN);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				json.addProperty("checkBoolean", true);
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
