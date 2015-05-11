<%@page import="com.jsprite.web.commons.ReturnMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../head.jsp" %>
<title>用户登录</title>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Ease</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <!-- <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul> -->
      <!-- <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form> -->
      <!-- <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul> -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="wrap clearfix">
	<div class="login-show">
		<img alt="EASE" src="resources/images/person.png">
	</div>
	<div id="login-content" class="clearfix">
		<form class="form-horizontal" id="mainForm" action="login/login.htm" method="post">
		  	<div class="text-center">
		  		<h4 class="text-muted">欢迎登录</h4>
		  	</div>
		  	<div class="center-block">
		  		<img alt="EASE" src="resources/images/avatar_164x164.png"  class="img-circle img-responsive center-block">
		  	</div>
		  	<div class="form-group"></div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label sr-only">UserName:</label>
		    <div class="col-sm-8">
		      <input type="text" class="form-control" id="input_username" name="userName" placeholder="UserName">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label sr-only">Password:</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" id="input_password" name="password" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-8">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox" name="rememberMe"> <p class="text-muted">记住我</p>
		        </label>
		        <label class="float-right">
		          <a class="text-info no-underline" href="javascript:void(0);"><span>忘记密码？</span></a>
		        </label>
		      </div>
		    </div>
		  </div>
		  <%
		  ReturnMessage errorMessage = (ReturnMessage)request.getAttribute("errorMessage");
		  	if(errorMessage!=null){
  		  %>
		  	<div class="form-group" id="errorMessage">
			    <div class="col-sm-offset-2 col-sm-10">
			      <p class="text-danger"><%=errorMessage.getMessage()%></p>
			    </div>
		  	</div>
		  <%
		  	}
		  %>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">登	录</button>
		    </div>
		  </div>
		</form>
	</div>
</div>
<footer id="foot">
	<div class="container">
		<p class="text-muted">Ease . All Rights Reserved. 2015-2015</p>
	</div>
</footer>
<%@include file="../tail.jsp" %>
</body>
</html>