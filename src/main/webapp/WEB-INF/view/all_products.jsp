<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>All Product</title>
</head>
<body>
ALL PRODUCT
<table  >

    <tr>
        <th>edit</th>
        <th>delete</th>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>discount</th>
    </tr>


    <c:forEach var="product" items="${requestScope.products}">
        <tr>
            <td>
                <a href="updateExpense?id=${product.id}">Edit</a>
            </td>
            <td>
                <a href="deleteExpense?id=${product.id}">Delete</a>
            </td>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.discount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
