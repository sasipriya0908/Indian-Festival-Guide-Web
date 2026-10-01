<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Festival Feedback | Indian Festival Guide</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .feedback-wrap{width:90%;max-width:700px;margin:40px auto;background:#fff;padding:30px;border-radius:16px;box-shadow:0 5px 18px rgba(0,0,0,.12)}
        .feedback-wrap h2{text-align:center;color:#cc3300}
        .feedback-wrap label{display:block;margin-top:15px;font-weight:bold}
        .feedback-wrap input,.feedback-wrap select,.feedback-wrap textarea{width:100%;padding:11px;margin-top:7px;border:1px solid #ccc;border-radius:8px;box-sizing:border-box;font-size:15px}
        .feedback-wrap textarea{min-height:120px;resize:vertical}
        .feedback-wrap button{margin-top:20px;padding:12px 24px;background:#ff6600;color:#fff;border:0;border-radius:8px;cursor:pointer;font-weight:bold}
        .feedback-wrap button:hover{background:#cc3300}
        .feedback-links{text-align:center;margin-top:25px}
        .feedback-links a{margin:0 8px}
        .success{background:#e8f7e8;color:#267326;padding:12px;border-radius:8px;margin-bottom:18px;text-align:center}
    </style>
</head>
<body>
<header>
    <h1>Indian Festival Guide 🪔</h1>
    <p>Share your experience with our festival guide</p>
</header>

<div class="feedback-wrap">
    <h2>💬 Festival Feedback Form</h2>

    <% if ("true".equals(request.getParameter("success"))) { %>
        <div class="success">Thank you! Your feedback was saved to the XML database.</div>
    <% } %>

    <form action="FeedbackServlet" method="post">
        <label for="fullName">1. Full Name</label>
        <input id="fullName" type="text" name="fullName" required>

        <label for="email">2. Email</label>
        <input id="email" type="email" name="email" required>

        <label for="festival">3. Favourite Festival</label>
        <select id="festival" name="festival" required>
            <option value="">Select Festival</option>
            <option>Diwali</option>
            <option>Holi</option>
            <option>Navratri</option>
            <option>Eid</option>
            <option>Christmas</option>
            <option>Ganesh Chaturthi</option>
            <option>Pongal</option>
            <option>Onam</option>
            <option>Raksha Bandhan</option>
        </select>

        <label for="rating">4. Rating (1 to 5)</label>
        <select id="rating" name="rating" required>
            <option value="">Select Rating</option>
            <option value="1">1 - Poor</option>
            <option value="2">2 - Fair</option>
            <option value="3">3 - Good</option>
            <option value="4">4 - Very Good</option>
            <option value="5">5 - Excellent</option>
        </select>

        <label for="comments">5. Comments / Suggestions</label>
        <textarea id="comments" name="comments" required></textarea>

        <button type="submit">Submit Feedback</button>
    </form>

    <div class="feedback-links">
        <a href="xml/feedbacks.xml">View XSLT Summary</a>
        |
        <a href="XPathSearchServlet?minRating=3">Search Rating &gt; 3</a>
        |
        <a href="index.jsp">Back to Home</a>
    </div>
</div>

<footer>
    <p>© 2026 Indian Festivals Guide | Celebrate the Spirit of India</p>
</footer>
</body>
</html>
