<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<h2 align="center">Do you wanna build a budget?</h2>
<table align="center">
<tr>
<form:form commandName="newPurchase" method="post" action="canIAfford">
<td>Item Cost:</td><td><form:input type="text" path="newPurchase" size="10"/></td>
</tr><tr><td align = "center"><input type="submit" value="Enter the Price!"/></td></tr>
</form:form>
</table>

</body>
</html>
