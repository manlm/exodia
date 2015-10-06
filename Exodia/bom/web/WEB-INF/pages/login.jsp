<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/12/2015
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Exodia - Login </title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-roboto.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">


    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>
<div style="position: fixed;
                        top: 50%;
                        left: 50%;
                        -webkit-transform: translate(-50%, -50%);
                        transform: translate(-50%, -50%);">
    <div style="width: 560px">
        <div>
            <div class="form-box">
                <div class="form-top">
                    <div class="form-top-left">
                        <h3>Login to our site</h3>

                        <p>Enter your username and password to log on:</p>
                    </div>
                    <div class="form-top-right">
                        <i class="fa fa-lock"></i>
                    </div>
                </div>
                <div class="form-bottom">
                    <form role="form" action="" method="post" class="login-form">
                        <div class="form-group">
                            <label class="sr-only" for="form-username">Username</label>
                            <input type="text" name="form-username" placeholder="Username..."
                                   class="form-username form-control" id="form-username">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="form-password">Password</label>
                            <input type="password" name="form-password" placeholder="Password..."
                                   class="form-password form-control" id="form-password">
                        </div>
                        <button type="submit" class="btn">Sign in!</button>
                        <div class="forgot-pass">
                            <a href="#">Forgot password?</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Javascript -->
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>

</body>

</html>