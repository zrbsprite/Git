package com.jsprite.core.exception;

@SuppressWarnings("serial")
public class EaseException extends Exception {

	public EaseException(){
		super("EASE系统异常！");
	}
	
	public EaseException(String message){
		super(message);
	}
	
	public EaseException(String message, Exception e){
		EaseException exception = new EaseException(message);
		exception.initCause(e);
	}
}
