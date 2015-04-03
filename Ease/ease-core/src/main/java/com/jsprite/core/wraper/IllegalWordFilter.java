package com.jsprite.core.wraper;

/**
 * 支持正则表达式
 * @author JSprite
 */
public class IllegalWordFilter extends TextFilter {

	private Filter filter;

	private String[] arrays;

	public IllegalWordFilter(Filter filter) {
		super(filter);
		this.filter = filter;
	}

	public IllegalWordFilter(Filter filter, String[] arrays) {
		super(filter);
		this.filter = filter;
		this.arrays = arrays;
	}

	@Override
	public String doFilter(String msg) {
		String temp = this.filter.doFilter(msg);
		if(null!=arrays){
			for (String key : arrays) {
				temp = temp.replace(key, "*");
			}
		}
		
		return temp;
	}

}
