<%--
  Created by IntelliJ IDEA.
  User: manlm1
  Date: 9/23/2015
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal"/>
</sec:authorize>

<!-- Logo -->
<a href="" class="logo" style="pointer-events: none; cursor: default">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"><b><spring:message code="logo_mini"/></b></span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><spring:message code="logo_full"/></span>
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"></a>

    <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="hidden-xs">
                        ${principal.username}
                    </span>
                </a>
                <ul class="dropdown-menu" style="width:auto;">
                    <li>
                        <a href="${pageContext.request.contextPath}/viewMyProfile?username=${principal.username}"
                           class="text-center">
                            <spring:message code="profile"/>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="text-center" onclick="document.getElementById('logoutForm').submit()">
                            <spring:message code="logout"/>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<form id="logoutForm" name="logoutForm" class="form-horizontal" role="form"
      action="${pageContext.request.contextPath}/j_spring_security_logout" method="POST">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

