package com.cmcc.slience.velocity;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class HelloVelocity {

	public static void main(String[] args) {
//		defaultTemplatePath();
		srcTemplatePath();
	}

	/**
	 * 默认读取模板的路径，即classpath下的模板
	 */
	private static void defaultTemplatePath() {
		// 初始化单例的velocity
		//可以不调用init方法，但是官方强调如果不调用可能不起作用，所以一定要调用init方法不传参数表示使用默认配置
		Velocity.init();
		// 创建velocity环境
		VelocityContext context = new VelocityContext();
		// 放入数据
		context.put("name", "zhangsan");

		Template template = null;
		try {
			// 读取模板
			template = Velocity.getTemplate("vm/HelloVelocity.vm");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建字符串流
		StringWriter writer = new StringWriter();
		// 合并模板和数据
		template.merge(context, writer);
		// 输出到控制台
		System.out.println(writer.toString());
	}

	/**
	 * 读取src目录下的模板
	 */
	private static void srcTemplatePath(){
		//初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        // 初始化单例的velocity
 		Velocity.init(properties);
 		// 创建velocity环境
 		VelocityContext context = new VelocityContext();
 		// 放入数据
 		context.put("name", "zhangsan");

 		Template template = null;
 		try {
 			// 读取模板
 			template = Velocity.getTemplate("com/cmcc/slience/velocity/template/HelloVelocity.vm");
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 		// 创建字符串流
 		StringWriter writer = new StringWriter();
 		// 合并模板和数据
 		template.merge(context, writer);
 		// 输出到控制台
 		System.out.println(writer.toString());
	}
}
