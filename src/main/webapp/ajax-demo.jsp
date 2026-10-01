<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AJAX Festival Explorer</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .ajax-box { width: 85%; max-width: 900px; margin: 40px auto; padding: 30px; background: #fff8ea; border-radius: 18px; box-shadow: 0 5px 18px rgba(0,0,0,.15); text-align: center; }
        .ajax-box input, .ajax-box select, .ajax-box button { padding: 12px; margin: 8px; border-radius: 8px; }
        .ajax-box input, .ajax-box select { width: 280px; border: 1px solid #ccc; }
        .ajax-box button { border: none; cursor: pointer; background: #ff7a00; color: white; font-weight: bold; }
        #ajax-result { margin-top: 25px; padding: 20px; min-height: 100px; text-align: left; border-radius: 12px; background: white; }
        .ajax-card { padding: 15px; border-left: 5px solid #ff9933; background: #fffaf0; border-radius: 10px; }
    </style>
</head>
<body>
<header>
    <h1>Indian Festivals Guide 🪔</h1>
    <p>AJAX Festival Explorer</p>
</header>

<main>
    <div class="ajax-box">
        <h2>Dynamic Festival Search using AJAX</h2>
        <p>Load festival information from the Servlet, XML and JSP without refreshing the page.</p>

        <input type="text" id="festival" placeholder="Enter festival name">

        <div>
            <button type="button" id="servletBtn">Load from Servlet</button>
            <button type="button" id="xmlBtn">Load from XML</button>
            <button type="button" id="jspBtn">Load from JSP</button>
        </div>

        <div id="ajax-result">AJAX response will appear here.</div>

        <br>
        <a href="registration.jsp">Go to Festival Registration</a>
        <br><br>
        <a href="index.jsp">Back to Home</a>
    </div>
</main>

<footer>
    <p>© 2026 Indian Festivals Guide | AJAX Experiment</p>
</footer>

<script src="ajax-demo.js"></script>
</body>
</html>
