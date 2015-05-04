/**
 * File Name:LoginController.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月4日上午8:58:45
 */
package com.jsprite.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsprite.core.utils.LogUtils;
import com.jsprite.web.commons.ReturnMessage;
import com.jsprite.web.model.UserModel;

/**描述：登录处理<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月4日上午8:58:45 <br>
 * E-mail:sireezhang@163.com<br> 
 */
@Controller("loginController")
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("index")
	public String index(){
		return "login/index";
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute UserModel user, HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(user.getUserName());
		token.setPassword(user.getPassword().toCharArray());
		token.setRememberMe(user.getRememberMe());
		try {
			subject.login(token);
			return "welcom";
		} catch (AuthenticationException e) {
			LogUtils.warn("用户["+user.getUserName()+"]登录认证失败，原因是：("+e.getMessage()+")");
			ReturnMessage message = new ReturnMessage(ReturnMessage.REQUEST_STATUS_FAIL, "用户名或密码错误!");
			request.setAttribute("errorMessage", message);
			return "login/index";
		}
	}
	
	@RequestMapping("logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login/index";
	}
}
