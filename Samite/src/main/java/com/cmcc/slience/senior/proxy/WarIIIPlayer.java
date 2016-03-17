/**
 * File Name:WarIIIPlayer.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月14日上午11:44:54
 */
package com.cmcc.slience.senior.proxy;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月14日上午11:44:54 <br>
 * E-mail:  <br> 
 */
public class WarIIIPlayer implements IPlayer {

	/**方法名称：play <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月14日上午11:44:54 
	 * @see com.cmcc.slience.senior.proxy.IPlayer#play() 
	 */
	@Override
	public void play() {
		System.out.println("I play warIII!");
	}

}
