package com.jsprite.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

public class HttpUtils {
	
	private static final int GET_METHOD = 1;
	
	private static final int POST_METHOD = 0;
	
	/**
	 * 针对表单方式的请求
	 * */
	public static String getRequest(String uri, Map<String, String> paramMap){
		HttpClient client = new HttpClient();
		String queryString = makeQueryString(paramMap);
		if(!StringUtils.isEmpty(queryString)){
			if(uri.indexOf("?")==-1){
				uri += "?";
				uri += queryString;
			}else{
				uri += "&";
				uri += queryString;
			}
		}
		GetMethod get = (GetMethod) getMethod(uri, GET_METHOD);
		HttpMethodParams httpParams = new HttpMethodParams();
		httpParams.setContentCharset("UTF-8");
		get.setParams(httpParams);
		HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
		params.setConnectionTimeout(3000);
		//设置读取超时时间
		params.setSoTimeout(5000);
		String responseString = null;
		try {
			int code = client.executeMethod(get);
			if(code == 200){
				responseString = get.getResponseBodyAsString();
			}else{
				LogUtils.info("向地址<"+uri+">发送请求不成功，返回响应码是：" + code);
			}
		} catch (HttpException e) {
			LogUtils.warn("连接超时");
			responseString = null;
		} catch (IOException e) {
			LogUtils.warn("IO异常");
			responseString = null;
		}
		get.releaseConnection();
		return responseString;
	}
	
	public static String postRequest(String uri, Map<String, String> paramMap){
		HttpClient client = new HttpClient();
		PostMethod post = (PostMethod) getMethod(uri, POST_METHOD);
		HttpMethodParams httpParams = new HttpMethodParams();
		httpParams.setContentCharset("UTF-8");
		post.setParams(httpParams);
		NameValuePair[] parametersBody = makesNameValuePairArray(post, paramMap);
		if(parametersBody.length>0){
			post.setRequestBody(parametersBody);
		}
		HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
		params.setConnectionTimeout(3000);
		//设置读取超时时间
		params.setSoTimeout(5000);
		String responseString = null;
		try {
			int code = client.executeMethod(post);
			if(code == 200){
				responseString = post.getResponseBodyAsString();
			}else{
				LogUtils.info("向地址<"+uri+">发送请求不成功，返回响应码是：" + code);
			}
		} catch (HttpException e) {
			LogUtils.warn("连接超时");
			responseString = null;
		} catch (IOException e) {
			LogUtils.warn("IO异常");
			responseString = null;
		}
		post.releaseConnection();
		return responseString;
	}
	
	public static String postRequestUpper(String uri, Map<String, String> paramMap){
		HttpClient client = new HttpClient();
		PostMethod post = (PostMethod) getMethod(uri, POST_METHOD);
		HttpMethodParams httpParams = new HttpMethodParams();
		httpParams.setContentCharset("UTF-8");
		post.setParams(httpParams);
		MultipartRequestEntity requestEntity = makesMultipartRequestEntity(post, paramMap);
		if(null!=requestEntity){
			long len = paramMap.size();
			if(len>0){
				post.setRequestEntity(requestEntity);
			}
		}
		HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
		params.setConnectionTimeout(3000);
		//设置读取超时时间
		params.setSoTimeout(5000);
		String responseString = null;
		try {
			int code = client.executeMethod(post);
			if(code == 200){
				responseString = post.getResponseBodyAsString();
			}else{
				LogUtils.info("向地址<"+uri+">发送请求不成功，返回响应码是：" + code);
			}
		} catch (HttpException e) {
			LogUtils.warn("连接超时");
			responseString = null;
		} catch (IOException e) {
			LogUtils.warn("IO异常");
			responseString = null;
		}
		post.releaseConnection();
		return responseString;
	}
	
	private static HttpMethod getMethod(String uri, int methodType){
		HttpMethod method = null;
		if(methodType == POST_METHOD){
			method = new PostMethod(uri);
		}else if(methodType == GET_METHOD){
			method = new GetMethod(uri);
		}else{
			return null;
		}
		method.setRequestHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.setRequestHeader("connection", "Keep-Alive");
		method.setRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0");
		method.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
        //postMethod.setRequestHeader("Content-Type", "text/html;charset=utf-8");
		return method;
	}
	
	private static String makeQueryString(Map<String, String> paramMap){
		if(null==paramMap){
			return null;
		}
		StringBuffer queryString = new StringBuffer(); 
		Set<Entry<String, String>> set = paramMap.entrySet();
		for(Entry<String, String> entry : set){
			queryString.append(entry.getKey());
			queryString.append("=");
			queryString.append(entry.getValue());
			queryString.append("&");
		}
		queryString.append(System.currentTimeMillis()+"");
		return queryString.toString();
	}
	
	private static MultipartRequestEntity makesMultipartRequestEntity(PostMethod post, Map<String, String> paramMap){
		if(null==paramMap){
			return null;
		}
		Set<Entry<String, String>> set = paramMap.entrySet();
		Part[] parts = null;
		List<Part> listPart = new ArrayList<Part>();
		for(Entry<String, String> entry : set){
			listPart.add(new StringPart(entry.getKey(), entry.getValue()));
		}
		parts = listPart.toArray(new Part[0]);
		MultipartRequestEntity requestEntity = new MultipartRequestEntity(parts, post.getParams());
		return requestEntity;
	}
	
	private static NameValuePair[] makesNameValuePairArray(HttpMethod method, Map<String, String> paramMap){
		if(null==paramMap){
			return new NameValuePair[0];
		}
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> set = paramMap.entrySet();
		for(Entry<String, String> entry : set){
			NameValuePair pair = new NameValuePair(entry.getKey(), entry.getValue());
			list.add(pair);
		}
		NameValuePair[] parametersBody = list.toArray(new NameValuePair[0]);
		return parametersBody;
	}
}