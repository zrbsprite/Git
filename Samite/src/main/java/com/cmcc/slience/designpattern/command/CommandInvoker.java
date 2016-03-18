/**
 * File Name:CommandInvoker.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午2:43:46
 */
package com.cmcc.slience.designpattern.command;

/**描述：命令调用者<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午2:43:46 <br>
 * E-mail:  <br> 
 */
public class CommandInvoker {

	private Command command;
	
	public CommandInvoker(Command command){
		this.command = command;
	}
	
	public void call(int x, int y){
		command.execute(x, y);
	}
}
