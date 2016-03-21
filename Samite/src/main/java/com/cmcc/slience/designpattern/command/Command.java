/**
 * File Name:Command.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午2:19:03
 */
package com.cmcc.slience.designpattern.command;

/**描述：抽象的命令<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午2:19:03 <br>
 * E-mail:  <br> 
 */
public abstract class Command {

	protected CommandReciever plusCommandReciever = new PlusCommandReciever();
	
	//其他命令接收者
	
	public abstract void execute(int x, int y);
}
