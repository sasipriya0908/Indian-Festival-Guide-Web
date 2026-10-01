<!DOCTYPE html>
<html>
<head>
    <title>Indian Festival Registration</title>

    <link rel="stylesheet" href="style.css">
</head>

<body>

<header>
    <h1>Indian Festivals Guide 🪔</h1>
</header>

<main>

    <section class="registration-section">

        <h2>Festival Registration</h2>

        <form action="RegisterServlet" method="post">

            <label>Registration ID:</label>
            <input type="text"
                   name="registrationId"
                   value="FEST<%= (int)(Math.random() * 9000 + 1000) %>"
                   readonly>

            <br><br>

            <label>Full Name:</label>
            <input type="text"
                   name="fullName"
                   required>

            <br><br>

            <label>Email:</label>
            <input type="email"
                   name="email"
                   required>

            <br><br>

            <label>Phone:</label>
            <input type="text"
                   name="phone"
                   required>

            <br><br>

            <label>Age:</label>
            <input type="number"
                   name="age"
                   required>

            <br><br>

            <label>Favourite Festival:</label>

            <select name="favouriteFestival" required>

                <option value="">Select Festival</option>

                <option value="Diwali">Diwali</option>

                <option value="Holi">Holi</option>

                <option value="Navratri">Navratri</option>

                <option value="Eid">Eid</option>

                <option value="Christmas">Christmas</option>

                <option value="Ganesh Chaturthi">
                    Ganesh Chaturthi
                </option>

                <option value="Pongal">Pongal</option>

                <option value="Onam">Onam</option>

                <option value="Raksha Bandhan">
                    Raksha Bandhan
                </option>

            </select>

            <br><br>

            <button type="submit">
                Register
            </button>

        </form>

    </section>

</main>

</body>
</html>