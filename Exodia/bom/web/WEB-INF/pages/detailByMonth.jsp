<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/8/2015
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<title><spring:message code="title_month_detail"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_month_detail"/>: ${year}/${month}
    </h2>
</div>

<div class="box-body">

    <div style="width: 60%; margin: 0 auto">

        <table id="myTable" class="table table-striped fixed table-hover" style="width: 100%">

            <thead style="width: 100%">
            <tr style="width: 100%">
                <th style="vertical-align: middle; width: 10%; text-align: center">
                    <spring:message code="table_column_rank"/>
                </th>
                <th style="vertical-align: middle; width: 20%; text-align: center">
                    <spring:message code="table_column_score"/>
                </th>
                <th style="vertical-align: middle; width: 45%; text-align: center">
                    <spring:message code="table_column_player"/>
                </th>
                <th style="vertical-align: middle; width: 25%; text-align: center">
                    <spring:message code="table_column_play_time"/>
                </th>
            </tr>
            </thead>

            <tbody style="width: 100%">
            <c:forEach items="${scoreList}" var="score" varStatus="counter">
                <tr style="width: 100%" class="info">
                    <td style="text-align: center">${counter.count}</td>
                    <td style="text-align: center">${score.score}</td>
                    <td style="text-align: center">${score.playerAccount.email}</td>
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${score.playTime}"/>
                    <fmt:formatDate var="playTime" value="${dateValue}" pattern="yyyy/MM/dd HH:mm:ss"/>
                    <td style="text-align: center">${playTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>