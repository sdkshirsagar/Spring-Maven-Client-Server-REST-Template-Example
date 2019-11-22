<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

	<form:form method="post" modelAttribute="vendor123">
		<table style="background-color: cyan; margin: auto;" border="1">
			<tr>
				<td>Vendor ID</td>
				<td><form:input path="id" readonly="true" /></td>
			</tr>
			<tr>
				<td>Vendor Email</td>
				<td><form:input path="email" readonly="true" /></td>
			</tr>
			<tr>
				<td>Vendor Name</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Vendor City</td>
				<td><form:input path="city" /></td>
			</tr>
			<tr>
				<td>Vendor Phone No</td>
				<td><form:input path="phoneNo" /></td>
			</tr>
			<tr>
				<td>Vendor Reg Amount</td>
				<td><form:input path="regAmount" /></td>
			</tr>
			<tr>
				<td>Vendor Reg Date</td>
				<td><form:input type="date" path="regDate" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Update Vendor" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html> --%>