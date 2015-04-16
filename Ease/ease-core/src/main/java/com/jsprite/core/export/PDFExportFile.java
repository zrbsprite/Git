package com.jsprite.core.export;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

public class PDFExportFile implements IExportFile {

	PDFExportFile() {

	}

	private String filePath;
	
	public Object export() {

		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		PDPageContentStream content;
		try {
			content = new PDPageContentStream(doc, page);
			content.beginText();
			content.moveTextPositionByAmount(0, 0);
			content.drawString("bgsjhdjs--------------------==skksskAA");
			// 设置颜色
			content.setNonStrokingColor(Color.blue);
			// 起始x坐标，起始y坐标，方块宽度，高度
			content.fillRect(10, 200, 100, 300);
			// do something

			doc.addPage(page);
			content.endText();
			content.close();
			doc.save(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (COSVisitorException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
