package com.cmcc.slience.senior.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ReadFileLIneNoData {

	/**
	 * 方法名称: main<br>
	 * 描述：使用可以读取指定行数据的字符流
	 * 作者: ruibo
	 * 修改日期：2016年4月17日下午4:22:02
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String classPath = Thread.currentThread().getContextClassLoader().getClass().getResource("/").getPath();
			String fileName = classPath + "log4j.properties";
			FileReader fr = new FileReader(fileName);
			LineNumberReader reader = new LineNumberReader(fr);
			reader.setLineNumber(10);
			String line = reader.readLine();
			System.out.println(line);
			reader.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
