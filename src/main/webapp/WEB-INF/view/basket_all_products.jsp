<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <jsp:include page="../include/head.jsp"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            background-color: #f1f1f1;
        }

        #navbar {
            background-color: #333;
            position: fixed;
            top: -50px;
            width: 100%;
            display: block;
            transition: top 0.3s;
        }

        #navbar a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 15px;
            text-decoration: none;
            font-size: 17px;
        }

        #navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        * {
            box-sizing: border-box;
        }

        #myInput {
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            font-size: 16px;
            padding: 12px 20px 12px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }

        #myTable {
            border-collapse: collapse;
            width: 100%;
            border: 1px solid #ddd;
            font-size: 18px;
        }

        #myTable th, #myTable td {
            text-align: left;
            padding: 12px;
        }

        #myTable tr {
            border-bottom: 1px solid #ddd;
        }

        #myTable tr.header, #myTable tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<jsp:include page="../include/sidenav.jsp"/>
<div class="main">
    <h3>List of items in the cart</h3>
    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">
    <table id="myTable">
        <tr class="header">
            <th>N/P</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Discount</th>
            <th>Total</th>
            <th>Final coast</th>
        </tr>
        <c:forEach var="basket" items="${requestScope.basketList}">
            <tr>
                <td>${basket.id}</td>
                <td><a href="basket_one_product?id=${basket.id}">${basket.nameProduct}</a></td>
                <td>$ ${basket.priceProduct}</td>
                <td>${basket.quantityProduct}</td>
                <td>${basket.discountProduct ? 'YES' : 'NO'}</td>
                <td>$ ${basket.totalPrice}</td>
                <td>$ ${basket.totalDiscount}</td>
            </tr>
        </c:forEach>
    </table>

</div>
<script>
    function myFunction() {
        let input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }

    // When the user scrolls down 20px from the top of the document, slide down the navbar
    window.onscroll = function () {
        scrollFunction()
    };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = "-50px";
        }
    }
</script>

</body>
</html>