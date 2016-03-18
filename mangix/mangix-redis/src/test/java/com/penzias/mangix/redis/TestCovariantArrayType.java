/**
 * File Name:CovariantArray.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日下午4:35:37
 */
package com.penzias.mangix.redis;

import org.junit.Test;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日下午4:35:37 <br>
 * E-mail:  <br> 
 */
public class TestCovariantArrayType {

	@Test
	public void isCan() throws Exception {
		Person[] persons = new EnglishPerson[5];
		persons[0] = new EnglishPerson();
		//下面的写法是错误的
		//persons[0] = new EnglishPerson();
	}
	
	class Person{
		
	}
	
	class EnglishPerson extends Person{
		
	}
	
	class AmaricanPerson extends Person{
		
	}
}
