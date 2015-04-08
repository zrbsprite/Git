package com.cmcc.slience.senior.exception;

/**
 * 描述：对于可恢复的条件使用被检查的异常（CheckedException），对于程序错误（言外之意不可恢复，大错已经酿成）使用运行时异常（RuntimeException）<br>
 * 作者：ZRB <br>
 * 修改日期：2015年3月23日下午2:37:36 <br> <br/>
 */
@SuppressWarnings("serial")
public class SlienceRuntimeException extends RuntimeException {
	public SlienceRuntimeException(){
		super("系统运行时异常");
	}
	
	public SlienceRuntimeException(String message){
		super(message);
	}
}
