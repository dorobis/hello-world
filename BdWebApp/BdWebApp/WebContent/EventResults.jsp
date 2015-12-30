<%@page import="java.util.Iterator"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.PrintWriter,java.util.List,java.util.Iterator,org.dorobis.prototype.Event" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="BDWebAppStyle.css" rel="stylesheet" type="text/css">
<title>Event Recorder Results</title>
</head>
<%!
List<Event> theEvents;
%>
<% 
PrintWriter writer = response.getWriter();
theEvents = (List<Event>)(request.getAttribute("eventResults"));
pageContext.setAttribute("theEvents", theEvents);
%>
<body>
<c:set var="myEvents" value="${theEvents}" />
<jsp:useBean id="myEvents" type="java.util.List<org.dorobis.prototype.Event>" />
<p>
<c:out value="Number of Table elements: ${myEvents.size()}" />
</p>
<table title="Events Recorded To Date">
	<tr>
	<th>Timestamp</th>
	<th>Message</th>
	</tr>
<c:forEach var="aEvent" items="${myEvents}" >
<jsp:useBean id="aEvent" type="org.dorobis.prototype.Event" />
	<tr>
	<td><c:out value="${aEvent.date}"/></td>
	<td><c:out value="${aEvent.title}"/></td>
	</tr>
</c:forEach>
</table>
</body>
</html>