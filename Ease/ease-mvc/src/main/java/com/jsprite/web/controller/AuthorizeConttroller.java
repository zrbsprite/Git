/**
 * File Name:AuthorizeConttoller.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月6日下午5:47:13
 */
package com.jsprite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月6日下午5:47:13 <br>
 * E-mail:  <br> 
 */
@Controller("authorizeConttroller")
@RequestMapping("/")
public class AuthorizeConttroller {

	@RequestMapping("unAuthc")
	public String unAuthc(){
		return "unauthorize";
	}
}
