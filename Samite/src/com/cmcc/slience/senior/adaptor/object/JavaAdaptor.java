package com.cmcc.slience.senior.adaptor.object;

import com.cmcc.slience.senior.adaptor.object.target.ICode;
import com.cmcc.slience.senior.adaptor.object.target.IProcess;
import com.cmcc.slience.senior.adaptor.object.target.JavaCode;
import com.cmcc.slience.senior.adaptor.object.target.JavaProcess;

/**
 * 描述：此为对象适配器模式。 
 * 在不改变系统稳定性的前提下，现在要使用目标接口提供的数据， 
 * 但是目标接口不能完全和现有系统对接，考虑使用适配器模式<br>
 * 不一定是在多目标接口的时候才用对象式的适配器，单一接口也可以采用。
 * 作者：ZRB <br>
 * 修改日期：2016年3月9日下午2:17:52 <br>
 * E-mail: <br>
 *
 */
public class JavaAdaptor implements IResult {

	private ICode code;

	private IProcess process;

	public JavaAdaptor(ICode code, IProcess process) {
		this.code = code;
		this.process = process;
	}

	@Override
	public void caculate() {
		code.compile();
		process.execute();
	}
	
	public static void main(String[] args) {
		//原来的数据获取方式
		ProcessResult ps = new ProcessResult();
		ps.caculate();
		
		//适配器模式下
		ICode code = new JavaCode();
		IProcess process = new JavaProcess();
		JavaAdaptor ad = new JavaAdaptor(code, process);
		ad.caculate();
	}
}
