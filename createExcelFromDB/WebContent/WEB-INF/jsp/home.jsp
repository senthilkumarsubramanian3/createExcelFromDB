<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form:form action="hit" method="post" modelAttribute="home">
		<center>
			<h1>Metcheck</h1>
			<table>
				<tr>
					<td>JobID</td>
					<td><input type="number" name="jobid" required="required"></td>
				</tr>
				<tr>
					<td>Path</td>
					<td><input type="text" name="path" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="submit">
		</center>
	</form:form>
</body>
</html>
