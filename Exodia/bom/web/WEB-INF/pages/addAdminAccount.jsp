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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addAdminAccount.js"></script>

<title><spring:message code="title_add_admin_account"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_add_admin_account"/>
    </h2>
</div>

<div class="box-body">

    <form id="add-form" class="form-input" action="addAdminAccount" method="POST" autocomplete="off">

        <%--Username--%>
        <div style="width: 100%">
            <label><spring:message code="username"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input id="username" name="username" autocomplete="off" class="form-control username"
                           maxlength="256"
                           placeholder="<spring:message code="placeholder_username"/>"
                           onblur="validUsername('<spring:message code="error_enter_username"/>')"
                           onkeyup="validUsername('<spring:message code="error_enter_username"/>')">
                </div>
                <div class="div-error">
                    <input id="username-error" class="input-error" readonly/>
                </div>
            </div>
        </div>
        <br>

        <%--Email--%>
        <div>
            <label><spring:message code="email"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input id="email" name="email" autocomplete="off" class="form-control email" maxlength="254"
                           placeholder="<spring:message code="placeholder_email"/>"
                           onblur="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)"
                           onkeyup="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)">
                </div>
                <div class="div-error">
                    <input id="email-error" class="input-error" readonly/>
                </div>
            </div>
        </div>
        <br>

        <%--Password--%>
        <div>
            <label><spring:message code="password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input type="password" id="password" name="password" autocomplete="off"
                           class="form-control password" maxlength="32"
                           placeholder="<spring:message code="placeholder_password"/>"
                           onblur="validPassword('<spring:message code="error_enter_password"/>'
                                   ,'<spring:message code="error_password_length"/>'
                                   ,'<spring:message code="error_password_pattern"/>'
                                   ,<spring:message code="regex_password"/>);
                                   validConfirmPassword('<spring:message code="error_confirm_password"/>')"
                           onkeyup="validPassword('<spring:message code="error_enter_password"/>'
                                   ,'<spring:message code="error_password_length"/>'
                                   ,'<spring:message code="error_password_pattern"/>'
                                   ,<spring:message code="regex_password"/>);
                                   validConfirmPassword('<spring:message code="error_confirm_password"/>')">
                </div>
                <div class="div-error">
                    <input id="password-error" class="input-error" readonly/>
                </div>
            </div>
        </div>
        <br>

        <%--Confirm Password--%>
        <div>
            <label><spring:message code="confirm_password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input type="password" id="confirmPassword" name="confirmPassword" autocomplete="off"
                           class="form-control confirm-password" maxlength="32"
                           placeholder="<spring:message code="placeholder_confirm_password"/>"
                           onblur="validConfirmPassword('<spring:message code="error_confirm_password"/>')"
                           onkeyup="validConfirmPassword('<spring:message code="error_confirm_password"/>')">
                </div>
                <div class="div-error">
                    <input id="confirm-password-error" class="input-error" readonly/>
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
        <br><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <input type="button" id="btn-add" class="btn btn-success" style="border-radius: 0px"
                   value="<spring:message code="btn_add"/>"
                   onclick="validOnSubmit('<spring:message code="error_enter_username"/>'
                           ,'<spring:message code="error_enter_email"/>'
                           ,'<spring:message code="error_email_pattern"/>'
                           ,<spring:message code="regex_email"/>
                           ,'<spring:message code="error_enter_password"/>'
                           ,'<spring:message code="error_password_length"/>'
                           ,'<spring:message code="error_password_pattern"/>'
                           ,<spring:message code="regex_password"/>)
                           ,'<spring:message code="error_confirm_password"/>'">
        </div>
    </form>

</div>