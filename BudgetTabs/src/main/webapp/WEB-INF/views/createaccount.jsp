<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Your Account!</title>
</head>
<body>
<form:form action="adduser" commandName="addUser" method="post">
<label class="fieldLabel">Username:
	
    <form:input type="text" path="username" name="username" size="40" maxlength="32" placeholder="username" />      
<!-- error form -->
	<form:errors path="username" cssClass="error"/>

  </label>
  <br>
  <label class="fieldLabel">Password:
    <form:input type="password" path="password" name="password" size="40" maxlength="12"/>      
  </label>
  <br>
   <label class="fieldLabel"> Email:
    <form:input type="text" path="email" name="email" size="40" maxlength="32" placeholder="someone@somewhere.com" />      
  </label>
  <br>
  <div class="buttons">
    <input type="submit" name="submit" value="Create Account" />
    <input type="reset" name="reset" value="Clear Form" />
  </div>
  </form:form>
</body>
</html>