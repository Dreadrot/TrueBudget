<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<form:form commandName="loginForm" method="post">
<td>Username:</td><td><form:input type="text" path="username" size="30"/></td>
</tr><tr>
<td>Password:</td><td><form:input path="password" type="password" size="30"/></td>
</tr><tr><td align = "center"><input type="submit" value="Login!"/></td></tr>
</form:form>
</body>
</html>