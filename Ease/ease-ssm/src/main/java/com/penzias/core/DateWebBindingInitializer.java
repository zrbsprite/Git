package com.penzias.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 描述：全局属性编辑器，可以把形如：yyyy-MM-dd的字符串转换成日期类型；需要配置在AnnotationMethodHandlerAdapter<br/>
 * 作者：Bob <br/>
 * 修改日期：2015年11月25日 - 下午5:37:15<br/>
 */
public class DateWebBindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder,WebRequest request){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

}
