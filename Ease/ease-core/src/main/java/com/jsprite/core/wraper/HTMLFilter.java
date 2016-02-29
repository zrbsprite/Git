package com.jsprite.core.wraper;

import org.apache.commons.lang.StringEscapeUtils;


public class HTMLFilter extends FilterDecorator {
	
	private Filter filter;
	
	public HTMLFilter(Filter filter) {
		super(filter);
		this.filter = filter;
	}

	@Override
	public String doFilter(String msg) {
		String temp = this.filter.doFilter(msg);
		return StringEscapeUtils.escapeHtml(temp);
	}

}
