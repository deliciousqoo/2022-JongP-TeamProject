<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="vote.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
//int EventNo = Integer.parseInt(request.getParameter("EventNo"));
//int VoteNo = Integer.parseInt(request.getParameter("VoteNo"));
//boolean isStart = Boolean.parseBoolean(request.getParameter("isStart"));
int EventNo = 5;
int VoteNo = 1;
boolean isStart = true;
Vote v = new Vote(jdbc.getConn());
String result = v.updateTime(EventNo, VoteNo, isStart);
%>
<%= result%>