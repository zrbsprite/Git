package com.cmcc.slience.senior.templatefun;

/**
 * 描述：模板方法设计模式<br>
 * 作者：ruibo <br>
 * 修改日期：2015年4月11日下午11:04:52 <br>
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
