<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="eventcode.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
AttendCode a = new AttendCode(jdbc.getConn());
// String code = request.getParameter("code");
// String SSN= request.getParameter("SSN");
// int EventNo = Integer.parseInt(request.getParameter("EventNo"));
String code = "5678";
String SSN = "D1017";
int EventNo = 2;
String result = a.checkAttendCode(code, SSN, EventNo);
%>
<%= result%>