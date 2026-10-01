<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Indian Festival Store</title>

    <link rel="stylesheet"
          href="style.css">

    <style>

        .store-container {
            width: 90%;
            margin: 40px auto;
        }

        .store-title {
            text-align: center;
            color: #cc3300;
            font-size: 38px;
            margin-bottom: 30px;
        }

        .product-grid {
            display: grid;
            grid-template-columns:
                repeat(auto-fit, minmax(250px, 1fr));

            gap: 25px;
        }

        .product-card {
            background: white;
            border-radius: 15px;
            padding: 20px;
            text-align: center;
            box-shadow:
                0 5px 15px rgba(0,0,0,0.15);
        }

        .product-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 10px;
        }

        .product-card h2 {
            color: #cc3300;
        }

        .price {
            font-size: 22px;
            font-weight: bold;
            color: #008000;
        }

        .buy-btn {
            background: #ff6600;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 8px;
            cursor: pointer;
        }

        .buy-btn:hover {
            background: #cc3300;
        }

        .top-links {
            text-align: center;
            margin-bottom: 30px;
        }

        .top-links a {
            margin: 10px;
        }

    </style>

</head>

<body>

<header>

    <h1>
        Indian Festival Guide 🪔
    </h1>

    <p>
        Indian Festival Store
    </p>

</header>


<div class="store-container">

    <h1 class="store-title">
        🛍️ Indian Festival Store
    </h1>


    <div class="top-links">

        <a href="index.jsp">
            Home
        </a>

        |

        <a href="cart.jsp">
            🛒 View Cart
        </a>

    </div>


    <div class="product-grid">

<%

List<Map<String, Object>> products =
    (List<Map<String, Object>>)
    request.getAttribute("products");

if (products != null) {

    for (Map<String, Object> product : products) {

%>

        <div class="product-card">

            <img
                src="<%= product.get("image") %>"
                alt="<%= product.get("name") %>"
            >

            <h2>
                <%= product.get("name") %>
            </h2>

            <p>
                Festival:
                <%= product.get("festival") %>
            </p>

            <p>
                <%= product.get("description") %>
            </p>

            <p class="price">
                ₹ <%= product.get("price") %>
            </p>


            <form action="cart.jsp"
                  method="post">

                <input
                    type="hidden"
                    name="productId"
                    value="<%= product.get("id") %>"
                >

                <input
                    type="hidden"
                    name="productName"
                    value="<%= product.get("name") %>"
                >

                <input
                    type="hidden"
                    name="price"
                    value="<%= product.get("price") %>"
                >

                <button
                    type="submit"
                    class="buy-btn">

                    Add to Cart 🛒

                </button>

            </form>

        </div>

<%

    }

}

%>

    </div>

</div>


<footer>

    <p>
        © 2026 Indian Festivals Guide |
        Festival Store
    </p>

</footer>

</body>

</html>