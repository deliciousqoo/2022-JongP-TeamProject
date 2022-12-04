package attend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class attend {
	private final Connection Conn;

	public attend(Connection conn) {
		super();
		Conn = conn;
	}

	public String getAttend(int EventNo) {
		JsonObject json = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		String sql = "select *\n" + "from\n" + "member left outer join \n" + "(   select * \n" + "    from attend\n"
				+ "    where attend.eventno = ?) attend1\n" + "on member.ssn = attend1.ssn";

		PreparedStatement pstmt = null;

		try {
			pstmt = Conn.prepareStatement(sql);
			pstmt.setInt(1, EventNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				JsonObject temp = new JsonObject();
				temp.addProperty("SSN", rs.getString(1));
				temp.addProperty("NAME", rs.getString(2));
				temp.addProperty("RANK", rs.getString(3));
				if (rs.getString(5) == null) {
					temp.addProperty("ATTEND", false);
				} else {
					temp.addProperty("ATTEND", true);
				}
				jsonArray.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		json.add("item", jsonArray);
		return json.toString();
	}
}
