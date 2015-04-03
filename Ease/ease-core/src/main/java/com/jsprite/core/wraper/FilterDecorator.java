package com.jsprite.core.wraper;

public class FilterDecorator implements Filter {

	private Filter filter;
	
	public FilterDecorator(Filter filter){
		this.filter = filter;
	}
	
	public String doFilter(String msg) {
		return this.filter.doFilter(msg);
	}

}
