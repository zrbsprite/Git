package com.cmcc.slience.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpDown {

	/**
	 * Description: ��FTP�����������ļ�
	 * 
	 * @Version1.0 
	 * @param url
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param remotePath
	 *            FTP�������ϵ����·��
	 * @param fileName
	 *            Ҫ���ص��ļ���
	 * @param localPath
	 *            ���غ󱣴浽���ص�·��
	 * @return
	 */
	public static boolean downloadFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;

			// ����FTP������
			if (port > -1) {
				ftp.connect(url, port);
			} else {
				ftp.connect(url);
			}

			ftp.login(username, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// ת�Ƶ�FTP������Ŀ¼
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
}
