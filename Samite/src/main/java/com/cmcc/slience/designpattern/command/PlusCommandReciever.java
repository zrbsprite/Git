/**
 * File Name:PlusCommandReciever.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午2:41:58
 */
package com.cmcc.slience.designpattern.command;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午2:41:58 <br>
 * E-mail:  <br> 
 */
public class PlusCommandReciever extends CommandReciever {

	/**方法名称：recieve <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年3月18日下午2:41:58 
	 * @see com.cmcc.slience.designpattern.command.CommandReciever#recieve(int, int) 
	 * @param x
	 * @param y
	 */
	@Override
	public void recieve(int x, int y) {
		System.out.println(x + y);
	}

}
