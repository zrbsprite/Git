package com.jsprite.core.wraper;

public class TextFilter implements Filter {

	private Filter filter;
	
	public TextFilter(Filter filter) {
		this.filter = filter;
	}
	
	@Override
	public String doFilter(String msg) {
		return filter.doFilter(msg);
	}

}
