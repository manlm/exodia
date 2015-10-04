<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="curPage"
       value="${fn:replace(requestScope['javax.servlet.forward.request_uri'],pageContext.request.contextPath ,'' )}"/>

<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">
    <li class="header"><spring:message code="menu_main_menu"/></li>
    <li class="treeview <c:if test="${curPage== '/addAdminAccount' || curPage== '/viewAdminAccount'}">active</c:if>">
        <a href="">
            <i class="fa fa-fw fa-user"></i>
            <span><spring:message code="menu_admin_account"/></span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li <c:if test="${curPage== '/addAdminAccount'}">class="active"</c:if>>
                <a href="">
                    <i class="fa fa-plus-square"></i> <spring:message code="menu_add_admin_account"/>
                </a>
            </li>
            <li <c:if test="${curPage== '/viewAdminAccount'}">class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/viewAdminAccount">
                    <i class="fa fa-list-alt"></i> <spring:message code="menu_view_admin"/>
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-fw fa-file"></i> <span>Player Data</span>
        </a>
    </li>
</ul>
