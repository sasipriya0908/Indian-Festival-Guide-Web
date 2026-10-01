<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Festival Store Checkout</title>

    <link rel="stylesheet"
          href="style.css">

</head>


<body>


<header>

    <h1>
        Indian Festival Guide 🪔
    </h1>

    <p>
        Checkout
    </p>

</header>


<main style="width:80%; margin:40px auto;">

    <h2>
        🛍️ Customer Details
    </h2>


    <form
        action="OrderServlet"
        method="post">


        <label>
            Full Name:
        </label>

        <br>

        <input
            type="text"
            name="name"
            required
        >

        <br><br>


        <label>
            Email:
        </label>

        <br>

        <input
            type="email"
            name="email"
            required
        >

        <br><br>


        <label>
            Address:
        </label>

        <br>

        <textarea
            name="address"
            rows="5"
            cols="40"
            required
        ></textarea>

        <br><br>


        <button type="submit">

            Place Order 🎉

        </button>


    </form>

</main>


<footer>

    <p>
        © 2026 Indian Festival Guide
    </p>

</footer>


</body>

</html>