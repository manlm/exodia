<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 10/7/2015
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/detailPlayerAccount.js"></script>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<title><spring:message code="title_player_detail"/></title>

<div>
    <h2 class="page-title">
        <spring:message code="title_player_detail"/>
    </h2>
</div>

<div class="box-body">

    <%--Email--%>
    <div style="width: 100%">
        <label style="width: 100px"><spring:message code="email"/>:</label>
        <span>${account.email}</span>
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

    <div style="width: 100%">
        <div style="width: 100%; margin: 0 auto; text-align: center">
            <label>Player Scores</label>
        </div>
        <div style="width: 60%; margin: 0 auto">
            <table id="myTable" class="table table-striped fixed table-hover" style="width: 100%">
                <thead style="width: 100%">
                <th style="vertical-align: middle; width: 10%">
                    <spring:message code="table_column_no"/>
                </th>
                <th style="vertical-align: middle; width: 40%">
                    <spring:message code="table_column_score"/>
                </th>
                <th style="vertical-align: middle; width: 50%">
                    <spring:message code="table_column_play_time"/>
                </th>
                </thead>

                <tbody style="width: 100%">
                <c:forEach items="${scoreList}" var="score" varStatus="counter">
                    <tr style="width: 100%" class="info">
                        <td>${counter.count}</td>
                        <td>${score.score}</td>
                        <jsp:useBean id="dateValue" class="java.util.Date"/>
                        <jsp:setProperty name="dateValue" property="time" value="${score.playTime}"/>
                        <fmt:formatDate var="playTime" value="${dateValue}" pattern="yyyy/MM/dd HH:mm:ss"/>
                        <td>${playTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var creationTime = new Date(${account.creationTime});
        $('#span-creation-time').html(creationTime.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
        var lastUpdate = new Date(${account.lastUpdate});
        $('#span-last-update').html(lastUpdate.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
    });

    function convertTime(time, tag) {
        alert("call");
        var time = new Date(time);
        alert(time);
        $('#' + tag).html(time.customFormat("#YYYY#/#MM#/#DD# #hh#:#mm#:#ss#"));
    }
</script>