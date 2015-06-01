package com.jsprite.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsprite.core.data.Dictionary;
import com.jsprite.core.data.UserStatus;
import com.jsprite.core.utils.EncryptUtils;
import com.jsprite.web.model.UserModel;
import com.jsprite.web.service.UserService;

@Controller("adminLoginController")
@RequestMapping(name="/admin")
public class LoginController {

	private UserService userService;
	
	@RequestMapping(name="index")
	public String index(){
		return "admin/login";
	}
	
	@RequestMapping("login")
	public ModelAndView login(@ModelAttribute UserModel user, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("admin/login");
		ModelMap map = new ModelMap();
		if(StringUtils.isEmpty(user.getUserName())){
			map.put("errorMsg", "用户名不能为空");
			view.addAllObjects(map);
			return view;
		}else if(StringUtils.isEmpty(user.getPassword())){
			map.put("errorMsg", "密码不能为空");
			view.addAllObjects(map);
			return view;
		}else{
			String pwd = user.getPassword();
			pwd = EncryptUtils.getMD5(pwd);
			UserModel model = this.userService.findUser(user);
			if(!pwd.equals(model.getPassword())){
				map.put("errorMsg", "用户名或密码错误");
				view.addAllObjects(map);
				return view;
			}else if(model.getUserStatus()==UserStatus.USER_STATUS_LOCKED){
				map.put("errorMsg", "用户名被锁定");
				view.addAllObjects(map);
				return view;
			}
			//用户正常登录
			request.getSession().setAttribute(Dictionary.ADMIN_SESSION_USER_KEY, model);
		}
		view.setViewName("admin/welcome");
		return view;
	}
	
	@RequestMapping(name="logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Dictionary.ADMIN_SESSION_USER_KEY);
		return "admin/login";
	}
	
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
