package com.cmcc.slience.senior.templatefun;

/**
 * ������ģ�巽�����ģʽ<br>
 * ���ߣ�ruibo <br>
 * �޸����ڣ�2015��4��11������11:04:52 <br>
 * E-mail:  <br>
 */
public abstract class Biology {

	protected abstract void birth();
	
	protected abstract void grow();
	
	protected abstract void older();
	
	protected abstract void die();
	
	public final void life(){
		birth();
		grow();
		older();
		die();
	}
}
