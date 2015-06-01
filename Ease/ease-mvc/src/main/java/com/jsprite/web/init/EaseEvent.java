package com.jsprite.web.init;

import org.springframework.context.ApplicationEvent;

/**
 * 描述：自定义的spring事件<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月31日下午7:36:01 <br>
 * E-mail:  <br>
 */
@SuppressWarnings("serial")
public class EaseEvent extends ApplicationEvent {

	private String content;
	
	public EaseEvent(Object source) {
		super(source);
	}

	public EaseEvent(Object source, String content){
		super(source);
		
	}
	
	public String getContent(){
		return this.content;
	}
}
