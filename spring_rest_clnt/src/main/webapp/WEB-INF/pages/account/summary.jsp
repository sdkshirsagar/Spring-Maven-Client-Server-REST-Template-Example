<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${requestScope.status}</h3>
<form:form method="post" modelAttribute="bankAccount">
		<table style="background-color: cyan; margin: auto;" border="1">
			<tr>
				<td>Enter A/C ID</td>
				<td><form:input path="acctId" /></td>
				
			</tr>
			<tr>
				<td>Enter PIN</td>
				<td><form:password path="pin" /></td>
			
			</tr>

			<tr>
				<td><input type="submit" value="Get A/C Summary" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>