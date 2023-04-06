<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>All Product</title>
</head>
<body>
ALL PRODUCT
<ul>
    <c:forEach var="user" items="${users}">
        <li><c:out value="${user}" /></li>
    </c:forEach>
</ul>
</body>
</html>
