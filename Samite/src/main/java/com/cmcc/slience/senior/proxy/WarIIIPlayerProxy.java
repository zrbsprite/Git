/**
 * File Name:WarIIIPlayerProxy.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015��4��14������11:45:53
 */
package com.cmcc.slience.senior.proxy;

/**������<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��4��14������11:45:53 <br>
 * E-mail:  <br> 
 */
public class WarIIIPlayerProxy implements IPlayer {

	private IPlayer player;
	
	public WarIIIPlayerProxy(IPlayer player){
		this.player = player;
	}
	
	/**�������ƣ�play <br>
	 * ������ <br>
	 * ���ߣ�ZRB <br>
	 * �޸����ڣ�2015��4��14������11:45:53 
	 * @see com.cmcc.slience.senior.proxy.IPlayer#play() 
	 */
	@Override
	public void play() {
		this.player.play();
	}

}
