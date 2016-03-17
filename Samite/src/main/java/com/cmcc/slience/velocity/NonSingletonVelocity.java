package com.cmcc.slience.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.cmcc.slience.velocity.bean.ComputerBean;

public class NonSingletonVelocity {

	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		//���Բ�����init���������ǹٷ�ǿ����������ÿ��ܲ������ã�����һ��Ҫ����init��������������ʾʹ��Ĭ������
		ve.init();
		
		VelocityContext context = new VelocityContext();
		context.put("name", "lisi");
		
		ComputerBean bean = new ComputerBean();
		bean.setBrand("lenovo");
		bean.setSize(14.5f);
		bean.setPrice(4999.0);
		context.put("cpuBean", bean);
		
		List<String> strs = new ArrayList<String>();
		strs.add("1234");
		strs.add("2345");
		strs.add("3456");
		context.put("strs", strs);
		
		
		StringWriter writer = new StringWriter();
		
		Template template = ve.getTemplate("vm/HelloVelocity.vm","GBK");
		template.merge(context, writer);
		
		//���������������һ����Ч��
		//ve.mergeTemplate("vm/HelloVelocity.vm", "GBK", context, writer);
		System.out.println(writer.toString());
	}
}
