<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String jspPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="HandheldFriendly" content="true" />
<meta name="MobileOptimized" content="320" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<base href="<%=jspPath%>"></base>
<link rel="stylesheet" type="text/css" href="resources/css/error.css"  media="screen" />
<link rel="icon" href="resources/images/ease.ico"></link>
<title>错误详情</title>
</head>
<body>
	<div id="da-wrapper" class="fluid">
        <!-- Content -->
        <div id="da-content">
            <!-- Container -->
            <div class="da-container">
            	<div id="da-error-wrapper">
                   	<div id="da-error-pin"></div>
                    <div id="da-error-code">
                    	error <span>Sorry</span>                    
                    </div>
                	<h1 class="da-error-heading">系统发生错误了！</h1>
                    <p><a href="" >返回首页</a></p>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div id="da-footer">
        	<div class="da-container">
           		<p>Ease . All Rights Reserved. 2015-2020</p>
           	</div>
        </div>
    </div>
</body>
</html>