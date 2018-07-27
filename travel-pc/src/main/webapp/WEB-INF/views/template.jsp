<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>travel</title>
    <link href="${css}" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${contextpath}js/jquery-3.1.0.min.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />
</body>
</html>
<script type="text/javascript" src="${js}"></script>