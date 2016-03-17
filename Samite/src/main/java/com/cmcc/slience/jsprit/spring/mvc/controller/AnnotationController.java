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
		// ���ģ������ �����������POJO����
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("hello");
		
		return mv;
	}
	
	/**
	 * ��ͨuriģʽӳ��
	 * @return
	 */
	@RequestMapping(value={"/annotation1","/parent/annotation1"})
	public ModelAndView showA(){
		ModelAndView mv = new ModelAndView();
		// ���ģ������ �����������POJO����
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("hello");
		
		return mv;
	}
	
	/**
	 * URI ģ��ģʽӳ�� 
	 * @return
	 */
	@RequestMapping(value="/parent/{userId}")
	public ModelAndView showB(){
		ModelAndView mv = new ModelAndView();
		// ���ģ������ �����������POJO����
		mv.addObject("message", "Hello World!");
		// �����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("hello");
		
		return mv;
	}
	
	//@RequestMapping(value="/users/**")������ƥ�䡰/users/abc/abc�� ������/users/123�����ᱻ��URIģ��ģʽӳ���еġ�/users/{userId}��ģʽ����ӳ�䵽�� �����4.14���ƥ�����ȡ� �� 
	//@RequestMapping(value="/product?")�� ��ƥ�� ��/product1�� �� ��/producta�� �� ����ƥ�� ��/product�� �� ��/productaa�� ; 
	//@RequestMapping(value="/product*")����ƥ�䡰/productabc����/product�� ������ƥ�䡰/productabc/abc��; 
	//@RequestMapping(value="/product/*")����ƥ�䡰/product/abc�� ������ƥ�䡰/productabc��; 
	//@RequestMapping(value="/products/**/{productId}")����ƥ�䡰/products/abc/abc/123����/products/123�� ��Ҳ����Ant����URIģ��������ɻ���;
	
	// 	?ƥ��һ���ַ�����/index? ����ƥ�� /index1 �� ������ƥ�� /index �� /index12 
	// 	*ƥ����������ַ�����/index1/*������ƥ��/index1/demo������ƥ��/index1/demo/demo 
	//	**   ƥ���������·������/index2/**������ƥ��/index2 ·���µ�������·������ƥ��/index2/demo����
	//	/index2/demo/demo 
	//	�����������ģʽ���� Spring ��ѡ����һ��ִ���أ����ҵ�����Ϊ��/long/long��ʱ������ʾ�� 
	//	/long/long 
	//	/long/**/abc 
	//	/long/** 
	//	/** 
	//	Spring �� AbstractUrlHandlerMapping ʹ�ã��ƥ�����ȣ� 
	//	������Ϊ ��/long/long��  ��ƥ���һ�� ��/long/long�� �� ������ ��/long/acd��  ��ƥ�� ��/long/**�� �� ������ ��/long/aa/abc�� ,��ƥ�䡰/long/**/abc�� ��������/abc����ƥ�䡰/**��
	 
	
	/**
	 * ����ʹpost����Ҳ������get����</br>
	 * �ض���
	 * @return
	 */
	@RequestMapping(value="/uri/{userId}",method = {RequestMethod.POST, RequestMethod.GET})
	public String goRedirect(){
		return "redirect:/annotation.htm";
	}
	
	/**
	 * ����ʹpost����Ҳ������get����</br>
	 * �����Ƿ���String���ͣ�ָ��hello.jsp
	 * @return
	 */
	@RequestMapping(value="/parent/redirectA",method = {RequestMethod.POST, RequestMethod.GET})
	public String goRedirectA(){
		return "hello";
	}
}
