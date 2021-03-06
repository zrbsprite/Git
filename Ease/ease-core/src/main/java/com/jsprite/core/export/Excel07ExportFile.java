package com.jsprite.core.export;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jsprite.core.IBaseExcel;
import com.jsprite.core.annotation.Cell;
import com.jsprite.core.annotation.Excel;
import com.jsprite.core.annotation.Format;
import com.jsprite.core.utils.DateFormatUtils;
import com.jsprite.core.utils.LogUtils;
import com.jsprite.core.utils.NumberUtils;

public class Excel07ExportFile implements IExportFile {

	private List<IBaseExcel> listModel;
	
	Excel07ExportFile(){
		
	}
	
	Excel07ExportFile(List<IBaseExcel> listModel){
		this.listModel = listModel;
	}
	
	@SuppressWarnings("unchecked")
	public Workbook export() {
		if(null==listModel){
			return null;
		}
		
		IBaseExcel template = listModel.get(0);
		Class<IBaseExcel> clazz = (Class<IBaseExcel>) template.getClass();
		
		int index = 0;
		String sheetName = "New Sheet";
		int dataRowIndex = 2;
		String excelTitle = "";
		int titleRowIndex = 0;
		int headRowIndex = 1;
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		if(clazz.isAnnotationPresent(Excel.class)){
			Excel excel = clazz.getAnnotation(Excel.class);
			index = excel.index();
			sheetName = excel.sheetName();
			dataRowIndex = excel.start();
			excelTitle = excel.title();
			titleRowIndex = excel.titleIndex();
			headRowIndex = dataRowIndex-1;
		}
		
		XSSFSheet sheet = workbook.createSheet(sheetName);
		workbook.setSheetOrder(sheetName, index);

		int columnSize = 0;
		
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(Cell.class)){
				columnSize ++;
				Cell cell = field.getAnnotation(Cell.class);
				int columIndex = cell.index();
				String title = cell.title();
				XSSFRow headRow = sheet.createRow(headRowIndex);
				XSSFCell headCell = headRow.createCell(columIndex);
				headCell.setCellValue(title);
			}
		}
		
		for(IBaseExcel model : listModel){
			for(Field field : fields){
				if(field.isAnnotationPresent(Cell.class)){
					Cell cell = field.getAnnotation(Cell.class);
					int columIndex = cell.index();
					XSSFRow dataRow = sheet.createRow(dataRowIndex);
					XSSFCell dataCell = dataRow.createCell(columIndex);
					
					field.setAccessible(true);
					Object value = null;
					try {
						value = field.get(model);
					} catch (Exception e) {
						value = "";
						LogUtils.warn("获取对象属性<"+field.getName()+">失败,已忽略!", getClass());
					}
					
					Type type = field.getGenericType();
					if(type.toString().equals("class java.lang.String")){
						dataCell.setCellValue((String)value);
					}else if(type==Integer.TYPE){
						if(field.isAnnotationPresent(Format.class)){
							Format format = field.getAnnotation(Format.class);
							String pattern = format.pattern();
							BigDecimal decimal = new BigDecimal((Integer)value);
							String fString = NumberUtils.customNumberFormat(decimal, pattern);
							dataCell.setCellValue(fString);
						}else{
							dataCell.setCellValue((Integer)value);
						}
					}else if(type == Float.TYPE){
						if(field.isAnnotationPresent(Format.class)){
							Format format = field.getAnnotation(Format.class);
							String pattern = format.pattern();
							BigDecimal decimal = new BigDecimal((Float)value);
							String fString = NumberUtils.customNumberFormat(decimal, pattern);
							dataCell.setCellValue(fString);
						}else{
							dataCell.setCellValue((Float)value);
						}
					}else if(type==Double.TYPE){
						if(field.isAnnotationPresent(Format.class)){
							Format format = field.getAnnotation(Format.class);
							String pattern = format.pattern();
							BigDecimal decimal = new BigDecimal((Double)value);
							String fString = NumberUtils.customNumberFormat(decimal, pattern);
							dataCell.setCellValue(fString);
						}else{
							dataCell.setCellValue((Double)value);
						}
					}else if(type == Long.TYPE){
						if(field.isAnnotationPresent(Format.class)){
							Format format = field.getAnnotation(Format.class);
							String pattern = format.pattern();
							BigDecimal decimal = new BigDecimal((Long)value);
							String fString = NumberUtils.customNumberFormat(decimal, pattern);
							dataCell.setCellValue(fString);
						}else{
							dataCell.setCellValue((Long)value);
						}
					}else if(type == Character.TYPE){
						dataCell.setCellValue((Character)value);
					}else if(type.toString().equals("class java.util.Date")){
						if(field.isAnnotationPresent(Format.class)){
							Format format = field.getAnnotation(Format.class);
							String pattern = format.pattern();
							String fString = DateFormatUtils.getFormatedString((Date)value, pattern);
							dataCell.setCellValue(fString);
						}else{
							dataCell.setCellValue((Date)value);
						}
					}else{
						dataCell.setCellValue(value.toString());
					}
				}
			}
		}
		
		/*生成excel标题*/
		XSSFRow titleRow = sheet.createRow(titleRowIndex);
		CellRangeAddress range = new CellRangeAddress(titleRowIndex, titleRowIndex, 0, columnSize);
		sheet.addMergedRegion(range);
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(excelTitle);
		
		return workbook;
	}

	public List<IBaseExcel> getListModel() {
		return listModel;
	}

	public void setListModel(List<IBaseExcel> listModel) {
		this.listModel = listModel;
	}
}
