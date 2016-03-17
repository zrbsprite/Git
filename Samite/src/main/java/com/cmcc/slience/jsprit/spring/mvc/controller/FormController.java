package com.cmcc.slience.jsprit.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmcc.slience.jsprit.spring.mvc.model.UserBean;

/**
 * 此类中不能直接使用request的对象
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/uri")
public class FormController{

	/**
	 * 从前台取值<br>
	 * 将参数封装进多个参数
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/add")
	public String addItem(String name,String password){
		System.out.println(name);
		System.out.println(password);
		return "hello";
	}
	
	/**
	 * 从前台取值<br>
	 * 将参数封装进对象<br>
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/addA")
	public ModelAndView addItemA(UserBean bean){
		System.out.println(bean.getName());
		System.out.println(bean.getPassword());
		ModelAndView view = new ModelAndView();
		view.addObject("message", "hello");
		view.setViewName("hello");
		return view;
	}
	
	/**
	 * 从前台取值<br>
	 * @RequestParam("name")适用于参数较少<br>
	 * 如果同名的值有多个这样映射
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/addB")
//	public ModelAndView addItemB(@RequestParam("name") String names){
//		System.out.println(names);
	public ModelAndView addItemB(@RequestParam("name") String[] names){
		System.out.println(names.toString());
//	public ModelAndView addItemB(@RequestParam("name") List<String> names){
//		System.out.println(names.size());
		ModelAndView view = new ModelAndView();
		view.addObject("message", "hello");
		view.setViewName("hello");
		return view;
	}
	
	/**
	 * 这里指定为bean<br>
	 * UserBean的返回类型将被处理成请求路径的对应的后缀的视图，此处就是：/uri/userInfo.jsp
	 * A<br>
	 * AB同
	 * @return
	 */
	@RequestMapping("/userInfo")
	@ModelAttribute(value="bean")
	public UserBean sendParam(){
		UserBean bean = new UserBean();
		bean.setName("庞涓");
		bean.setPassword("pangjuan");
		return bean;
	}
	
	/**
	 * B
	 * @return
	 */
	@RequestMapping("/getUser")
	public ModelAndView sendParamA(){
		UserBean bean = new UserBean();
		bean.setName("庞涓");
		bean.setPassword("pangjuan");
		ModelAndView mv = new ModelAndView();
		mv.addObject("bean", bean);
		mv.setViewName("uri/userInfo");
		return mv;
	}
}