<%@ page import="connectDB.*"%>
<%@ page import="event.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ConnectDB jdbc = new ConnectDB();
int eventNo = 1;
EventInfo e = new EventInfo(jdbc.getConn());
String result = e.showEventInfo(eventNo);
%>
<%= result%>