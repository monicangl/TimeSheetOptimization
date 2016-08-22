<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<style>
table, th, td
{
border-collapse: collapse;
border: 1px solid black;
text-align: center;
padding: 5px 15px;
}
</style>
</head>

<body>

	<div class="container">

		<h1>All People</h1>

        <c:forEach var="office" items="${people}">
        	<h2>${office.name}: ${office.peopleCount}</h1>

            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>

                <c:forEach var="people" items="${office.people}">
                    <tr>
                        <td>${people.fullName}</td>
                    </tr>
                </c:forEach>
            </table>

        </c:forEach>
	</div>

</body>
</html>