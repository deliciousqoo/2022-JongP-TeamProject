<%@ page import="connectDB.*"%>
<%@ page import="attend.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();

//int EventNo = Integer.parseInt(request.getAttribute("EventNo").toString());
int EventNo = 1;
attend a = new attend(jdbc.getConn());
String result = a.getAttend(EventNo);
%>
<%= result%>