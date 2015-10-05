<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/4/2015
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<title><spring:message code="title_add_admin_account"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_add_admin_account"/>
    </h2>
</div>

<div class="box-body">

    <form class="form-input" action="addAdminAccount" method="POST">

        <%--Username--%>
        <div>
            <label><spring:message code="username"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input name="username" autocomplete="off" class="form-control username"
                           placeholder="<spring:message code="placeholder_username"/>">
                </div>
                <div class="div-error">
                    <input id="username-error" class="input-error"/>
                </div>
            </div>
        </div>
        <br>

        <%--Email--%>
        <div>
            <label><spring:message code="email"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input name="email" autocomplete="off" class="form-control email"
                           placeholder="<spring:message code="placeholder_email"/>">
                </div>
                <div class="div-error">
                    <input id="email-error" class="input-error"/>
                </div>
            </div>
        </div>
        <br>

        <%--Password--%>
        <div>
            <label><spring:message code="password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input name="password" autocomplete="off" class="form-control password"
                           placeholder="<spring:message code="placeholder_password"/>">
                </div>
                <div class="div-error">
                    <input id="password-error" class="input-error"/>
                </div>
            </div>
        </div>
        <br>

        <%--Confirm Password--%>
        <div>
            <label><spring:message code="confirm_password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input name="confirmPassword" autocomplete="off" class="form-control confirm-password"
                           placeholder="<spring:message code="placeholder_confirm_password"/>">
                </div>
                <div class="div-error">
                    <input id="confirm-password-error" class="input-error"/>
                </div>
            </div>
        </div>
        <br>

        <%--Role--%>
        <div>
            <label><spring:message code="role"/></label><br>

            <div class="div-input">
                <select name="role" class="form-control">
                    <c:forEach items="${roleList}" var="role">
                        <option value="${role.id}">${role.role}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <input type="submit" id="btn-add" class="btn btn-success" style="border-radius: 0px"
                   value="<spring:message code="btn_add"/>" onclick="">
        </div>
    </form>

</div>