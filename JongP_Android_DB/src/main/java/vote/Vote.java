package vote;

import java.sql.*;

import org.apache.catalina.startup.AddPortOffsetRule;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Vote {
	private final Connection conn;

	public Vote(Connection conn) {
		this.conn = conn;
	}
//	투표 목록 띄우기 
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
				VoteInfo.addProperty("voteno", rs.getInt(2));
				VoteInfo.addProperty("title", rs.getString(3));
				VoteInfo.addProperty("explain", rs.getString(4));
				
				int status = 0;
				if(rs.getDate(5) != null) {
					VoteInfo.addProperty("starttime", rs.getDate(5).toString());
					status = 1;
				}
				if(rs.getDate(6) != null) {
					VoteInfo.addProperty("endtime", rs.getDate(6).toString());
					status = 2;
				}
				VoteInfo.addProperty("status", status);
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

//	투표 정보 띄우기 
	public String getVoteInfo(String Ssn, int EventNo, int VoteNo) {
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
				
				sql = "select * from participate where eventno=? and voteno=? and ssn=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, EventNo);
				ps.setInt(2, VoteNo);
				ps.setString(3, Ssn);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					VoteInfo.addProperty("ANSWER", rs.getInt(4));
				}else {
					VoteInfo.addProperty("ANSWER", 0);
				}
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return VoteInfo.toString();
	}

//	투표 참가하기 
	public String participateVote(String SSN, int EventNo, int VoteNo, int Answer) {
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;

		try {
			sql = "select * from participate where ssn=? and eventno=? and voteno=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, SSN);
			ps.setInt(2, EventNo);
			ps.setInt(3, VoteNo);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				sql="update participate set answer=? where ssn=? and eventno=? and voteno=?";
				ps = conn.prepareStatement(sql);
				ps.setString(2, SSN);
				ps.setInt(3, EventNo);
				ps.setInt(4, VoteNo);
				ps.setInt(1, Answer);
				
				int res = ps.executeUpdate();
				if (res == 1) {
					conn.commit();
					json.addProperty("checkBoolean", true);
				}else {
					json.addProperty("checkBoolean", false);
				}
			}else {
				sql="insert into participate values (?, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, SSN);
				ps.setInt(2, EventNo);
				ps.setInt(3, VoteNo);
				ps.setInt(4, Answer);
				
				int res = ps.executeUpdate();
				if (res == 1) {
					conn.commit();
					json.addProperty("checkBoolean", true);
				}else {
					json.addProperty("checkBoolean", false);
				}
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return json.toString();
	}

//	시간 업데이트하기
	public String updateTime(int EventNo, int VoteNo, boolean isStart) {
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;
		java.sql.Date Time = new java.sql.Date(System.currentTimeMillis());
		
		try {
			sql = "select * from vote where eventno=? and voteno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ps.setInt(2, VoteNo);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if(isStart) {	// set start time
					if(rs.getDate(5) != null) {	// already has start time
						json.addProperty("checkBoolean", false);
					}else {
						sql="update vote set starttime=? where eventno=? and voteno=?";
						ps = conn.prepareStatement(sql);
						ps.setDate(1, Time);
						ps.setInt(2, EventNo);
						ps.setInt(3, VoteNo);
						
						int res = ps.executeUpdate();
						if (res == 1) {
							conn.commit();
							json.addProperty("checkBoolean", true);
							json.addProperty("time", Time.toString());
							
						}else {
							json.addProperty("checkBoolean", false);
						}
					}
				}else {		// set finish time
					if(rs.getDate(6) != null) {	// already has start time
						json.addProperty("checkBoolean", false);
					}else {
						sql="update vote set finishtime=? where eventno=? and voteno=?";
						ps = conn.prepareStatement(sql);
						ps.setDate(1, Time);
						ps.setInt(2, EventNo);
						ps.setInt(3, VoteNo);
						
						int res = ps.executeUpdate();
						if (res == 1) {
							conn.commit();
							json.addProperty("checkBoolean", true);
							json.addProperty("time", Time.toString());
						}else {
							json.addProperty("checkBoolean", false);
						}
					}
				}
			}else {
				json.addProperty("checkBoolean", false);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return json.toString();
	}
		
// 	투표 추가하기
//	Insert into VOTE (EVENTNO,VOTENO,AGENDA,CONTENT,STARTTIME,FINISHTIME) 
//	values (4,1,'저녁메뉴','국밥 먹으러 갈 사람',null,null);
	public String insertVote(int EventNo, String Agenda, String content) {
		JsonObject json = new JsonObject();
		String sql = "";
		PreparedStatement ps = null;
		sql = "Insert into VOTE (EVENTNO,VOTENO,AGENDA,CONTENT,STARTTIME,FINISHTIME) "
				+"values (?,?,?,?,null,null)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);
			ps.setInt(2, nextVoteNo(EventNo));
			ps.setString(3, Agenda);			
			ps.setString(4, content);			
			
			int rs = ps.executeUpdate();
			if (rs == 1) {
				conn.commit();
				json.addProperty("checkBoolean", true);
			}else {
				json.addProperty("checkBoolean", false);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		json.addProperty("EventNo", EventNo);
//		json.addProperty("agenda", Agenda);
//		json.addProperty("content", content);
		return json.toString();
	}
	
	public int nextVoteNo(int EventNo) {
		int res = 0;
		String sql = "";
		PreparedStatement ps = null;
		sql = "select max(voteno) as max from vote where eventno=? group by eventno";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, EventNo);			
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}else {
				res = 0;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + 1;
	}

}
