package com.cmcc.slience.senior.strategy;

/**
 * ������ö�ٲ���<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��9������11:01:31 <br>
 * E-mail: <br>
 */
public enum CaculatorEnum {

	ADD("+"){
		public void execute(int x, int y){
			System.out.println(x + y);
		}
	},
	REDUCE("-"){
		public void execute(int x, int y){
			System.out.println(x - y);
		}
	};
	
	private String flag;
	
	private CaculatorEnum(String flag){
		this.flag = flag;
	}
	
	public String getFlag(){
		return this.flag;
	}
	
	public abstract void execute(int x, int y);
	
	public static void main(String[] args) {
		CaculatorEnum.ADD.execute(10, 11);
	}
}
