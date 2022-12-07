<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="changeAttend.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
ChangeAttend a = new ChangeAttend(jdbc.getConn());
// Boolean checkBoolean = Boolean.parseBoolean(request.getParameter("checkBoolean"));
// String SSN= request.getParameter("SSN");
// int EventNo = Integer.parseInt(request.getParameter("EventNo"));
String SSN = "D1017";
int EventNo = 2;
boolean checkBoolean = false;
String result = a.manageAttend(SSN, EventNo, checkBoolean);
%>
<%= result%>