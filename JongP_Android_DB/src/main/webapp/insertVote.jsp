<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="vote.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
int EventNo = Integer.parseInt(request.getParameter("EventNo"));
String Agenda = request.getParameter("Agenda");
String content = request.getParameter("Content");
/* int EventNo = 2;
String Agenda = "아침 메뉴";  
String content = "해장국 어떠신가요?"; */
Vote v = new Vote(jdbc.getConn());
String result = v.insertVote(EventNo, Agenda, content);
%>
<%= result%>