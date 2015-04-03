package com.jsprite.core.wraper;

import com.jsprite.core.utils.LogUtils;


public class FilterDecorator implements Filter {

	public String doFilter(String msg) {
		LogUtils.info("具体的猴子类", FilterDecorator.class);
		return msg;
	}

}
