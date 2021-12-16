<%@page contentType="text/html" import="java.util.*,java.text.*"%>
<html>
<head><title>Server Date - JSP Version</title></head>
<body>
<h1>Server Date - JSP Version</h1>

<%
    Date now = new Date();
    String time = DateFormat.getTimeInstance().format(now);
    String date = DateFormat.getDateInstance().format(now);
    out.println("doing wonderful things here");
%>

The current server date is <strong><%= date %></strong> , the time is <strong><%= time %></strong>.
The request has been made by <strong><%= request.getRemoteHost() %></strong>.
<p>
The user agent is: <strong><%= request.getHeader("user-agent")%></strong>
</body>
</html>
