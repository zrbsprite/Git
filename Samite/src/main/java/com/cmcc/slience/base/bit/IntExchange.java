package com.cmcc.slience.base.bit;

import org.junit.Test;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年12月3日下午2:08:44 <br>
 * E-mail:  <br> 
 */
public class IntExchange {

	public void exchange(int a, int b){
		int temp = a;
		a = b;
		b = temp;
		System.out.println(a);
		System.out.println(b);
	}
	
	public void exchangeV1(int a, int b){
		b = a+b;
		a = b - a;
		b = b - a;
		System.out.println(a);
		System.out.println(b);
	}
	
	/**
	 * 方法名称: exchangeV2<br>
	 * 描述：	用异或实现交换，原理是两次异或同一个数，值不变<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年12月3日下午2:14:43<br/>
	 * @param a
	 * @param b
	 */
	public void exchangeV2(int a, int b){
		a = a^b;
		b = a^b;
		a = a^b;
		System.out.println(a);
		System.out.println(b);
	}
	
	@Test
	public void test(){
		IntExchange bean = new IntExchange();
		bean.exchange(10, 20);
		bean.exchangeV1(10, 20);
		bean.exchangeV2(10, 20);
	}
}
