package com.jsprite.web.init;

import org.springframework.context.ApplicationEvent;

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
