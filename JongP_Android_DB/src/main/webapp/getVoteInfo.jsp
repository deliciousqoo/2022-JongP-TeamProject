<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="vote.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
//String Ssn = request.getParameter("Ssn");
//int EventNo = Integer.parseInt(request.getParameter("EventNo"));
//int VoteNo = Integer.parseInt(request.getParameter("VoteNo"));
String Ssn = "D1017";
int EventNo = 1;
int VoteNo = 1;
Vote v = new Vote(jdbc.getConn());
String result = v.getVoteInfo(Ssn, EventNo, VoteNo);
%>
<%= result%>