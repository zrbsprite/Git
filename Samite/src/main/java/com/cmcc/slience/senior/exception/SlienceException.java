package com.cmcc.slience.senior.exception;


@SuppressWarnings("serial")
public class SlienceException extends Exception{

	public SlienceException(){
		super("ϵͳ�����쳣");
	}
	
	public SlienceException(String errorMessage){
		super(errorMessage);
	}
}
