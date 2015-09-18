<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/12/2015
  Time: 9:25 AM
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title"><spring:message code="sign_in"/></div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px">
                    <a href="${pageContext.request.contextPath}/forgotPassword"><spring:message
                            code="forgot_password_question"/></a>
                </div>
            </div>

            <div style="padding-top:30px" class="panel-body">
                <c:if test="${not empty error}">
                    <div style="display:block;" id="login-alert" class="alert alert-danger col-sm-12">
                        <spring:message code="login_failed"/>
                    </div>
                </c:if>
                <form id="loginform" name="loginform" class="form-horizontal" role="form"
                      action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">

                    <div id="div-username" style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login-username" type="text" class="form-control username" name="username"
                               placeholder="<spring:message code="placeholder_username"/>" minlength="1" maxlength="256"
                               required onblur="validUsername('<spring:message code="error_enter_username"/>')"
                               onkeyup="validUsername('<spring:message code="error_enter_username"/>')">
                    </div>

                    <div style="display: block;margin-bottom: 25px; color: red" id="username-error"></div>

                    <div id="div-password" style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="login-password" type="password" class="form-control password" name="password"
                               placeholder="<spring:message code="placeholder_password"/>" minlength="8" maxlength="32"
                               required onblur="validPassword('<spring:message code="error_enter_password"/>'
                                ,'<spring:message code="error_password_length"/>'
                                ,'<spring:message code="error_password_pattern"/>'
                                ,<spring:message code="regex_password"/>)"
                               onkeyup="validPassword('<spring:message code="error_enter_password"/>'
                                       ,'<spring:message code="error_password_length"/>'
                                       ,'<spring:message code="error_password_pattern"/>'
                                       ,<spring:message code="regex_password"/>)">
                    </div>

                    <div style="display: none;margin-bottom: 25px; color: red" id="password-error"></div>

                    <div style="margin-top:10px" class="form-group">
                        <!-- Button -->

                        <div class="col-sm-12 controls">
                            <input type="submit" id="btn-login" class="btn btn-success"
                                   value="<spring:message code="btn_login"/>" disabled>
                        </div>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>