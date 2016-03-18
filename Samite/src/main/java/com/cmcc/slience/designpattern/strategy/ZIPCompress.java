/**
 * File Name:ZIPCompress.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日上午10:37:26
 */
package com.cmcc.slience.designpattern.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日上午10:37:26 <br>
 * E-mail:  <br> 
 */
public class ZIPCompress implements ICompress {

	/**方法名称：doCompress <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年3月18日上午10:48:52 
	 * @see com.cmcc.slience.designpattern.strategy.ICompress#doCompress(java.io.File, java.io.File) 
	 * @param file
	 * @param target
	 */
	@Override
	public void doCompress(File file, File target) {
		try {
			
			FileOutputStream fos = new FileOutputStream(target);
			ZipOutputStream zos = new ZipOutputStream(fos);
			FileInputStream fis = new FileInputStream(file);
			byte[] byt = IOUtils.toByteArray(fis);
			ZipEntry entry = new ZipEntry(file.getName());
			zos.putNextEntry(entry);
			zos.write(byt);
			zos.close();
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**方法名称：doUnCompress <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年3月18日上午10:48:52 
	 * @see com.cmcc.slience.designpattern.strategy.ICompress#doUnCompress(java.io.File, java.io.File) 
	 * @param zip
	 * @param target
	 */
	@Override
	public void doUnCompress(File zip, File target) {
		
		try {
			
			FileInputStream fis = new FileInputStream(zip);
			ZipInputStream zis = new ZipInputStream(fis);
			
			ZipEntry entry;
			while(null!=(entry = zis.getNextEntry())){
				String fileName = entry.getName();
				File out = new File(target, fileName);
				if(entry.isDirectory()){
					out.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(out);
				byte[] byt = IOUtils.toByteArray(zis);
				fos.write(byt);
				fos.close();
			}
			
			zis.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
