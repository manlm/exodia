<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/6/2015
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/firstLogin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>

<title><spring:message code="title_first_login"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_first_login"/>
    </h2>
</div>

<div class="box-body">

    <form id="first-login-form" class="form-input" action="updateFirstLogin" method="POST" autocomplete="off">

        <%--Username--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="username"/>:</label>
            <span>${account.username}</span>
        </div>

        <%--Email--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="email"/>:</label>
            <span>${account.email}</span>
        </div>

        <%--Creation Time--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="creation_time"/>:</label>
            <jsp:useBean id="creationTime" class="java.util.Date"/>
            <jsp:setProperty name="creationTime" property="time" value="${account.creationTime}"/>
            <fmt:formatDate var="creationTime" value="${creationTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
            <span id="span-creation-time">${creationTime}</span>
        </div>

        <%--Last Update--%>
        <div style="width: 100%">
            <label style="width: 100px"><spring:message code="last_update"/>:</label>
            <jsp:useBean id="lastUpdate" class="java.util.Date"/>
            <jsp:setProperty name="lastUpdate" property="time" value="${account.lastUpdate}"/>
            <fmt:formatDate var="lastUpdate" value="${lastUpdate}" pattern="yyyy/MM/dd HH:mm:ss"/>
            <span id="span-last-update">${lastUpdate}</span>
        </div>
        <br>

        <%--Old Password--%>
        <div>
            <label><spring:message code="old_password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input type="password" id="old-password" name="oldPassword" autocomplete="off"
                           class="form-control old-password" maxlength="32"
                           placeholder="<spring:message code="placeholder_password"/>"
                           onblur="validOldPassword('<spring:message code="error_enter_password"/>'
                                   ,'<spring:message code="error_password_length"/>'
                                   ,'<spring:message code="error_password_pattern"/>'
                                   ,<spring:message code="regex_password"/>)"
                           onkeyup="validOldPassword('<spring:message code="error_enter_password"/>'
                                   ,'<spring:message code="error_password_length"/>'
                                   ,'<spring:message code="error_password_pattern"/>'
                                   ,<spring:message code="regex_password"/>)">
                </div>
                <div class="div-error">
                    <input id="old-password-error" class="input-error" readonly
                           value="<c:if test="${updateResult == 'wrongPassword'}"><spring:message code="error_wrong_password"/></c:if>"/>
                </div>
            </div>
        </div>
        <br>

        <%--Password--%>
        <div>
            <label><spring:message code="new_password"/></label><br>

            <div class="div-wrapper">
                <div class="div-input">
                    <input type="password" id="new-password" name="newPassword" autocomplete="off"
                           class="form-control new-password" maxlength="32"
                           placeholder="<spring:message code="placeholder_password"/>"
                           onblur="validNewPassword('<spring:message code="error_enter_password"/>'
                                   ,'<spring:message code="error_password_length"/>'
                                   ,'<spring:message code="error_password_pattern"/>'
                                   ,<spring:message code="regex_password"/>);
                                   validConfirmPassword('<spring:message code="error_confirm_password"/>')"
                           onkeyup="validNewPassword('<spring:message code="error_enter_password"/>'
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
                    <input type="password" id="confirm-password" name="confirmPassword" autocomplete="off"
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
        <br><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="username" value="${account.username}"/>


        <div>
            <input type="button" id="btn-update" class="btn btn-success" style="border-radius: 0px"
                   value="<spring:message code="btn_update"/>"
                   onclick="validOnSubmit('<spring:message code="error_enter_password"/>'
                           ,'<spring:message code="error_password_length"/>'
                           ,'<spring:message code="error_password_pattern"/>'
                           ,
                   <spring:message code="regex_password"/>
                           ,'<spring:message code="error_confirm_password"/>'
                           ,'<spring:message code="btn_process"/>')">
        </div>

    </form>

</div>

<!-- Modal -->
<div class="modal fade" id="successModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="update_success"/></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px"
                        onclick="redirectPage()">
                    <spring:message code="btn_ok"/>
                </button>
            </div>
        </div>
    </div>
</div>


<script>
    function redirectPage() {
        window.location = "${pageContext.request.contextPath}/main";
    }

    $(document).ready(function () {
        var show = '${updateResult}';
        if (show == 'success') {
            $('#successModal').modal('show');
        }
    });
</script>