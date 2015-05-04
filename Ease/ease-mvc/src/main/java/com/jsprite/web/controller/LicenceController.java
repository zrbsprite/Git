/**
 * File Name:LicenceController.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月20日上午9:12:32
 */
package com.jsprite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**描述：用户License处理<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月20日上午9:12:32 <br>
 * E-mail:  <br> 
 */
@Controller("licenceController")
@RequestMapping("/licen")
public class LicenceController {
	
	@RequestMapping("index")
	public String index(){
		return "license/index";
	}
	
	@RequestMapping("register")
	public String register(){
		return "";
	}
}
