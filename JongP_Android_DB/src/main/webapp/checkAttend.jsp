<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="java.sql.*"%>
<%@ page import="connectDB.*"%>
<%@ page import="checkAttend.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
CheckAttend a = new CheckAttend(jdbc.getConn());
String SSN= request.getParameter("Ssn");
int EventNo = Integer.parseInt(request.getParameter("EventNo"));
String result = a.checkAttend(SSN, EventNo);
%>
<%= result%>