<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- FontAwesome 4.3.0 -->
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>

    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
    folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/resources/css/_all-skins.min.css" rel="stylesheet" type="text/css"/>

    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"
            type="text/javascript"></script>

    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/app.min.js" type="text/javascript"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datatables/js/dataTables.bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/datatables/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>


<body class="skin-green sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <!-- Header -->
        <tiles:insertAttribute name="header"/>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <!-- Menu Page -->
            <div>
                <tiles:insertAttribute name="menu"/>
            </div>
        </section>
    </aside>
    <!-- Body Page -->
    <div class="content-wrapper" style="background: gainsboro">
        <section class="content">
            <tiles:insertAttribute name="body"/>
        </section>
    </div>
    <footer class="main-footer">
        <!-- Footer Page -->
        <tiles:insertAttribute name="footer"/>
    </footer>
</div>
</body>
</html>