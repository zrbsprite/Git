package com.jsprite.core.export;

import com.jsprite.core.utils.LogUtils;

public class PDFExportFactory implements IExportFactory {

	private String className = PDFExportFile.class.getName();
	
	public IExportFile create() {
		Object obj;
		try {
			obj = Class.forName(getClassName()).newInstance();
		} catch (Exception e) {
			LogUtils.error("工厂生产IExportFile失败："+e.getMessage());
			obj = null;
		}
		return (IExportFile)obj; 
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
