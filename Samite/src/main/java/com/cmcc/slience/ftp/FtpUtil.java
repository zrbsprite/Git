package com.cmcc.slience.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FtpUtil {

	private static Logger log = Logger.getLogger(FtpUtil.class);
	
	public static final String localMachine = "127.0.0.1";
	
	public static final int defaultPort = 21;
	
	private static FTPClient ftp;
	
	private FtpUtil(){
		setFtpClient();
	}
	
	private void setFtpClient(){
		ftp = new FTPClient();
	}
	
	public static FtpUtil init(){
		return new FtpUtil();
	}
	
	public  boolean connect(String host, int port,String username,String password){
		if(ftp==null){
			setFtpClient();
		}
		try {
			ftp.connect(host, port);
		} catch (SocketException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		int replyCode = 0;
		try {
			ftp.login(username, password);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		replyCode = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(replyCode)){
			try {
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		log.info("���ӳɹ���");
		return true;
	}
	
	public  boolean connect(String host, int port,String username,String password,String ftpDir){
		if(ftp==null){
			setFtpClient();
		}
		try {
			ftp.connect(host, port);
		} catch (SocketException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		int replyCode = 0;
		try {
			ftp.login(username, password);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		replyCode = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(replyCode)){
			try {
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		try {
			ftp.changeWorkingDirectory(ftpDir);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("δ�ҵ���Ӧ��Ŀ¼");
			try {
				ftp.disconnect();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return false;
		}
		log.info("���ӳɹ���");
		return true;
	}
	
	public  void useDir(String ftpDir) throws Exception{
		if(ftp==null){
			throw new NullPointerException("Ϊ����ftpClientʵ����");
		}
		
		if(!ftp.isConnected()){
			log.error("δ����ftp������");
			throw new IOException("δ����ftp��������");
		}
		
		int code = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(code)){
			log.error("δ��¼ftp������");
			throw new IOException("δ��¼ftp��������");
		}
		ftp.changeWorkingDirectory(ftpDir);
		log.info("�ı�ftp·���ɹ���"+ftpDir);
	}
	
	public  void upload(File file) throws Exception{
		if(!file.isDirectory()){
			InputStream is;
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.error("δ�ҵ��ļ���"+file.getName());
				throw e;
			}
			ftp.storeFile(file.getName(), is);
			log.info("upload file success:"+file.getName());
			is.close();
		}else{
			//�ϴ����ļ���
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			File[] files = file.listFiles();
			for(File f : files){
				upload(f);
				if(f.isDirectory()){
					ftp.changeToParentDirectory();
				}
			}
		}
	}

	/**
	 * ftp�����ļ�
	 * @param remotePath
	 * @param localPath
	 * @param fileName
	 */
	public  void download(String remotePath,String localPath,String fileName){
		try {
			ftp.changeWorkingDirectory(remotePath);
			FTPFile[] ftpFiles = ftp.listFiles();
			for(FTPFile file : ftpFiles){
				if (file.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + file.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(file.getName(), is);
					is.close();
					log.info("�ɹ������ļ���·���ǣ�"+localPath+file.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("�ļ�·������");
			return;
		}
	}
	
	public  void deleteFile(String filename) throws Exception{
		ftp.deleteFile(filename);
		log.info("ɾ��ftp�ļ��ɹ���"+getWorkDictionary()+File.separator+filename);
	}
	
	public  boolean removeDir(String dirPath){
		boolean flag = false;
		try {
			flag = ftp.removeDirectory(dirPath);
			log.info("ɾ��ftp�ļ��ܳɹ���"+dirPath);
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public  boolean removeFiles(String filepath){
		boolean flag = false;
		try {
			FTPFile[] ftpFiles = ftp.listFiles(filepath);
			if(ftpFiles==null){
				ftp.deleteFile(filepath);
				log.info("ɾ��ftp�ļ��ɹ���"+getWorkDictionary()+File.separator+filepath);
			}else{
				int size = ftpFiles.length;
				if(size==0){
					flag = ftp.removeDirectory(filepath);
					log.info("ɾ��ftp�ļ��ܳɹ���"+filepath);
				}else{
					for(FTPFile f : ftpFiles){
						if(f.isFile()){
							ftp.deleteFile(filepath+File.separator+f.getName());
						}else{
							removeFiles(filepath+File.separator+f.getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public String getWorkDictionary(){
		String workDir;
		try {
			workDir = ftp.printWorkingDirectory();
		} catch (IOException e) {
			e.printStackTrace();
			workDir = null;
		}
		return workDir;
	}
	
	public void dispose(){
		try {
			ftp.logout();
			if(ftp.isConnected()){
				ftp.disconnect();
			}
		} catch (Exception e) {
			log.error("�ͷ�ftp��Դ����"+e.getMessage());
		}
		log.info("ftp�ͷ����ӳɹ���");
	}
	
}