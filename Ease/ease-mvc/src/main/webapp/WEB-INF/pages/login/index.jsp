<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../head.jsp" %>
<title>用户登录</title>
</head>
<body>
<div class="container">
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">UserName:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="input_username" placeholder="UserName">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label">Password:</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="input_password" placeholder="Password">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox"> 记住我的登录状态
	        </label>
	      </div>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">登	录</button>
	    </div>
	  </div>
</form>
</div>
<%@include file="../tail.jsp" %>
</body>
</html>