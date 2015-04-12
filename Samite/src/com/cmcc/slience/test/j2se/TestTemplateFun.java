package com.cmcc.slience.test.j2se;

import org.junit.Test;

import com.cmcc.slience.senior.templatefun.Animal;
import com.cmcc.slience.senior.templatefun.Biology;
import com.cmcc.slience.senior.templatefun.Human;

/**
 * 描述：测试模板方法设计模式<br>
 * 作者：ruibo <br>
 * 修改日期：2015年4月11日下午11:04:08 <br>
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
