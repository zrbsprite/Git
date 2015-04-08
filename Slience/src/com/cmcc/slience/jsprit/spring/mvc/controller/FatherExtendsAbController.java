package com.cmcc.slience.jsprit.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * ajax的实现方式
 * @author Administrator
 *
 */
public class FatherExtendsAbController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.getWriter().print("继承AbstractController类");
		return null;
	}

}
