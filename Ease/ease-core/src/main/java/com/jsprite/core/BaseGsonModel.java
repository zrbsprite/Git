package com.jsprite.core;

import com.google.gson.annotations.Expose;

public abstract class BaseGsonModel {

	@Expose
	private String className = getClass().getName();

	public String getClassName() {
		return className;
	}
}
