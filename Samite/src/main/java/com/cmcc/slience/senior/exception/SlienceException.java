package com.cmcc.slience.senior.exception;


@SuppressWarnings("serial")
public class SlienceException extends Exception{

	public SlienceException(){
		super("系统运行异常");
	}
	
	public SlienceException(String errorMessage){
		super(errorMessage);
	}
}
