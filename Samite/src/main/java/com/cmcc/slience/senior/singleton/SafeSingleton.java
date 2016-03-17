package com.cmcc.slience.senior.singleton;

public class SafeSingleton {

	private SafeSingleton(){
		
	}
	
	private static SafeSingleton singleton = null;
	
	/**
	 * 方法名称: getInstance<br>
	 * 描述：这个是有线程安全问题的   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年4月8日上午10:45:09<br>
	 * @return<br>
	 */
	public static SafeSingleton getInstance(){
		if(null==singleton){
			singleton = new SafeSingleton();
		}
		return singleton;
	}
	
	/**
	 * 方法名称: getSafeInstance<br>
	 * 描述：这个解决了上面的安全问题   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年4月8日上午10:46:10<br>
	 * @return<br>
	 */
	public static synchronized SafeSingleton getSafeInstance(){
		if(null==singleton){
			singleton = new SafeSingleton();
		}
		return singleton;
	}
	
	/**
	 * 方法名称: getSafeInstance<br>
	 * 描述：这个解决了上面的安全问题   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年4月8日上午10:46:10<br>
	 * @return<br>
	 */
	public static SafeSingleton getSafeInstance2(){
		synchronized(singleton){
			if(null==singleton){
				singleton = new SafeSingleton();
			}
		}
		return singleton;
	}
}
