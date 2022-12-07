<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="connectDB.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="attend.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
Object EventString = request.getParameter("EventNo");
//int EventNo = 1;
if(EventString != null){
	int EventNo = Integer.parseInt(EventString.toString());
	attend a = new attend(jdbc.getConn());
	String result = a.getAttend(EventNo);
	out.print(result);
}else{
	out.print("there's not correct input");
}
%>
