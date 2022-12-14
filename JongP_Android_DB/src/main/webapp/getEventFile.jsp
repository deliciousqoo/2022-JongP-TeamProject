<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="event.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB jdbc = new ConnectDB();
int EventNo = Integer.parseInt(request.getParameter("EventNo"));
//int EventNo = 1;
Event e = new Event(jdbc.getConn());
String result = e.showEventFile(EventNo);
%>
<%= result%>