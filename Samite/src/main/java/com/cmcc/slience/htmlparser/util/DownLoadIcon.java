package com.cmcc.slience.htmlparser.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.CharEncoding;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class DownLoadIcon {
	
	public static String systemCharSet = System.getProperty("file.encoding");

	private static String basePath = "E:\\资料\\图片\\图标\\下载";
	
	private static String baseDomain = "http://www.easyicon.net";
	
	/**
	 * 测试通过hp拿到scrpt的内容
	 * @return
	 */
	public static String getDicResult(HttpEntity entity){
		Parser parser = new Parser();
		try {
			parser.setEncoding(CharEncoding.UTF_8);
		} catch (ParserException e) {
			//使用默认的编码
		}
		
		String inputHTML = "";
		try {
			inputHTML = EntityUtils.toString(entity);
		} catch (Exception e1) {
			return null;
		}
		
		try {
			parser.setInputHTML(inputHTML);
		} catch (ParserException e) {
			return null;
		}
		
		//寻找返回的结果
		NodeFilter filter = new  TagNameFilter("script");
		try {
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if(nodes!= null){
				for(int i=0;i<nodes.size();i++){
					Node node = nodes.elementAt(i);
					ScriptTag tag = (ScriptTag)node;
					if(tag.isEmptyXmlTag()){
						continue;
					}
					System.out.println(tag.getScriptCode());
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static HttpEntity getEntity(String key){
		CloseableHttpClient  httpClient = HttpClients.createDefault();
		URI uri=null;
		try {
			uri = new URI("http://www.easyicon.net/iconsearch/"+key+"/");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");
        CloseableHttpResponse response = null;
        try {
        	response = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			System.out.println("不支持此协议！");
			return null;
		} catch (IOException e) {
			System.out.println("通信异常！");
			return null;
		}
        return response.getEntity();
	}
	
	/**
	 * download icon
	 * @param entity
	 */
	public static void getPicFromWebsite(HttpEntity entity){
		Parser parser = new Parser();
		try {
			parser.setEncoding(CharEncoding.UTF_8);
		} catch (ParserException e) {
			//使用默认的编码
		}
		
		String inputHTML = "";
		try {
			inputHTML = EntityUtils.toString(entity);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		try {
			parser.setInputHTML(inputHTML);
		} catch (ParserException e) {
			e.printStackTrace();
			return;
		}
		
		//寻找返回的结果
		NodeFilter filter = new  TagNameFilter("div");
		HasAttributeFilter haf = new HasAttributeFilter("class", "icon_img");
		AndFilter af = new AndFilter(filter, haf);
		try {
			NodeList nodes = parser.extractAllNodesThatMatch(af);
			if(nodes!= null){
				for(int i=0;i<nodes.size();i++){
					Node node = nodes.elementAt(i);
					LinkTag linkTag = (LinkTag)node.getFirstChild();
					String linkTagUrl = linkTag.getLink();
					Parser linkTagParser = new Parser(baseDomain+linkTagUrl);
					linkTagParser.setEncoding(CharEncoding.UTF_8);
					NodeFilter linkFilter = new  TagNameFilter("td");
					HasAttributeFilter linkFaf = new HasAttributeFilter("valign", "bottom");
					AndFilter linkAf = new AndFilter(linkFilter, linkFaf);
					HasParentFilter hpf = new HasParentFilter(linkAf);
					NodeList linkTagNodeList = linkTagParser.extractAllNodesThatMatch(hpf);
					if(linkTagNodeList!=null){
						for(int j=0;j<linkTagNodeList.size();j++){
							Node linkNode = linkTagNodeList.elementAt(j);
							if(!(linkNode instanceof LinkTag)){
								continue;
							}
							LinkTag ltTar = (LinkTag)linkNode;
							String ltTarUrl = ltTar.getLink();
							//抓图
							try {
								getHttpFile(ltTarUrl);
							} catch (Exception e) {
								e.printStackTrace();
								continue;
							}
						}
					}
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	public static void getHttpFile(String url) throws Exception{
		CloseableHttpClient  httpClient = HttpClients.createDefault();
		URI uri=null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");
        CloseableHttpResponse response = null;
        InputStream is=null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        FileImageOutputStream fios = null;
        try {
        	response = httpClient.execute(httpget);
        	HttpEntity entity = response.getEntity();
			is = entity.getContent();
        	Header[] headers = response.getHeaders("Content-Type");
        	String imageType = headers[0].getValue();
        	headers = response.getHeaders("Content-Disposition");
        	String imageName = headers[0].getValue();
        	imageName = imageName.substring(imageName.indexOf("=")+1);
        	imageName = imageName.replace("\"", "");
        	if(imageType!=null){
        		if(imageType.equals("application/image/png")){
        			//图片
        			BufferedImage bi = ImageIO.read(is);
					fios = new FileImageOutputStream(new File(basePath+"\\"+imageName));
					ImageIO.write(bi, "png", fios);
        		}else{
        			//icon
        			//二进制文件
        			bis = new BufferedInputStream(is);
        			fos = new FileOutputStream(basePath+"\\"+imageName);
        			BufferedOutputStream bos = new BufferedOutputStream(fos);
        			byte[] b = new byte[1024];
        			int readed = 0;
        			while((readed = bis.read(b))!=-1){
        				bos.write(b,0,readed);
        			}
        			bos.flush();
        			bos.close();
        		}
        	}
		} catch (Exception e) {
			throw e;
		}finally{
			try {
				if(fos!=null)
					fos.close();
				if(bis!=null)
					bis.close();
				if(fios!=null)
					fios.close();
				response.close();
				httpClient.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
