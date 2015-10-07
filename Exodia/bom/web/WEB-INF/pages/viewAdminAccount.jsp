<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/20/2015
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/viewAdminAccount.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<title><spring:message code="title_admin_account"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_admin_account"/>
    </h2>
</div>

<div class="box-body">
    <div>
        <form id="export-form" action="${pageContext.request.contextPath}/exportAdmin" method="POST">
            <button type="button" class="btn btn-success" aria-label="Left Align" style="border-radius: 0px"
                    onclick="getSearchValue()">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>
                <spring:message code="btn_export"/>
            </button>
            <input type="hidden" id="txtSearchUsername" name="txtSearchUsername" value=""/>
            <input type="hidden" id="txtSearchEmail" name="txtSearchEmail" value=""/>
            <input type="hidden" id="txtSearchRole" name="txtSearchRole" value=""/>
            <input type="hidden" id="txtSearchStatus" name="txtSearchStatus" value=""/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div style="width: 100%">
        <table id="myTable" class="table table-striped fixed table-hover" style="width: 100%">
            <thead style="width: 100%">
            <tr style="width: 100%">
                <th style="width: 5%"></th>
                <th id="th-search-username" style="width: 18%">
                    <input id="search-username" class="form-control" type="text" style="width:100%;"
                           placeholder="<spring:message code="search_by_username"/>"/>
                </th>
                <th id="th-search-email" style=" width: 32%">
                    <input id="search_email" class="form-control" type="text" style="width:100%;"
                           placeholder="<spring:message code="search_by_emai"/>"/>
                </th>
                <th id="th-search-role" style="text-align: center; width: 13%"></th>
                <th id="th-search-status" style="text-align: center; width: 12%"></th>
                <th style="width: 5%"></th>
                <th style="width: 5%"></th>
                <th style="width: 5%"></th>
                <th style="width: 5%"></th>
            </tr>
            <tr style="width: 100%" class="danger">
                <th style="vertical-align: middle">
                    <spring:message code="table_column_no"/>
                </th>
                <th style="vertical-align: middle">
                    <spring:message code="table_column_username"/>
                </th>
                <th style="vertical-align: middle">
                    <spring:message code="table_column_email"/>
                </th>
                <th style="text-align: center; vertical-align: middle">
                    <spring:message code="table_column_role"/>
                </th>
                <th style="text-align: center; vertical-align: middle">
                    <spring:message code="table_column_status"/>
                </th>
                <th style="text-align: center; vertical-align: middle">
                    <spring:message code="table_column_access_log"/>
                </th>
                <th style="text-align: center; vertical-align: middle">
                    <spring:message code="table_column_resend_email"/>
                </th>
                <th style="text-align: center; ;vertical-align: middle">
                    <spring:message code="table_column_edit"/>
                </th>
                <th style="text-align: center; ;vertical-align: middle">
                    <spring:message code="table_column_delete"/>
                </th>
            </tr>
            </thead>

            <tbody style="width: 100%">
            <c:forEach items="${accountList}" var="account" varStatus="counter">
                <tr style="width: 100%" class="info">
                    <td>${counter.count}</td>
                    <td>${account.username}</td>
                    <td>${account.email}</td>
                    <td style="text-align: center">
                        <c:if test="${account.role.id == 1}">
                            <spring:message code="role_account_manager"/>
                        </c:if>
                        <c:if test="${account.role.id == 2}">
                            <spring:message code="role_data_manager"/>
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${account.status.id == 1}">
                            <spring:message code="status_active"/>
                        </c:if>
                        <c:if test="${account.status.id == 2}">
                            <spring:message code="status_inactive"/>
                        </c:if>
                        <c:if test="${account.status.id == 7}">
                            <spring:message code="status_deleted"/>
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <a href="#" onclick="downloadLog('${account.username}')"
                           style="color: green">
                            <span class="glyphicon glyphicon-download-alt"></span>
                        </a>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${account.status.id == 2}">
                            <a href="#" onclick="resendEmail('${account.username}')"
                               style="color: black">
                                <span class="glyphicon glyphicon-envelope"></span>
                            </a>
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <a href="${pageContext.request.contextPath}/viewEditAdminAccount?username=${account.username}"
                           style="color: lightseagreen">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${account.status.id != 7}">
                            <a href="#" onclick="deleteAccount('${account.username}')"
                               style="color: red">
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<form id="resendEmailForm" action="resendEmail" method="POST">
    <input id="resend-username" name="username" type="hidden">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form id="deleteForm" action="deleteAdminAccount" method="POST">
    <input id="delete-username" name="username" type="hidden">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form id="accessLogForm" action="downloadAccessLog" method="POST">
    <input id="log-username" name="username" type="hidden">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<!-- Inform Modal -->
<div class="modal fade" id="myModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 0px">
            <div class="modal-body" style="border-bottom: 0px">
                <h4 id="popup-message"></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px">
                    <spring:message code="btn_ok"/>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Confirm Resend Email Modal -->
<div class="modal fade" id="resendModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 0px">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="resend_email"/></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" style="border-radius: 0px" onclick="submitResendEmail()">
                    <spring:message code="btn_ok"/>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px">
                    <spring:message code="btn_cancel"/>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Confirm Delete Modal -->
<div class="modal fade" id="deleteModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 0px">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="delete_account"/></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" style="border-radius: 0px" onclick="submitResendEmail()">
                    <spring:message code="btn_ok"/>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" style="border-radius: 0px">
                    <spring:message code="btn_cancel"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var show = '${popup}';
        if (show == 'sendEmailSuccess') {
            $('#myModal').modal('show');
            $("#popup-message").html("<spring:message code="resend_active_success"/>");
        } else if (show == 'deleteSuccess') {
            $('#myModal').modal('show');
            $("#popup-message").html("<spring:message code="delete_success"/>");
        }
    });
</script>