package com.penzias.main.mybatis;

import java.io.UnsupportedEncodingException;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 描述：自定义mybatis注释生成器<br/>
 * 作者：Bob <br/>
 * 修改日期：2015年12月16日 - 下午3:35:34<br/>
 * E-mail: sireezhang@163.com<br/>
 */
public class RobertoCommentGenerator extends DefaultCommentGenerator{

	@Override
	public void addFieldComment(Field field,IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn){
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			String marks = introspectedColumn.getRemarks();
			try{
				marks = new String(marks.getBytes("GBK"), "UTF-8");
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
	        field.addJavaDocLine("//" + marks);
	    }
	}
}
