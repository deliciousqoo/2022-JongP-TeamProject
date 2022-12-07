package eventcode;

import java.sql.*;
import com.google.gson.JsonObject;

public class AttendCode {
	private final Connection conn;

	public AttendCode(Connection conn) {
		this.conn = conn;
	}

	public String checkAttendCode(String code, String SSN, int EventNo) { // codeCheck
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select attendcode from event where eventno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(code)) {
					json.addProperty("codeCheck", true);
					attend(SSN, EventNo);
//					System.out.println("correct code");
				} else {
					json.addProperty("codeCheck", false);
//					System.out.println("wrong code");
				}
			}

			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return json.toString();
	}

	void attend(String SSN, int EventNo) {
		try {
			String sql = "insert into attend values (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, SSN);
			ps.setInt(2, EventNo);
			int res = ps.executeUpdate();
			if (res == 1) {
				conn.commit();
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
