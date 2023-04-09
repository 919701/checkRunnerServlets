<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>All Product</title>
</head>
<body>
ALL PRODUCT
<ul>
    <c:forEach var="product" items="${requestScope.products}">
        <li>Name: <c:out value="${product.name}"/> Price: <c:out value="${product.price}"/></li>
        <hr/>
    </c:forEach>
</ul>
</body>
</html>
