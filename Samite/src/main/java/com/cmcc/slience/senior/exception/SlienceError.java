package com.cmcc.slience.senior.exception;

/**
 * 描述：Error：用于指示合理的应用程序不应该试图捕获的严重问题。
 * 	         这种情况是很大的问题，大到你不能处理了，所以听之任之就行了，你不用管它。
 * 	         比如说VirtualMachineError：当 Java 虚拟机崩溃或用尽了它继续操作所需的资源时，抛出该错误。
 * 	        好吧，就算这个异常的存在了，那么应该何时，如何处理它呢？？交给JVM吧，没有比它更专业的了。<br>
 * 作者：ZRB <br>
 * 修改日期：2015年3月23日下午2:13:09 <br>
 */
@SuppressWarnings("serial")
public class SlienceError extends Error {
	
	public SlienceError(){
		super("系统发生错误");
	}
	
	public SlienceError(String error){
		super(error);
	}
}
