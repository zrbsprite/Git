package com.cmcc.slience.apache;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

/**
 * ������commons-beanutils�Ķ�̬��ʹ��<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��2��26������3:58:44 <br> <br/>
 */
public class DynBean{

	public static void main(String[] args) {
		
		//��������
		DynaProperty name = new DynaProperty("name");
		DynaProperty sex = new DynaProperty("sex");
		DynaProperty age = new DynaProperty("age",java.lang.Integer.class);
		DynaProperty[] pros = {name, sex, age};
		
		//��̬class
		DynaClass personClass = new BasicDynaClass("Person", null, pros);
		
		//��̬bean
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
