<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
</head>

<body>

	<div class="container">

		<h1>Please input people name list separated by commas:</h1>

        <form action="people" method="post">
        People list: <input type="text" name="people">
        <input type="submit" name="submit" value="submit">
        </form>
	</div>

</body>
</html>