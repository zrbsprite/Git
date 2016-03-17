/**
 * File Name:TestProxy.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015��4��14������11:47:57
 */
package com.cmcc.slience.test.proxy;

import com.cmcc.slience.senior.proxy.DynamicProxy;
import com.cmcc.slience.senior.proxy.IPlayer;
import com.cmcc.slience.senior.proxy.PlayerHandler;
import com.cmcc.slience.senior.proxy.WarIIIPlayer;
import com.cmcc.slience.senior.proxy.WarIIIPlayerProxy;

/**������<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��4��14������11:47:57 <br>
 * E-mail:  <br> 
 */
public class TestProxy {

	public static void main(String[] args) {
		simpleProxy();
		dynamicProxy();
	}

	/**��������: SimpleProxy<br>
	 * ������
	 * ����: ZRB
	 * �޸����ڣ�2015��4��14������11:49:46
	 */
	private static void simpleProxy() {
		IPlayer player = new WarIIIPlayer();
		IPlayer proxyPlayer = new WarIIIPlayerProxy(player);
		proxyPlayer.play();
	}
	
	private static void dynamicProxy(){
		IPlayer player = new WarIIIPlayer();
		PlayerHandler handler = new PlayerHandler(player);
		IPlayer proxy = DynamicProxy.getProxy(player.getClass(), handler);
		proxy.play();
	} 
}
