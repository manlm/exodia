<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC - Tiles Integration tutorial</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.4.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/screen.css" type="text/css"
          media="screen, projection">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/print.css" type="text/css"
          media="print">
    <!--[if IE]>
    <link rel="stylesheet" href="/resources/css/ie.css" type="text/css" media="screen, projection">
    <![endif]-->
    <style>
        body {
            margin-top: 20px;
            margin-bottom: 20px;
            background-color: #DFDFDF;
        }
    </style>
</head>
<body>
<div class="container" style="border: #C1C1C1 solid 1px; border-radius:10px;">
    <!-- Header -->
    <tiles:insertAttribute name="header"/>
    <!-- Menu Page -->
    <div class="span-5  border" style="height:400px;background-color:#FCFCFC;">
        <tiles:insertAttribute name="menu"/>
    </div>
    <!-- Body Page -->
    <div class="span-19 last">
        <tiles:insertAttribute name="body"/>
    </div>
    <!-- Footer Page -->
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>