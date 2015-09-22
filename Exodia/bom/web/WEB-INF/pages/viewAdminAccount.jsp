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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/datatables/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/viewAdminAccount.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/css/dataTables.bootstrap.min.css">


<style>
    .dataTables_filter {
        display: none;
    }
</style>

<br>

<div>
    <form id="export-form" action="${pageContext.request.contextPath}/exportAdmin" method="POST">
        <input type="button" id="btn-export" class="btn btn-success"
               value="<spring:message code="btn_export"/>" onclick="getSearchValue()">
        <input type="hidden" id="txtSearchUsername" name="txtSearchUsername" value=""/>
        <input type="hidden" id="txtSearchEmail" name="txtSearchEmail" value=""/>
        <input type="hidden" id="txtSearchRole" name="txtSearchRole" value=""/>
        <input type="hidden" id="txtSearchStatus" name="txtSearchStatus" value=""/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

<div>
    <table id="myTable" class="table table-striped">
        <thead>
        <tr>
            <th id="th-search-id" style="background: white"></th>
            <th id="th-search-username" style="background: white">
                <input id="search-username" class="form-control" type="text"
                       placeholder="<spring:message code="search_by_username"/>"/>
            </th>
            <th id="th-search-email" style="background: white">
                <input id="search_email" class="form-control" type="text"
                       placeholder="<spring:message code="search_by_emai"/>"/>
            </th>
            <th id="th-search-role" style="background: white; text-align: center"></th>
            <th id="th-search-status" style="background: white; text-align: center"></th>
        </tr>
        <tr>
            <th><spring:message code="table_column_no"/></th>
            <th><spring:message code="table_column_username"/></th>
            <th><spring:message code="table_column_email"/></th>
            <th style="text-align: center"><spring:message code="table_column_role"/></th>
            <th style="text-align: center"><spring:message code="table_column_status"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${accountList}" var="account" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${account.username}</td>
                <td>${account.email}</td>
                <c:if test="${account.role == 1}">
                    <td style="text-align: center"><spring:message code="role_account_manager"/></td>
                </c:if>
                <c:if test="${account.role == 2}">
                    <td style="text-align: center"><spring:message code="role_data_manager"/></td>
                </c:if>
                <c:if test="${account.status == 1}">
                    <td style="text-align: center"><spring:message code="status_active"/></td>
                </c:if>
                <c:if test="${account.status == 2}">
                    <td style="text-align: center"><spring:message code="status_inactive"/></td>
                </c:if>
                <c:if test="${account.status == 7}">
                    <td style="text-align: center"><spring:message code="status_deleted"/></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script>

</script>