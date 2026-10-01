<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>

<%

String productId =
    request.getParameter("productId");

String productName =
    request.getParameter("productName");

String price =
    request.getParameter("price");


List<Map<String, String>> cart =
    (List<Map<String, String>>)
    session.getAttribute("cart");


if (cart == null) {

    cart =
        new ArrayList<Map<String, String>>();

    session.setAttribute(
        "cart",
        cart
    );
}


if (productId != null) {

    Map<String, String> item =
        new HashMap<String, String>();

    item.put(
        "id",
        productId
    );

    item.put(
        "name",
        productName
    );

    item.put(
        "price",
        price
    );

    cart.add(item);
}

%>


<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Shopping Cart</title>

    <link rel="stylesheet"
          href="style.css">

</head>


<body>


<header>

    <h1>
        Indian Festival Guide 🪔
    </h1>

    <p>
        Your Shopping Cart
    </p>

</header>


<main style="width:90%; margin:40px auto;">

    <h2>
        🛒 Shopping Cart
    </h2>


<%

if (cart.isEmpty()) {

%>

    <p>
        Your cart is empty.
    </p>

    <a href="ProductServlet">
        Continue Shopping
    </a>

<%

} else {

    double total = 0;

%>

    <table
        border="1"
        cellpadding="12"
        cellspacing="0"
        width="100%"
    >

        <tr>

            <th>Product</th>

            <th>Price</th>

        </tr>


<%

    for (Map<String, String> item : cart) {

        double itemPrice =
            Double.parseDouble(
                item.get("price")
            );

        total += itemPrice;

%>

        <tr>

            <td>
                <%= item.get("name") %>
            </td>

            <td>
                ₹ <%= item.get("price") %>
            </td>

        </tr>

<%

    }

%>

        <tr>

            <th>
                Total
            </th>

            <th>
                ₹ <%= total %>
            </th>

        </tr>

    </table>


    <br><br>

    <a href="ProductServlet">

        <button>
            Continue Shopping
        </button>

    </a>


    <a href="checkout.jsp">

        <button>
            Proceed to Checkout
        </button>

    </a>

<%

}

%>

</main>


<footer>

    <p>
        © 2026 Indian Festival Guide
    </p>

</footer>


</body>

</html>