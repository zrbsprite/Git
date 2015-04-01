package com.jsprite.core.wraper;

import org.springframework.web.util.HtmlUtils;


public class HTMLFilter extends FilterDecorator {
	
	private Filter filter;
	
	public HTMLFilter(Filter filter) {
		super(filter);
		this.filter = filter;
	}

	@Override
	public String doFilter(String msg) {
		String temp = this.filter.doFilter(msg);
		return HtmlUtils.htmlEscape(temp, "UTF-8");
	}

}
