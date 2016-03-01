package com.penzias.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.penzias.core.Token;

/**
 * 描述：防止回退重复提交拦截器<br/>
 * 作者：data-zrb<br/>
 * 修改日期：2015年11月13日 - 下午3:07:00<br/>
 * 说明：在跳转新增的页面的controller方法上添加@Token(save=true)，在具体的保存操作上添加@Token(remove=true)注解
 */
public class TokenIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
			throws Exception{

		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if(annotation != null){
				boolean needSaveSession = annotation.save();
				if(needSaveSession){
					request.getSession(false).setAttribute("token",UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.remove();
				if(needRemoveSession){
					if(isRepeatSubmit(request)){
						request.getRequestDispatcher("/anno/out").forward(request, response);
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
			return true;
		} else{
			return super.preHandle(request,response,handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request){

		String serverToken = (String) request.getSession(false).getAttribute("token");
		if(serverToken == null){
			return true;
		}
		String clinetToken = request.getParameter("token");
		if(clinetToken == null){
			return true;
		}
		if(!serverToken.equals(clinetToken)){
			return true;
		}
		return false;
	}
}
