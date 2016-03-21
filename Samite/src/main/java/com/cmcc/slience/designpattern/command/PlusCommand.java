/**
 * File Name:PlusCommand.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午2:37:21
 */
package com.cmcc.slience.designpattern.command;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午2:37:21 <br>
 * E-mail:  <br> 
 */
public class PlusCommand extends Command {

	/**方法名称：execute <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年3月18日下午2:37:21 
	 * @see com.cmcc.slience.designpattern.command.Command#execute(int, int) 
	 * @param x
	 * @param y
	 */
	@Override
	public void execute(int x, int y) {
		super.plusCommandReciever.recieve(x, y);
	}

}
