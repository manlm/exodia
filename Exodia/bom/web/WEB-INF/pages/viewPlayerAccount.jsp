<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/6/2015
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/viewPlayerAccount.js"></script>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<title><spring:message code="title_player_account"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_player_account"/>
    </h2>
</div>

<div class="box-body">

    <div>
        <form id="export-form" action="${pageContext.request.contextPath}/exportPlayer" method="POST">
            <button type="button" class="btn btn-success" aria-label="Left Align" style="border-radius: 0px"
                    onclick="getSearchValue()">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>
                <spring:message code="btn_export"/>
            </button>
            <input type="hidden" id="txtSearchEmail" name="txtSearchEmail" value=""/>
            <input type="hidden" id="txtSearchStatus" name="txtSearchStatus" value=""/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div style="width: 80%; margin: 0 auto">
        <table id="myTable" class="table table-striped fixed table-hover" style="width: 100%">
            <thead style="width: 100%">
            <tr style="width: 100%">
                <th style="width: 7%"></th>
                <th id="th-search-email" style="width: 64%">
                    <input id="search-email" class="form-control" type="text" style="width:100%;"
                           placeholder="<spring:message code="search_by_email"/>"/>
                </th>
                <th id="th-search-status" style="text-align: center; width: 15%"></th>
                <th style="width: 7%"></th>
                <th style="width: 7%"></th>
            </tr>
            <tr style="width: 100%" class="danger">
                <th style="vertical-align: middle">
                    <spring:message code="table_column_no"/>
                </th>

                <th style="vertical-align: middle">
                    <spring:message code="table_column_email"/>
                </th>

                <th style="text-align: center; vertical-align: middle">
                    <spring:message code="table_column_status"/>
                </th>
                <th style="text-align: center; ;vertical-align: middle">
                    <spring:message code="table_column_detail"/>
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

                    <td>${account.email}</td>

                    <td style="text-align: center">
                        <c:if test="${account.status.id == 1}">
                            <spring:message code="status_active"/>
                        </c:if>
                        <c:if test="${account.status.id == 7}">
                            <spring:message code="status_deleted"/>
                        </c:if>
                    </td>

                    <td style="text-align: center">
                        <c:if test="${account.status.id != 7}">
                            <a href="${pageContext.request.contextPath}/viewDetailPlayerAccount?email=${account.email}"
                               style="color: lightseagreen">
                                <span class="glyphicon glyphicon-info-sign"></span>
                            </a>
                        </c:if>
                    </td>

                    <td style="text-align: center">
                        <c:if test="${account.status.id != 7}">
                            <a href="#" onclick="deleteAccount('${account.email}')"
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

<form id="deleteForm" action="deletePlayerAccount" method="POST">
    <input id="delete-email" name="email" type="hidden">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<!-- Confirm Delete Modal -->
<div class="modal fade" id="deleteModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" style="width: 300px; margin: 0 auto">

        <!-- Modal content-->
        <div class="modal-content" style="border-radius: 0px">
            <div class="modal-body" style="border-bottom: 0px">
                <h4><spring:message code="delete_account"/></h4>
            </div>
            <div class="modal-footer" style="border-top: 0px">
                <button type="button" class="btn btn-default" style="border-radius: 0px"
                        onclick="submitDeleteAccount()">
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
        if (show == 'deleteSuccess') {
            $('#myModal').modal('show');
            $("#popup-message").html("<spring:message code="delete_success"/>");
        }
    });
</script>