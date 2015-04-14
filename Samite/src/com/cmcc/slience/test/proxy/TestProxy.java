/**
 * File Name:TestProxy.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月14日上午11:47:57
 */
package com.cmcc.slience.test.proxy;

import com.cmcc.slience.senior.proxy.DynamicProxy;
import com.cmcc.slience.senior.proxy.IPlayer;
import com.cmcc.slience.senior.proxy.PlayerHandler;
import com.cmcc.slience.senior.proxy.WarIIIPlayer;
import com.cmcc.slience.senior.proxy.WarIIIPlayerProxy;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月14日上午11:47:57 <br>
 * E-mail:  <br> 
 */
public class TestProxy {

	public static void main(String[] args) {
		simpleProxy();
		dynamicProxy();
	}

	/**方法名称: SimpleProxy<br>
	 * 描述：
	 * 作者: ZRB
	 * 修改日期：2015年4月14日上午11:49:46
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
