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

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<title><spring:message code="title_admin_account"/></title>

<div>
    <h2 style="margin-top: 0px; font-size: x-large; font-weight: bold"><spring:message code="title_admin_account"/></h2>
</div>

<div class="box-body">
    <div>
        <form id="export-form" action="${pageContext.request.contextPath}/exportAdmin" method="POST">
            <input type="button" id="btn-export" class="btn btn-success" style="border-radius: 0px"
                   value="<spring:message code="btn_export"/>" onclick="getSearchValue()">
            <input type="hidden" id="txtSearchUsername" name="txtSearchUsername" value=""/>
            <input type="hidden" id="txtSearchEmail" name="txtSearchEmail" value=""/>
            <input type="hidden" id="txtSearchRole" name="txtSearchRole" value=""/>
            <input type="hidden" id="txtSearchStatus" name="txtSearchStatus" value=""/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div style="width: 100%">
        <table id="myTable" class="table table-striped" style="width: 100%">
            <thead style="width: 100%">
            <tr style="width: 100%">
                <th></th>
                <th id="th-search-username" style="width: 23%">
                    <input id="search-username" class="form-control" type="text" style="width:100%;"
                           placeholder="<spring:message code="search_by_username"/>"/>
                </th>
                <th id="th-search-email" style=" width: 31%">
                    <input id="search_email" class="form-control" type="text" style="width:100%;"
                           placeholder="<spring:message code="search_by_emai"/>"/>
                </th>
                <th id="th-search-role" style="text-align: center; width: 15%"></th>
                <th id="th-search-status" style="text-align: center; width: 15%"></th>
            </tr>
            <tr style="width: 100%" class="danger">
                <th style="width: 1%; vertical-align: middle">
                    <spring:message code="table_column_no"/>
                </th>
                <th style="width: 22%; vertical-align: middle">
                    <spring:message code="table_column_username"/>
                </th>
                <th style="width: 34%; vertical-align: middle">
                    <spring:message code="table_column_email"/>
                </th>
                <th style="text-align: center; width: 14%; vertical-align: middle">
                    <spring:message code="table_column_role"/>
                </th>
                <th style="text-align: center; width: 9%; vertical-align: middle">
                    <spring:message code="table_column_status"/>
                </th>
                <th style="text-align: center; width: 5%; vertical-align: middle">
                    <spring:message code="table_column_access_log"/>
                </th>
                <th style="text-align: center; width: 5%; vertical-align: middle">
                    <spring:message code="table_column_resend_email"/>
                </th>
                <th style="text-align: center; width: 5%;vertical-align: middle">
                    <spring:message code="table_column_edit"/>
                </th>
                <th style="text-align: center; width: 5%;vertical-align: middle">
                    <spring:message code="table_column_delete"/>
                </th>
            </tr>
            </thead>

            <tbody style="width: 100%">
            <c:forEach items="${accountList}" var="account" varStatus="counter">
                <tr style="width: 100%" class="info">
                    <td style="width: 1%">${counter.count}</td>
                    <td style="width: 25%">${account.username}</td>
                    <td style="width: 35%">${account.email}</td>
                    <td style="text-align: center; width: 15%">
                        <c:if test="${account.role == 1}">
                            <spring:message code="role_account_manager"/>
                        </c:if>
                        <c:if test="${account.role == 2}">
                            <spring:message code="role_data_manager"/>
                        </c:if>
                    </td>
                    <td style="text-align: center; width: 9%">
                        <c:if test="${account.status == 1}">
                            <spring:message code="status_active"/>
                        </c:if>
                        <c:if test="${account.status == 2}">
                            <spring:message code="status_inactive"/>
                        </c:if>
                        <c:if test="${account.status == 7}">
                            <spring:message code="status_deleted"/>
                        </c:if>
                    </td>
                    <td style="text-align: center; width: 5%">
                        <a href="" style="color: green">
                            <span class="glyphicon glyphicon-download-alt"></span>
                        </a>
                    </td>
                    <td style="text-align: center; width: 5%">
                        <a href="" style="color: black">
                            <span class="glyphicon glyphicon-envelope"></span>
                        </a>
                    </td>
                    <td style="text-align: center; width: 5%">
                        <a href="" style="color: lightseagreen">
                            <span class="glyphicon glyphicon-edit"></span>
                        </a>
                    </td>
                    <td style="text-align: center; width: 5%">
                        <a href="" style="color: red">
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


