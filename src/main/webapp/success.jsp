<!DOCTYPE html>
<html>

<head>

    <title>Registration Successful</title>

    <link rel="stylesheet" href="style.css">

</head>

<body>

<header>

    <h1>Indian Festivals Guide 🪔</h1>

</header>

<main>

    <section>

        <h2>Registration Successful!</h2>

        <p>
            Thank you for registering with
            Indian Festival Guide.
        </p>

        <h3>
            Your Registration ID:
        </h3>

        <h2>
            <%= request.getParameter("registrationId") %>
        </h2>

        <br>

        <a href="index.jsp">
            Back to Indian Festival Guide
        </a>

    </section>

</main>

</body>

</html>