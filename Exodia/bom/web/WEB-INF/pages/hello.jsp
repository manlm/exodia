<a href="#" onclick="document.getElementById('logoutForm').submit();">Logout</a>

<form id="logoutForm" name="loginform" class="form-horizontal" role="form"
      action="${pageContext.request.contextPath}/j_spring_security_logout" method="POST">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>