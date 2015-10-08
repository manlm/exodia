<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/8/2015
  Time: 7:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/summaryData.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/tab-content/tabcontent.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tab-content/tabcontent.js"></script>


<title><spring:message code="title_summary_data"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_summary_data"/>
    </h2>
</div>

<div class="box-body">
    <ul class="tabs">
        <li><a href="#view1">${curYear -2}</a></li>
        <li><a href="#view2">${curYear -1}</a></li>
        <li><a href="#view3">${curYear}</a></li>
    </ul>
    <div class="tabcontents">

        <%--The Year Before Last Year--%>
        <div id="view1">
            <div style="width: 40%; margin: 0 auto">
                <table class="table table-striped fixed table-hover" style="width: 100%">
                    <thead style="width: 100%">
                    <th style="vertical-align: middle; width: 30%; text-align: center">
                        <spring:message code="table_column_month"/>
                    </th>
                    <th style="vertical-align: middle; width: 40%; text-align: center">
                        <spring:message code="table_column_total_play_time"/>
                    </th>
                    <th style="vertical-align: middle; width: 30%; text-align: center">
                        <spring:message code="table_column_detail"/>
                    </th>
                    </thead>

                    <tbody style="width: 100%">
                    <c:forEach items="${list3rd}" var="list3rd" varStatus="counter">
                        <tr style="width: 100%" class="info">
                            <td style="text-align: center">${counter.count}</td>
                            <td style="text-align: center">${list3rd}</td>
                            <td style="text-align: center">
                                <a href="${pageContext.request.contextPath}/viewDetailByMonth?month=${counter.count}&year=${curYear -2}"
                                   style="color: lightseagreen">
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <%--Last Year--%>
        <div id="view2">
            <div style="width: 40%; margin: 0 auto">
                <table class="table table-striped fixed table-hover" style="width: 100%">
                    <thead style="width: 100%">
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_month"/>
                    </th>
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_total_play_time"/>
                    </th>
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_detail"/>
                    </th>
                    </thead>

                    <tbody style="width: 100%">
                    <c:forEach items="${list2nd}" var="list2nd" varStatus="counter">
                        <tr style="width: 100%" class="info">
                            <td style="text-align: center">${counter.count}</td>
                            <td style="text-align: center">${list2nd}</td>
                            <td style="text-align: center">
                                <a href="${pageContext.request.contextPath}/viewDetailByMonth?month=${counter.count}&year=${curYear -1}"
                                   style="color: lightseagreen">
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <%--Current Year--%>
        <div id="view3" class="selected">
            <div style="width: 40%; margin: 0 auto">
                <table class="table table-striped fixed table-hover" style="width: 100%">
                    <thead style="width: 100%">
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_month"/>
                    </th>
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_total_play_time"/>
                    </th>
                    <th style="vertical-align: middle; width: 10%; text-align: center">
                        <spring:message code="table_column_detail"/>
                    </th>
                    </thead>

                    <tbody style="width: 100%">
                    <c:forEach items="${list1st}" var="list1st" varStatus="counter">
                        <tr style="width: 100%" class="info">
                            <td style="text-align: center">${counter.count}</td>
                            <td style="text-align: center">${list1st}</td>
                            <td style="text-align: center">
                                <a href="${pageContext.request.contextPath}/viewDetailByMonth?month=${counter.count}&year=${curYear}"
                                   style="color: lightseagreen">
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


