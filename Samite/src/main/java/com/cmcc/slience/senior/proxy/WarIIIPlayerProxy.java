/**
 * File Name:WarIIIPlayerProxy.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月14日上午11:45:53
 */
package com.cmcc.slience.senior.proxy;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月14日上午11:45:53 <br>
 * E-mail:  <br> 
 */
public class WarIIIPlayerProxy implements IPlayer {

	private IPlayer player;
	
	public WarIIIPlayerProxy(IPlayer player){
		this.player = player;
	}
	
	/**方法名称：play <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月14日上午11:45:53 
	 * @see com.cmcc.slience.senior.proxy.IPlayer#play() 
	 */
	@Override
	public void play() {
		this.player.play();
	}

}
