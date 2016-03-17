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
		//可以不调用init方法，但是官方强调如果不调用可能不起作用，所以一定要调用init方法不传参数表示使用默认配置
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
		
		//这个和上面两句是一样的效果
		//ve.mergeTemplate("vm/HelloVelocity.vm", "GBK", context, writer);
		System.out.println(writer.toString());
	}
}
