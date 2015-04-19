package com.jsprite.core.export;


public class PDFExportFile implements IExportFile {

	PDFExportFile() {

	}

	private String filePath;
	
	public Object export() {
		return null;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
