package com.cmcc.slience.test.j2se;

import org.junit.Test;

import com.cmcc.slience.senior.templatefun.Animal;
import com.cmcc.slience.senior.templatefun.Biology;
import com.cmcc.slience.senior.templatefun.Human;

/**
 * ����������ģ�巽�����ģʽ<br>
 * ���ߣ�ruibo <br>
 * �޸����ڣ�2015��4��11������11:04:08 <br>
 * E-mail:  <br>
 */
public class TestTemplateFun {

	@Test
	public void test() {
		Biology human = new Human();
		human.life();
		
		Biology animal = new Animal();
		animal.life();
	}

}
