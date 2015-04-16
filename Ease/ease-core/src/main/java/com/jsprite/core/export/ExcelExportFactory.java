package com.jsprite.core.export;

import com.jsprite.core.utils.LogUtils;

public class ExcelExportFactory implements IExportFactory {

	//Class name
	private String className="";
	
	@SuppressWarnings("unchecked")
	public IExportFile create() {
		if("".equals(className)){
			return null;
		}
		try {
			Class<? extends IExportFile> clazz = (Class<? extends IExportFile>) Class.forName(className);
			return clazz.newInstance();
		} catch (ClassNotFoundException e) {
			LogUtils.error("创建IExportFile失败原因是：<"+e.getClass().getName()+">"+e.getMessage());
		} catch (InstantiationException e) {
			LogUtils.error("创建IExportFile失败原因是：<"+e.getClass().getName()+">"+e.getMessage());
		} catch (IllegalAccessException e) {
			LogUtils.error("创建IExportFile失败原因是：<"+e.getClass().getName()+">"+e.getMessage());
		}
		return null;
	}

	public String getExcelVersion() {
		return className;
	}

	public void setExcelVersion(String excelVersion) {
		this.className = excelVersion;
	}
}
