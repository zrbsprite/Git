package com.cmcc.slience.test.ftp;

import java.io.File;

import com.cmcc.slience.ftp.FtpUtil;

public class TestFtpUtil {

	public static void main(String[] args) {
		FtpUtil util  = FtpUtil.init();
		util.connect(FtpUtil.localMachine, FtpUtil.defaultPort, "zhangribo", "zhangribo");
		try {
			//上传开始
			util.useDir("/upload");
			File file = new File("E:\\logs\\king.log");
			util.upload(file);
			//下载开始
			util.download("/upload", "F:\\Ftp\\download", "king.log");
			//删除开始
			util.useDir("/upload");
			util.deleteFile("king.log");
			util.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
