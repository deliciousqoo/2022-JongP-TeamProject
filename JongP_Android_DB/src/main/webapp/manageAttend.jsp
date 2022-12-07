<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="changeAttend.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
ChangeAttend a = new ChangeAttend(jdbc.getConn());
Boolean checkBoolean = Boolean.parseBoolean(request.getParameter("Attend"));
String SSN= request.getParameter("Ssn");
int EventNo = Integer.parseInt(request.getParameter("EventNo"));
String result = a.manageAttend(SSN, EventNo, checkBoolean);
%>
<%= result%>