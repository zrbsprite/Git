/**
 * File Name:CommandClient.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午2:51:32
 */
package com.cmcc.slience.designpattern.command;

import org.junit.Test;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午2:51:32 <br>
 * E-mail:  <br> 
 */
public class CommandClient {

	@Test
	public void exeCommand() throws Exception {
		
		Command command = new PlusCommand();
		CommandInvoker invoker = new CommandInvoker(command);
		invoker.call(10, 12);
	}
}
