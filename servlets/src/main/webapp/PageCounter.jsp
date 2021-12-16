<%@page contentType="text/html"%>
<html>
<head><title>A simple (transient) page counter</title></head>
<body>

<h1>A simple (transient) page counter</h1>

<%! private static int accessCount = 0; %> 

Page counter value is: <%= ++accessCount %> 

</body>
</html>
