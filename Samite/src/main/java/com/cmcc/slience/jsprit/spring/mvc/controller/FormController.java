package com.cmcc.slience.jsprit.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmcc.slience.jsprit.spring.mvc.model.UserBean;

/**
 * �����в���ֱ��ʹ��request�Ķ���
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/uri")
public class FormController{

	/**
	 * ��ǰ̨ȡֵ<br>
	 * ��������װ���������
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
	 * ��ǰ̨ȡֵ<br>
	 * ��������װ������<br>
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
	 * ��ǰ̨ȡֵ<br>
	 * @RequestParam("name")�����ڲ�������<br>
	 * ���ͬ����ֵ�ж������ӳ��
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
	 * ����ָ��Ϊbean<br>
	 * UserBean�ķ������ͽ������������·���Ķ�Ӧ�ĺ�׺����ͼ���˴����ǣ�/uri/userInfo.jsp
	 * A<br>
	 * ABͬ
	 * @return
	 */
	@RequestMapping("/userInfo")
	@ModelAttribute(value="bean")
	public UserBean sendParam(){
		UserBean bean = new UserBean();
		bean.setName("���");
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
		bean.setName("���");
		bean.setPassword("pangjuan");
		ModelAndView mv = new ModelAndView();
		mv.addObject("bean", bean);
		mv.setViewName("uri/userInfo");
		return mv;
	}
}