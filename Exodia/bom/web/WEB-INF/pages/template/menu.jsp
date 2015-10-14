<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="curPage"
       value="${fn:replace(requestScope['javax.servlet.forward.request_uri'],pageContext.request.contextPath ,'' )}"/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal"/>
</sec:authorize>

<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">
    <li class="header"><spring:message code="menu_main_menu"/></li>
    <c:if test="${account.status.id != 2}">
        <c:if test="${principal.authorities == '[ACCOUNT MANAGER]'}">
            <li class="treeview <c:if test="${curPage== '/viewAddAdminAccount' || curPage== '/viewAdminAccount'
            || curPage== '/viewEditAdminAccount'}">active</c:if>">
                <a href="">
                    <i class="fa fa-fw fa-user"></i>
                    <span><spring:message code="menu_admin_account"/></span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li <c:if test="${curPage == '/viewAddAdminAccount'}">class="active"</c:if>>
                        <a href="${pageContext.request.contextPath}/viewAddAdminAccount">
                            <i class="fa fa-plus-square"></i> <spring:message code="menu_add_admin_account"/>
                        </a>
                    </li>
                    <li <c:if test="${curPage == '/viewAdminAccount'}">class="active"</c:if>>
                        <a href="${pageContext.request.contextPath}/viewAdminAccount">
                            <i class="fa fa-list-alt"></i> <spring:message code="menu_view_admin"/>
                        </a>
                    </li>
                </ul>
            </li>
        </c:if>

        <c:if test="${principal.authorities == '[DATA MANAGER]'}">
            <li class="treeview <c:if test="${curPage== '/viewPlayerAccount' || curPage== '/viewSummaryData'
            || curPage== '/viewDetailPlayerAccount' || curPage== '/viewDetailByMonth'}">active</c:if>">
                <a href="">
                    <i class="fa fa-database"></i>
                    <span><spring:message code="menu_play_data"/></span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li <c:if test="${curPage == '/viewPlayerAccount'}">class="active"</c:if>>
                        <a href="${pageContext.request.contextPath}/viewPlayerAccount">
                            <i class="fa fa-list-alt"></i> <spring:message code="menu_view_player"/>
                        </a>
                    </li>
                    <li <c:if test="${curPage == '/viewSummaryData'}">class="active"</c:if>>
                        <a href="${pageContext.request.contextPath}/viewSummaryData">
                            <i class="fa fa-calendar-o"></i> <spring:message code="menu_view_summary_data"/>
                        </a>
                    </li>
                </ul>
            </li>
        </c:if>
    </c:if>
</ul>
