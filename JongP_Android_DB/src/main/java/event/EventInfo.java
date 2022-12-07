package event;

import java.sql.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class EventInfo {
	private final Connection Conn;

	public EventInfo(Connection conn) {
		Conn = conn;
	}

	public String showEventInfo(int eventNo) {
		JsonObject eventInfo = new JsonObject();
		String sql = "";
		PreparedStatement pstmt = null;

		try {
			sql = "select * from event where eventno=?";
			pstmt = Conn.prepareStatement(sql);
			pstmt.setInt(1, eventNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				eventInfo.addProperty("NAME", rs.getString(2));
				eventInfo.addProperty("LOCATION", rs.getString(3));
				eventInfo.addProperty("DATE", rs.getDate(4).toString());
				eventInfo.addProperty("DESCRIPTION", rs.getString(5));
			}
			
			JsonArray progress = new JsonArray();			
			sql = "select * from progress where eventno=? order by progno asc";
			pstmt = Conn.prepareStatement(sql);
			pstmt.setInt(1, eventNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JsonObject temp = new JsonObject();
				temp.addProperty("PROGNO", rs.getInt(2));
				temp.addProperty("PCONTENT", rs.getString(3));
				progress.add(temp);
			}
			eventInfo.add("PROGRESS", progress);
			Conn.commit();

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eventInfo.toString();
	}
}
