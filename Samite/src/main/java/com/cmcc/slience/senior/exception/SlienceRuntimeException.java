package com.cmcc.slience.senior.exception;

/**
 * ���������ڿɻָ�������ʹ�ñ������쳣��CheckedException�������ڳ����������֮�ⲻ�ɻָ�������Ѿ���ɣ�ʹ������ʱ�쳣��RuntimeException��<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��3��23������2:37:36 <br> <br/>
 */
@SuppressWarnings("serial")
public class SlienceRuntimeException extends RuntimeException {
	public SlienceRuntimeException(){
		super("ϵͳ����ʱ�쳣");
	}
	
	public SlienceRuntimeException(String message){
		super(message);
	}
}
