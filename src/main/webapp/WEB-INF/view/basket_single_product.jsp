<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <jsp:include page="../include/head.jsp"/>
</head>
<body>
<jsp:include page="../include/sidenav.jsp"/>
<div class="main">
    <p5>Add in basket product</p5>
    <form method="POST" action="${pageContext.request.contextPath}/basket_single_product" >
        <h6>
            Name: ${requestScope.basket.nameProduct}<br/><p></p>
            <input type="text"  hidden name="name_product" value="${requestScope.basket.nameProduct}">
            Price: $ ${requestScope.basket.priceProduct}<br/> <p></p>
            <input type="text"  hidden name="price_product" value="${requestScope.basket.priceProduct}"> <p></p>
            Promotion: ${requestScope.basket.discountProduct ? 'YES' : 'NO'}<br/> <p></p>
            <input type="text"  hidden name="discount_product" value="${requestScope.basket.discountProduct}"> <p></p>
            <label>
                Quantity: <input type="number" name="quantity_product" min="0" value="${requestScope.basket.quantityProduct}">
            </label> <p></p>
            <input type="submit" value="Add in basket">
        </h6>
    </form>

</div>
</body>
</html>
