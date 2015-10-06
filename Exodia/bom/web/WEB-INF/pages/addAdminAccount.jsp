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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validAdminAccount.js"></script>

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
                           value="<c:if test="${not empty enteredUsername}">${enteredUsername}</c:if>"
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
                           value="<c:if test="${not empty emailExisted}">${emailExisted}</c:if>"
                           onblur="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)"
                           onkeyup="validEmail('<spring:message code="error_enter_email"/>'
                                   ,'<spring:message code="error_email_pattern"/>'
                                   ,<spring:message code="regex_email"/>)">
                </div>
                <div class="div-error">
                    <input type="text" id="email-error" class="input-error" readonly
                           value="<c:if test="${not empty emailExisted}"><spring:message code="error_email_existed"/></c:if>"/>
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
                           ,<spring:message code="regex_email"/>)">
        </div>
    </form>

</div>


<!-- Modal -->
<div class="modal fade" id="successModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="add_admin_success"/></h4>
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
        window.location = "${pageContext.request.contextPath}/viewAdminAccount";
    }

    $(document).ready(function () {
        var show = ${success};
        if (show == true) {
            $('#successModal').modal('show');
        }
    });
</script>