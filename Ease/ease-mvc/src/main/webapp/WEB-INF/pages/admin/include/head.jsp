<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String jspPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="UTF-8" />
<base href="<%=jspPath%>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resources/admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/admin/css/bootstrap-responsive.min.css" />
<link href="resources/admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="icon" href="resources/images/ease.ico">
