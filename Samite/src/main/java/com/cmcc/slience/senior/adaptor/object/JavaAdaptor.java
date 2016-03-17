package com.cmcc.slience.senior.adaptor.object;

import com.cmcc.slience.senior.adaptor.object.target.ICode;
import com.cmcc.slience.senior.adaptor.object.target.IProcess;
import com.cmcc.slience.senior.adaptor.object.target.JavaCode;
import com.cmcc.slience.senior.adaptor.object.target.JavaProcess;

/**
 * ��������Ϊ����������ģʽ�� 
 * �ڲ��ı�ϵͳ�ȶ��Ե�ǰ���£�����Ҫʹ��Ŀ��ӿ��ṩ�����ݣ� 
 * ����Ŀ��ӿڲ�����ȫ������ϵͳ�Խӣ�����ʹ��������ģʽ<br>
 * ��һ�����ڶ�Ŀ��ӿڵ�ʱ����ö���ʽ������������һ�ӿ�Ҳ���Բ��á�
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��9������2:17:52 <br>
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
		//ԭ�������ݻ�ȡ��ʽ
		ProcessResult ps = new ProcessResult();
		ps.caculate();
		
		//������ģʽ��
		ICode code = new JavaCode();
		IProcess process = new JavaProcess();
		JavaAdaptor ad = new JavaAdaptor(code, process);
		ad.caculate();
	}
}
