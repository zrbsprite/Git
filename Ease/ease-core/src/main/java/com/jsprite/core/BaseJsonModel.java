package com.jsprite.core;

/**
 * @author JSprite
 * 可以使用 @JSONField 注解
 */
public abstract class BaseJsonModel {

	private transient String className = getClass().getName();

	public String getClassName() {
		return className;
	}
}
