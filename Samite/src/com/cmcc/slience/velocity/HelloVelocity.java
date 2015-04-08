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
	 * Ĭ�϶�ȡģ���·������classpath�µ�ģ��
	 */
	private static void defaultTemplatePath() {
		// ��ʼ��������velocity
		//���Բ�����init���������ǹٷ�ǿ����������ÿ��ܲ������ã�����һ��Ҫ����init��������������ʾʹ��Ĭ������
		Velocity.init();
		// ����velocity����
		VelocityContext context = new VelocityContext();
		// ��������
		context.put("name", "zhangsan");

		Template template = null;
		try {
			// ��ȡģ��
			template = Velocity.getTemplate("vm/HelloVelocity.vm");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �����ַ�����
		StringWriter writer = new StringWriter();
		// �ϲ�ģ�������
		template.merge(context, writer);
		// ���������̨
		System.out.println(writer.toString());
	}

	/**
	 * ��ȡsrcĿ¼�µ�ģ��
	 */
	private static void srcTemplatePath(){
		//��ʼ������
        Properties properties=new Properties();
        //����velocity��Դ���ط�ʽΪclass
        properties.setProperty("resource.loader", "class");
        //����velocity��Դ���ط�ʽΪfileʱ�Ĵ�����
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //ʵ����һ��VelocityEngine����
        // ��ʼ��������velocity
 		Velocity.init(properties);
 		// ����velocity����
 		VelocityContext context = new VelocityContext();
 		// ��������
 		context.put("name", "zhangsan");

 		Template template = null;
 		try {
 			// ��ȡģ��
 			template = Velocity.getTemplate("com/cmcc/slience/velocity/template/HelloVelocity.vm");
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 		// �����ַ�����
 		StringWriter writer = new StringWriter();
 		// �ϲ�ģ�������
 		template.merge(context, writer);
 		// ���������̨
 		System.out.println(writer.toString());
	}
}
