<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/6/2015
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/myProfile.js"></script>

<title><spring:message code="title_my_profile"/></title>

<style>
    .borderless td, .borderless th .borderless tr {
        border: none;
    }
</style>

<div>
    <h2 class="page-title">
        <spring:message code="title_my_profile"/>
    </h2>
</div>

<div class="box-body">

    <form id="profile-form" class="form-input" action="updateProfile" method="POST" autocomplete="off">

        <%--Username--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="username"/>:</label>
            <span>${account.username}</span>
        </div>

        <%--Creation Time--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="creation_time"/>:</label>
            <span id="span-creation-time"></span>
        </div>

        <%--Creation Time--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="last_update"/>:</label>
            <span id="span-last-update"></span>
        </div>
        <br>

        <%--Email--%>
        <div>
            <label><spring:message code="email"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input id="email" name="email" autocomplete="off" class="form-control email" maxlength="254"
                           value="${account.email}"
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
            <label><spring:message code="old_password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input type="password" id="old-password" name="old-password" autocomplete="off"
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
                    <input id="old-password-error" class="input-error" readonly/>
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

    </form>

</div>

<script>
    $(document).ready(function () {
        var creationTime = new Date(${account.creationTime});
        $('#span-creation-time').html(creationTime.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
        var lastUpdate = new Date(${account.lastUpdate});
        $('#span-last-update').html(lastUpdate.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
    });
</script>