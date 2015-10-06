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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/myProfile.js"></script>

<title><spring:message code="title_edit_admin_account"/></title>

<style>
    .borderless td, .borderless th .borderless tr {
        border: none;
    }
</style>

<div>
    <h2 class="page-title">
        <spring:message code="title_edit_admin_account"/>
    </h2>
</div>

<div class="box-body">

    <form id="update-form" class="form-input" action="updateProfile" method="POST" autocomplete="off">

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
                           placeholder="<spring:message code="placeholder_email"/>"
                           value="${account.email}"
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