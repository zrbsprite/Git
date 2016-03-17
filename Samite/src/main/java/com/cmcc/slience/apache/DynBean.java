package com.cmcc.slience.apache;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

/**
 * 描述：commons-beanutils的动态类使用<br>
 * 作者：ZRB <br>
 * 修改日期：2015年2月26日下午3:58:44 <br> <br/>
 */
public class DynBean{

	public static void main(String[] args) {
		
		//设置属性
		DynaProperty name = new DynaProperty("name");
		DynaProperty sex = new DynaProperty("sex");
		DynaProperty age = new DynaProperty("age",java.lang.Integer.class);
		DynaProperty[] pros = {name, sex, age};
		
		//动态class
		DynaClass personClass = new BasicDynaClass("Person", null, pros);
		
		//动态bean
		try {
			DynaBean person = personClass.newInstance();
			person.set("name", "zhangsan");
			person.set("sex", "male");
			person.set("age", 26);
			
			System.out.println(person.get("name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
