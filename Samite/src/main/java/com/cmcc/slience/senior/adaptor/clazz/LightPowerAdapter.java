package com.cmcc.slience.senior.adaptor.clazz;


/**
 * ������IProcess process���󻯵�������ģʽ<br>
 * 		pc��+������+��Դ</br>
 * 		������������ģʽ</br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��9������2:27:24 <br>
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
