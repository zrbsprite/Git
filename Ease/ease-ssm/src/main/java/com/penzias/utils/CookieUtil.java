package com.penzias.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penzias.utils.PropertiesInitBean.PropertiesUtils;

public class CookieUtil {

	/**
	 * 获取cookie的域
	 * @return
	 */
	private static String getCookieDomainName(){
		return PropertiesUtils.getConfigByKey("cookie_domain_key");
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	/*public static void addCookie(HttpServletResponse response,String name,String value){
		DesEncrypt des = new DesEncrypt();
		value = des.getEncString(value+CookieConstant.SPLITCHAR);
		//value = Hex.encodeHexString(value.getBytes());
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		cookie.setDomain(getCookieDomainName());
		response.addCookie(cookie);
	}*/
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void removeCookie(HttpServletResponse response,String name){
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		//cookie.setDomain(getCookieDomainName());
		response.addCookie(cookie);
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = readCookieMap(request);
		if(cookieMap.containsKey(name)){
			Cookie cookie = (Cookie)cookieMap.get(name);
			return cookie;
		}else{
			return null;
		}	
	}
	
	/**
	 * 根据名字获取cookie值
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	/*public static String getCookieValueByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = readCookieMap(request);
		if(cookieMap.containsKey(name)){
			Cookie cookie = (Cookie)cookieMap.get(name);
			DesEncrypt des = new DesEncrypt();
			String value = des.getDesString(cookie.getValue());
			return value.replace(CookieConstant.SPLITCHAR, "");
		}else{
			return null;
		}	
	}*/
	
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> readCookieMap(HttpServletRequest request){	
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie : cookies){
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
