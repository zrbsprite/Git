package com.cmcc.slience.senior.exception;

/**
 * ������Error������ָʾ�����Ӧ�ó���Ӧ����ͼ������������⡣
 * 	         ��������Ǻܴ�����⣬���㲻�ܴ����ˣ�������֮��֮�����ˣ��㲻�ù�����
 * 	         ����˵VirtualMachineError���� Java ������������þ��������������������Դʱ���׳��ô���
 * 	        �ðɣ���������쳣�Ĵ����ˣ���ôӦ�ú�ʱ����δ������أ�������JVM�ɣ�û�б�����רҵ���ˡ�<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��3��23������2:13:09 <br>
 */
@SuppressWarnings("serial")
public class SlienceError extends Error {
	
	public SlienceError(){
		super("ϵͳ��������");
	}
	
	public SlienceError(String error){
		super(error);
	}
}
