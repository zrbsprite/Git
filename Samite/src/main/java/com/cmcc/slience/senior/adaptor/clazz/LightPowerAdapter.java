package com.cmcc.slience.senior.adaptor.clazz;


/**
 * 描述：IProcess process形象化的适配器模式<br>
 * 		pc机+适配器+电源</br>
 * 		采用类适配器模式</br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月9日下午2:27:24 <br>
 * E-mail: <br>
 *
 */
public class LightPowerAdapter extends LightPower implements IComputer{
	
	
	@Override
	public void consume() {
		super.supply();
	}
	
	public static void main(String[] args) {
		LightPower power = new LightPower();
		power.supply();
		
		IComputer cpu = new PersonComputer();
		cpu.consume();
		
		LightPowerAdapter adapter = new LightPowerAdapter();
		adapter.supply();
		adapter.consume();
	}
}
