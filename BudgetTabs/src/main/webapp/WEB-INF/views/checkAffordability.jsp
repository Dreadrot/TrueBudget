<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calculate the Data!</title>
</head>
<body>
<h1>Aw yis!</h1>
<form:form commandName="purchaseForm" method="post">
<td>Price:</td><td><form:input type="number" path="newPurchase" size="30"/></td>
</tr>
<tr><td align = "center"><input type="submit" value="Check you Budget!"/></td></tr>
</form:form>

</body>
</html>