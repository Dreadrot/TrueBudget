<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logout page</title>
</head>
<body>
<c:if test="${cookie.username.value == null}">
<h2>You have been logged out.</h2>
<a href="home">Return home</a>
</c:if>
</body>
</html>