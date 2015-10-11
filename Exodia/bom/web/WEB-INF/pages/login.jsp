<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/12/2015
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

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
                <div class="form-top" style="border-radius: 0px">
                    <div class="form-top-left">
                        <h3>Login to our site</h3>

                        <p>Enter your username and password to log on:</p>
                    </div>
                    <div class="form-top-right">
                        <i class="fa fa-lock"></i>
                    </div>
                </div>
                <div class="form-bottom" style="border-radius: 0px">
                    <form role="form" action="j_spring_security_check" method="POST" class="login-form">
                        <div class="form-group">
                            <label class="sr-only" for="form-username"><spring:message code="username"/></label>
                            <input type="text" name="username" style="border-radius: 0px" autocomplete="off"
                                   placeholder="<spring:message code="placeholder_username"/>"
                                   class="form-username form-control username" id="form-username"
                                   value="<c:if test='${not empty loggedUsername}'>${loggedUsername}</c:if>">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="form-password"><spring:message code="password"/></label>
                            <input type="password" name="password" style="border-radius: 0px" autocomplete="off"
                                   placeholder="<spring:message code="placeholder_password"/>"
                                   class="form-password form-control" id="form-password">
                        </div>
                        <button type="submit" style="border-radius: 0px" class="btn">
                            <spring:message code="sign_in"/>
                        </button>
                        <div class="forgot-pass">
                            <a href="${pageContext.request.contextPath}/showForgotPassword"><spring:message
                                    code="forgot_password_question"/></a>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</body>

</html>


<!-- Modal -->
<div class="modal fade" id="failedModal" role="dialog" data-keyboard="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 0px">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="login_failed"/></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px">
                    <spring:message code="btn_ok"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var show = ${error};
        if (show == true) {
            $('#failedModal').modal('show');
        }
    });
</script>