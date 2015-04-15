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
public @interface Format {

	public static int TYPE_NUMBER = 1;
	
	public static int TYPE_DATE = 0;
	
	String pattern() default "";
	
	int type() default 0;
}
