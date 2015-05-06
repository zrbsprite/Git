<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String jspPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=jspPath%>"></base>
<link rel="icon" href="resources/images/ease.ico">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/common.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<!--[if lt IE 9]>
      <script src="resources/js/html5shiv.min.js"></script>
      <script src="resources/js/respond.min.js"></script>
    <![endif]-->