package com.jsprite.core.export;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author JSprite
 *	导出文件接口
 */
public interface IExportFile {

	public Workbook export();
	
}
