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

 <c:if test="${cookie.username.value == null}">
Oh man, this is overwhelming. Let's start simple.
<a href="login">Login, I hope.</a>
<a href="createaccount">Create a user. Maybe.</a>
 </c:if>
 <c:if test="${cookie.username.value != null}">
 <a href="checkAffordability">Check to see if the item works!</a>
  <li><a href="logout">Logout</a></li>
 </c:if>
</body>
</html>
