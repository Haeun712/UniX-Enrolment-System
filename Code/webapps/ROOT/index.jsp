<!DOCTYPE html>
<html lang="en">

<head>
    <title>UniX - Enrolment</title>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .container form {
            display: inline-block;
            text-align: left;
            width: 300px;
        }

        h2 {
            margin-bottom: 60px
        }

        form input,
        form button {
            width: 100%;
            height: 30px;
            margin-bottom: 24px;
        }

        label {
            margin-bottom: 5px;
            display: inline-block;
        }
    </style>
</head>

<body>
    <header>
        <img src="/images/UniXLogo.jpg" height="32px" />
        <h1>Course Enrolment</h1>
    </header>

    <div class="container">
        <div>
            <h2>Log In</h2>

            <div>
                <form action="LoginServlet" method="post">
                    <label for="username">Username (e.g. c0000)</label><br>
                    <input type="text" id="username" name="username">
                    <label for="password">Password</label><br>
                    <input type="password" id="password" name="password">
                    <button type="submit">Log in</button>
                </form>
            </div>
        </div>
    </div>


    <%@ include file="/modal/error.jsp" %> 

</body>

</html>