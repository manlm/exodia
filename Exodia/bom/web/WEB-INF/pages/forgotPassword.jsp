<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/15/2015
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/forgotPassword.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title"><spring:message code="forgot_password"/></div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px">
                    <a href="${pageContext.request.contextPath}/login"><spring:message
                            code="sign_in"/></a>
                </div>
            </div>

            <div style="padding-top:30px" class="panel-body">
                <c:if test="${not empty error}">
                    <div style="display:block;" id="login-alert" class="alert alert-danger col-sm-12">
                        <spring:message code="login_failed"/>
                    </div>
                </c:if>
                <form id="forgotPasswordForm" name="forgotPasswordForm" class="form-horizontal" role="form"
                      action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">

                    <div id="div-email" style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <input id="email" type="text" class="form-control email" name="email"
                               placeholder="<spring:message code="placeholder_email"/>" minlength="1" maxlength="32"
                               required onblur="validEmail('<spring:message code="error_enter_email"/>'
                                ,'<spring:message code="error_email_pattern"/>'
                                ,<spring:message code="regex_email"/>)"
                               onkeyup="validEmail('<spring:message code="error_enter_email"/>'
                                       ,'<spring:message code="error_email_pattern"/>'
                                       ,<spring:message code="regex_email"/>)">
                    </div>

                    <div style="display: block;margin-bottom: 25px; color: red" id="email-error"></div>

                    <div style="margin-top:10px" class="form-group">
                        <!-- Button -->

                        <div class="col-sm-12 controls">
                            <input type="submit" id="btn-submit" class="btn btn-success"
                                   value="<spring:message code="btn_submit"/>" disabled>
                        </div>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
