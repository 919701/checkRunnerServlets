<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        body {
            font-family: Arial;
        }

        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>
    <jsp:include page="../include/head.jsp"/>
</head>
<body>
<jsp:include page="../include/sidenav.jsp"/>
<div class="main">
    <p>Product menu:</p>

    <div class="tab">
        <button class="tablinks" onclick="openPr(event, 'About')" id="defaultOpen">About</button>
        <button class="tablinks" onclick="openPr(event, 'Update')">Update</button>
        <button class="tablinks" onclick="openPr(event, 'Delete')">Delete</button>
    </div>


    <div id="About" class="tabcontent" style="background-color:lightgrey" ;>
        <h6>
            ID: ${requestScope.product.id}<br/>
            Name: ${requestScope.product.name}<br/>
            Price: ${requestScope.product.price}<br/>
            Promotion: ${requestScope.product.discount ? 'YES' : 'NO'}<br/>
        </h6>
    </div>

    <div id="Update" class="tabcontent" style="background-color:lightsteelblue" ;>
        <form method="POST" action="${pageContext.request.contextPath}/update_product">
            <h6>
                <label><input type="number" hidden name="idProduct" value="${requestScope.product.id}"/></label>
                <label> Name: <input type="text" name="nameProduct" value="${requestScope.product.name}"></label><br/>
                <p></p>
                <label> Price: <input type="number" name="priceProduct" value="${requestScope.product.price}" min=0
                                      placeholder="1.0" step="0.01"></label><br/>
                <p></p>
                <label>Promotion: <input type="checkbox" name="discountProduct"
                                         value="${requestScope.product.discount}"></label><br/>
                <p></p>
                <input type="submit" value="Update">
            </h6>
        </form>
    </div>

    <div id="Delete" class="tabcontent" style="background-color:indianred" ;>
        <form method="GET" action="${pageContext.request.contextPath}/delete_product">
            <h6>
                <input type="text" hidden name="idProduct" value="${requestScope.product.id}"/>
                ID: ${requestScope.product.id}<br/>
                <p></p>
                Name: ${requestScope.product.name}<br/>
                <p></p>
                Price: ${requestScope.product.price}<br/>
                <p></p>
                Promotion: ${requestScope.product.discount ? 'YES' : 'NO'}<br/>
                <p></p>
                <input type="submit" value="Delete">
            </h6>
        </form>
    </div>

</div>
<script>
    function openPr(evt, namePr) {
        let i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(namePr).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>
</body>
</html>
