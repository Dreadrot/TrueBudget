<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
Oh man, this is overwhelming. Let's start simple.
<a href="login">Login, I hope.</a>
<a href="createaccount">Create a user. Maybe.</a>
 <c:if test="${cookie.username.value != null}">
  <li><a href="logout">Logout</a></li>
 </c:if>
</body>
</html>
