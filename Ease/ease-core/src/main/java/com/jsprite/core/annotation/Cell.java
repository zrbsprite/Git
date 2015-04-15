package com.jsprite.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Cell {

   /**
	* 方法名称: index<br>
	* 描述：列数索引值<br/>
	* 作者: ZRB<br/>
	* 修改日期：2015年4月15日下午2:14:57<br/>
	* @return
	*/
	int index() default 0;
	
	/**
	 *  方法名称: title<br>
	 *  描述：对应的中文名称<br>
	 *  作者: ZRB <br>
	 *  修改日期：2015年4月15日下午2:18:16<br>
	 */
	String title() default "";
	
	/**
	 * 方法名称: colum<br>
	 * 描述：对应字段名称 <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年4月15日下午2:19:12<br>
	 */
	String column() default "";
	
}