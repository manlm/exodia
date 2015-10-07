<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/6/2015
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validAdminAccount.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/editAdminAccount.js"></script>

<title><spring:message code="title_edit_admin_account"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_edit_admin_account"/>
    </h2>
</div>

<div class="box-body">

    <form id="update-form" class="form-input" action="updateAdminAccount" method="POST" autocomplete="off">

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

        <%--Last Update--%>
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
                           placeholder="<spring:message code="placeholder_email"/>"
                           value="<c:if test="${not empty emailExisted}">${emailExisted}</c:if><c:if test="${empty emailExisted}">${account.email}</c:if>"
                           onblur="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)"
                           onkeyup="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)">
                </div>
                <div class="div-error">
                    <input id="email-error" class="input-error" readonly
                           value="<c:if test='${not empty emailExisted}'><spring:message code="error_email_existed"/></c:if>"/>
                </div>
            </div>
        </div>
        <br><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="username" value="${account.username}"/>

        <div>
            <input type="button" id="btn-update" class="btn btn-success" style="border-radius: 0px"
                   value="<spring:message code="btn_update"/>"
                   onclick="validOnSubmit('<spring:message code="error_enter_email"/>'
                           ,'<spring:message code="error_email_pattern"/>'
                           ,<spring:message code="regex_email"/>
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
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px">
                    <spring:message code="btn_ok"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var creationTime = new Date(${account.creationTime});
        $('#span-creation-time').html(creationTime.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
        var lastUpdate = new Date(${account.lastUpdate});
        $('#span-last-update').html(lastUpdate.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
        var show = '${success}';
        if (show == 'true') {
            $('#successModal').modal('show');
        }
    });
</script>