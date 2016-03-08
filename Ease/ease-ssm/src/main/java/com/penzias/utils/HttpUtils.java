package com.penzias.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.util.StringUtils;

public class HttpUtils {

	public static String requestAsHttpPOST(String urlvalue,Map<String, String> paras){

		HttpClient http = new HttpClient();
		HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(5000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);
		PostMethod method = new PostMethod(urlvalue);
		method.getParams().setHttpElementCharset("utf-8");
		method.getParams().setContentCharset("utf-8");
		method.getParams().setCredentialCharset("utf-8");
		for(String key:paras.keySet()){
			method.addParameter(key,paras.get(key));
		}
		String result = "";
		try{
			http.executeMethod(method);
			result = method.getResponseBodyAsString();
		} catch (IOException e){
			result = "";
			e.printStackTrace();
		}
		return result;
	}

	public static String requestAsHttpPOSTAsGBK(String urlvalue,Map<String, String> paras){

		HttpClient http = new HttpClient();
		PostMethod method = new PostMethod(urlvalue);
		method.getParams().setHttpElementCharset("gbk");
		method.getParams().setContentCharset("gbk");
		method.getParams().setCredentialCharset("gbk");
		for(String key:paras.keySet()){
			method.addParameter(key,paras.get(key));
		}
		String result = "";
		try{
			http.executeMethod(method);
			result = method.getResponseBodyAsString();
		} catch (IOException e){
			result = "";
			e.printStackTrace();
		}
		return result;
	}

	public static String requestAsHttpPOST(String urlvalue,Map<String, String> paras,String enc){

		HttpClient http = new HttpClient();
		PostMethod method = new PostMethod(urlvalue);
		method.getParams().setHttpElementCharset(enc);
		method.getParams().setContentCharset(enc);
		method.getParams().setCredentialCharset(enc);
		for(String key:paras.keySet()){
			method.addParameter(key,paras.get(key));
		}
		String result = "";
		try{
			http.executeMethod(method);
			result = method.getResponseBodyAsString();

		} catch (IOException e){
			result = "";
			e.printStackTrace();
		}
		return result;
	}

	public static String requestAsHttpGET(String urlvalue){

		String inputLine = "";
		try{
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			urlConnection.setReadTimeout(100000);
			urlConnection.setConnectTimeout(50000);
			String line = in.readLine();
			while (line != null){
				inputLine += line;
				line = in.readLine();
			}
		} catch (SSLHandshakeException d){
			requestAsHttpGET(urlvalue);
			d.printStackTrace();
		} catch (Exception e){

			e.printStackTrace();
		}
		return inputLine;
	}

	public static String requestAsHttpGETForException(String urlvalue) throws IOException{

		String inputLine = "";

		URL url;

		url = new URL(urlvalue);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream()));
		String line = in.readLine();
		while (line != null){
			inputLine += line;
			line = in.readLine();
		}

		return inputLine;
	}

	public static String getContent(Map<String, String> params,String privateKey,String charset){

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for(int i = 0;i < keys.size();i++){
			String key = (String) keys.get(i);
			String value = (String) params.get(key);
			if(!StringUtils.isEmpty(value)){
				if(i == keys.size() - 1){
					prestr = prestr + key + "=" + value;
				} else{
					prestr = prestr + key + "=" + value + "&";
				}
			}
		}
		return prestr + privateKey;
	}

	public static String getEncodingContent(Map<String, String> params,String charset){

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";

		for(int i = 0;i < keys.size();i++){
			String key = (String) keys.get(i);
			String value = (String) params.get(key);
			try{
				if(value != null && !"".equals(value)){
					value = URLEncoder.encode(value,charset);
				}

			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
			if(!StringUtils.isEmpty(value)){
				if(i == keys.size() - 1){
					prestr = prestr + key + "=" + value;
				} else{
					prestr = prestr + key + "=" + value + "&";
				}
			}
		}
		return prestr;
	}
}
