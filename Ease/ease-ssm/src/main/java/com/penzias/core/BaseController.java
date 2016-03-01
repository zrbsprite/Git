package com.penzias.core;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.support.RequestContext;

import com.penzias.utils.PropertiesInitBean.PropertiesUtils;

public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月11日 - 上午9:39:47<br/>
	 * 功能说明：获取国际化上下文<br/>
	 * @param request
	 * @return
	 */
	protected final RequestContext getRequestContext(HttpServletRequest request){
		return new RequestContext(request);
	}
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月11日 - 上午9:23:35<br/>
	 * 功能说明：获取国际化<br/>
	 * @param request
	 * @param key
	 * @return
	 */
	protected final String getMessage(HttpServletRequest request, String key){
		return getRequestContext(request).getMessage(key);
	}
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月2日 - 下午4:53:50<br/>
	 * 功能说明：获取每页容量<br/>
	 * @return
	 */
	protected final Integer getPageSize(){
		String pageSize = PropertiesUtils.getConfigByKey("page_per_size");
		return Integer.valueOf(pageSize);
	}
	
	@Value("#{propertiesReader[cookie_username_key]}")
	protected String cookieUserNameKey;
	
	@Value("#{propertiesReader[cookie_password_key]}")
	protected String cookiePasswordKey;
}