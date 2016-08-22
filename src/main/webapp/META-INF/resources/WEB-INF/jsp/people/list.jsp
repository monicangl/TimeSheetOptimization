<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<style>
table, th, td
{
margin-left: 100px;
border-collapse: collapse;
border: 1px solid black;
text-align: center;
padding: 5px 15px;
}

h1
{
margin-left: 100px;
}

</style>
</head>

<body>

	<div class="container">

		<h1>All People:</h1>

		<table class="people">
			<thead>
				<tr>
					<th>Name</th>
					<th>WorkingOffice</th>
				</tr>
			</thead>

			<c:forEach var="people" items="${people}">
				<tr>
					<td>${people.fullName}</td>
					<td>${people.workingOffice}</td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>