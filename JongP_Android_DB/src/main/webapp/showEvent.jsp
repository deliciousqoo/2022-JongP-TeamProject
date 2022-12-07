<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="event.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
int EventNo = Integer.parseInt(request.getParameter("EventNo"));
EventInfo e = new EventInfo(jdbc.getConn());
String result = e.showEventInfo(EventNo);
%>
<%= result%>