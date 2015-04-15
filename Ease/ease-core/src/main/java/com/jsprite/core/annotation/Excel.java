package com.jsprite.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Excel {

	/**
	 * 描述：excel文件名 修改日期：2015年4月15日下午2:03:51
	 */
	String fileName();

	/**
	 * 方法名称: index<br>
	 * 描述：写入的sheet索引 <br>
	 * 作者: ZRB <br>
	 * 修改日期：2015年4月15日下午2:21:30
	 */
	int index() default 0;

	/**
	 * 方法名称: sheetName<br>
	 * 描述：sheet名称 <br>
	 * 作者: ZRB <br>
	 * 修改日期：2015年4月15日下午2:21:58
	 */
	String sheetName() default "sheet";

	/**
	 * 方法名称: sheetName<br>
	 * 描述：数据列开始行数 ,默认从索引2即第三行开始 <br>
	 * 作者: ZRB <br>
	 * 修改日期：2015年4月15日下午2:21:58
	 */
	int start() default 3;

	/**
	 * 方法名称: titleIndex<br>
	 * 描述：excel表格的大标题所在行数<br>
	 * 作者: ZRB 修改日期：2015年4月15日下午4:38:47<br>
	 */
	int titleIndex() default 0;

	/**
	 * 方法名称: title<br>
	 * 描述：excel表格大标题<br>
	 * 作者: ZRB 修改日期：2015年4月15日下午4:39:26
	 */
	String title() default "";
}
