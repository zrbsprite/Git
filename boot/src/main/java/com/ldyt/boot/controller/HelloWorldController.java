package com.ldyt.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2017年4月11日上午10:49:19 <br>
 * @RestController是一类特殊的@Controller，它的返回值直接作为HTTP Response的Body部分返回给浏览器
 */
@RestController
@RequestMapping("demo")
@EnableAutoConfiguration
public class HelloWorldController {

	/**
	 * <b>描述：</b>Hello world<br>
	 * <b>作者:</b> ZRB
	 * <b>修改日期：</b>2017年4月11日上午9:37:47
	 * @return
	 */
	@RequestMapping("hello")
	public String sayHello(){
		return "Hello everyone!";
	}
}
