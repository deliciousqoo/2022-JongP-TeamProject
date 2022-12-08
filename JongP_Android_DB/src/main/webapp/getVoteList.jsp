<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="vote.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
//int EventNo = Integer.parseInt(request.getParameter("EventNo"));
int EventNo = 3;
Vote v = new Vote(jdbc.getConn());
String result = v.getVoteList(EventNo);
%>
<%= result%>