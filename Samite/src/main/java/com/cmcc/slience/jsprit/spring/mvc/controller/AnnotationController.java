package com.cmcc.slience.jsprit.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/parent")
//** /parent/annotation
public class AnnotationController {

	@RequestMapping("/annotation")
	public ModelAndView showInfo(){
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		
		return mv;
	}
	
	/**
	 * 普通uri模式映射
	 * @return
	 */
	@RequestMapping(value={"/annotation1","/parent/annotation1"})
	public ModelAndView showA(){
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		
		return mv;
	}
	
	/**
	 * URI 模板模式映射 
	 * @return
	 */
	@RequestMapping(value="/parent/{userId}")
	public ModelAndView showB(){
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		
		return mv;
	}
	
	//@RequestMapping(value="/users/**")：可以匹配“/users/abc/abc” ，但“/users/123”将会被【URI模板模式映射中的“/users/{userId}”模式优先映射到】 【详见4.14的最长匹配优先】 。 
	//@RequestMapping(value="/product?")： 可匹配 “/product1” 或 “/producta” ， 但不匹配 “/product” 或 “/productaa” ; 
	//@RequestMapping(value="/product*")：可匹配“/productabc”或“/product” ，但不匹配“/productabc/abc”; 
	//@RequestMapping(value="/product/*")：可匹配“/product/abc” ，但不匹配“/productabc”; 
	//@RequestMapping(value="/products/**/{productId}")：可匹配“/products/abc/abc/123”或“/products/123” ，也就是Ant风格和URI模板变量风格可混用;
	
	// 	?匹配一个字符，如/index? 可以匹配 /index1 ， 但不能匹配 /index 或 /index12 
	// 	*匹配零个或多个字符，如/index1/*，可以匹配/index1/demo，但不匹配/index1/demo/demo 
	//	**   匹配零个或多个路径，如/index2/**：可以匹配/index2 路径下的所有子路径，如匹配/index2/demo，或
	//	/index2/demo/demo 
	//	如果我有如下模式，那 Spring 该选择哪一个执行呢？当我的请求为“/long/long”时如下所示： 
	//	/long/long 
	//	/long/**/abc 
	//	/long/** 
	//	/** 
	//	Spring 的 AbstractUrlHandlerMapping 使用：最长匹配优先； 
	//	如请求为 “/long/long”  将匹配第一个 “/long/long” ， 但请求 “/long/acd”  则将匹配 “/long/**” ， 如请求 “/long/aa/abc” ,则匹配“/long/**/abc” ，如请求“/abc”则将匹配“/**”
	 
	
	/**
	 * 可以使post方法也可以是get方法</br>
	 * 重定向
	 * @return
	 */
	@RequestMapping(value="/uri/{userId}",method = {RequestMethod.POST, RequestMethod.GET})
	public String goRedirect(){
		return "redirect:/annotation.htm";
	}
	
	/**
	 * 可以使post方法也可以是get方法</br>
	 * 可以是返回String类型，指向hello.jsp
	 * @return
	 */
	@RequestMapping(value="/parent/redirectA",method = {RequestMethod.POST, RequestMethod.GET})
	public String goRedirectA(){
		return "hello";
	}
}
