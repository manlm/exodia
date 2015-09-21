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
    <table id="myTable" class="table table-striped">
        <thead>
        <tr>
            <th id="search-id" style="background: white"></th>
            <th id="search-username" style="background: white"></th>
            <th id="search-email" style="background: white"></th>
            <th id="search-role" style="background: white; text-align: center"></th>
            <th id="search-status" style="background: white; text-align: center"></th>
        </tr>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Email</th>
            <th style="text-align: center">Role</th>
            <th style="text-align: center">Status</th>
        </tr>
        </thead>
        <tfoot>
        <tr>

        </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.username}</td>
                <td>${account.email}</td>
                <c:if test="${account.role == 1}">
                    <td style="text-align: center">Account Manager</td>
                </c:if>
                <c:if test="${account.role == 2}">
                    <td style="text-align: center">Data Manager</td>
                </c:if>
                <c:if test="${account.status == 1}">
                    <td style="text-align: center">Active</td>
                </c:if>
                <c:if test="${account.status == 2}">
                    <td style="text-align: center">Inactive</td>
                </c:if>
                <c:if test="${account.status == 7}">
                    <td style="text-align: center">Deleted</td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script>

</script>