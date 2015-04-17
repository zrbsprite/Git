package com.jsprite.core.export;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFExportFile implements IExportFile {

	PDFExportFile() {

	}

	private String filePath;
	
	public Object export() {

		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		PDPageContentStream content;
		PDFont font = PDType1Font.HELVETICA;
		try {
			doc.addPage(page);
			content = new PDPageContentStream(doc, page);
			content.beginText();
			content.setFont(font, 14);
			content.moveTextPositionByAmount(100, 700);
			content.drawString("bgsjhdjs--------------------==skksskAA中文");
			// 设置颜色
			content.setNonStrokingColor(Color.blue);
			// 起始x坐标，起始y坐标，方块宽度，高度
//			content.fillRect(10, 200, 100, 300);
			// do something

			content.endText();
			content.close();
			doc.save(filePath);
			doc.close();
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
