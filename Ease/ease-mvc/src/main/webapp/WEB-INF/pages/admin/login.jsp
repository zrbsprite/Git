<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
        <title>EASE管理后台</title>
        <meta charset="UTF-8" />
        <%@include file="../include/head.jsp" %>
        <link rel="stylesheet" href="resources/admin/css/admin-login.css" />
    </head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="#" method="post">
				 <div class="control-group normal_text"> <h3><img src="resources/admin/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" placeholder="用户名" name="userName"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="密码" name="password" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                   <!--  <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码</a></span> -->
                    <span class="pull-right"><input type="submit" class="btn btn-success"  value="登录"/></span>
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">输入邮箱地址</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="输入邮箱地址" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登陆页面</a></span>
                    <span class="pull-right"><a class="btn btn-info">接收邮件</a></span>
                </div>
            </form>
        </div>
        
        <%@include file="../include/javascript.jsp" %>
        <script src="resources/admin/js/admin.login.js"></script> 
    </body>

</html>