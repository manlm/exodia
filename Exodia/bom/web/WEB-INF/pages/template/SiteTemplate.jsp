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
    <!-- Ionicons 2.0.0 -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
    folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath}/resources/css/_all-skins.min.css" rel="stylesheet" type="text/css"/>
    <!-- Morris chart -->
    <link href="${pageContext.request.contextPath}/resources/css/morris.css" rel="stylesheet" type="text/css"/>
    <!-- jvectormap -->
    <link href="${pageContext.request.contextPath}/resources/css/jquery-jvectormap-1.2.2.css" rel="stylesheet"
          type="text/css"/>
    <!-- Date Picker -->
    <link href="${pageContext.request.contextPath}/resources/css/datepicker3.css" rel="stylesheet" type="text/css"/>
    <!-- Daterange picker -->
    <link href="${pageContext.request.contextPath}/resources/css/daterangepicker-bs3.css" rel="stylesheet"
          type="text/css"/>
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap3-wysihtml5.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script type="text/javascript">
        $.widget.bridge('uibutton', $.ui.button);
    </script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"
            type="text/javascript"></script>
    <!-- Morris.js charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/morris.min.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.sparkline.min.js"
            type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-jvectormap-1.2.2.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-jvectormap-world-mill-en.js"
            type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.knob.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/daterangepicker.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"
            type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap3-wysihtml5.all.min.js"
            type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.min.js"
            type="text/javascript"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/resources/js/fastclick.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/resources/js/app.min.js" type="text/javascript"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">

    <script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datatables/js/dataTables.bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/datatables/css/dataTables.bootstrap.min.css">
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