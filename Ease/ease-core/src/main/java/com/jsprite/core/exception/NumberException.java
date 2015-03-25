package com.jsprite.core.exception;

@SuppressWarnings("serial")
public class NumberException extends EaseException{
	public NumberException(){
		super("非有效的数字类型");
	}
	
	public NumberException(String message){
		super(message);
	}
}
