package com.cmcc.slience.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

/**
 * 初步学习httpclient
 * @author Administrator
 *
 */
public class SimpleClient {
	
	public static void main(String[] args) {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.baidu.com");
		
		try {
			//hello httpClient
//			HttpResponse response = httpClient.execute(get);
//			HttpEntity entity = response.getEntity();
//			System.out.println(entity.getContentLength());
			//输出返回的相应内容
//			System.out.println(EntityUtils.toString(entity));
			
//			iteratHeaderElementByName(response, "set-cookie");
			
			//使用URIBuilder去构建uri
			/*URIBuilder builder = new URIBuilder();
			builder.setScheme("http");
			builder.setHost("www.baidu.com");
			builder.setPath("/search");
			builder.setParameter("q", "httpclient");
		    builder.setParameter("btnG", "Google Search");
		    builder.setParameter("aq", "f");
		    builder.setParameter("oq", "");
		    URI uri = builder.build();
		    HttpGet httpGet = new HttpGet(uri);
		    System.out.println(httpGet.getURI());*/
		    
		    //HTTP Response
			//模拟成功的相应信息
		    HttpResponse httpRes = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
    		httpRes.addHeader("Content-Disposition", "attachment; filename=20140404101107188_easyicon_net_96.png");
		    httpRes.addHeader("Content-Type", "application/image/png");
//		    System.out.println(httpRes.getHeaders("Content-Disposition")[0].getValue());
		    Header[] headers = httpRes.getHeaders("Content-Type");
        	String iamgeType = headers[0].getValue();
        	headers = httpRes.getHeaders("Content-Disposition");
        	String imageName = headers[0].getValue();
        	System.out.println(imageName);
        	System.out.println(iamgeType);
//		    iteratHeaderElementByName(httpRes, null);
		    
//		    getHttpInfo(httpRes);
    		
		    //下面是为请求加上一些header信息，来伪装浏览器
//	        httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
//	        httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
//	        httpget.addHeader("DNT","1");
//	        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
//	        httpget.addHeader("Accept-Encoding","gzip, deflate");
//    		
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			get.releaseConnection();
		}
	}
	
	/**
	 * 获取相应消息头</br>
	 * 头信息可以自定义添加，并根据规则获取
	 * @param response
	 */
	public static void getHttpHeader(HttpResponse response){
		response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
		response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
		Header h1 = response.getFirstHeader("Set-Cookie");
		System.out.println(h1);
		Header h2 = response.getLastHeader("Set-Cookie");
		System.out.println(h2);
		Header[] hs = response.getHeaders("Set-Cookie");
		System.out.println(hs.length);
	}
	
	/**
	 * HTTP响应
	 * HTTP响应第一行是HTTP版本号，然后是响应状态码和响应内容
	 */
	public static void getHttpInfo(HttpResponse response){
		 //获取协议版本
		System.out.println(response.getProtocolVersion());
		//获取状态
		System.out.println(response.getStatusLine().getStatusCode());
		//获取相应的前缀短语
		System.out.println(response.getStatusLine().getReasonPhrase());
		//获取返回的状态信息
		System.out.println(response.getStatusLine().toString());
	}
	
	/**
	 * 根据名称找到指定的header便利器并遍历对象
	 * @param name
	 */
	public static void iteratHeadersByName(HttpResponse response,String name){
		HeaderIterator ht;
		if(name!=null&&!name.equals("")){
			ht = response.headerIterator(name);
		}else{
			ht = response.headerIterator();
		}
		
		while(ht.hasNext()){
			Header header = ht.nextHeader();
			System.out.println(header.getName()+" = "+header.getValue());
		}
	}
	/**
	 * 将Http消息解析成单独的消息头元素
	 */
	public static void iteratHeaderElementByName(HttpResponse response,String name){
		HeaderIterator ht;
		if(name!=null&&!name.equals("")){
			ht = response.headerIterator(name);
		}else{
			ht = response.headerIterator();
		}
		HeaderElementIterator het = new BasicHeaderElementIterator(ht);
		while (het.hasNext()) {
			HeaderElement elem = het.nextElement();
			System.out.println(elem.getName() + " = " + elem.getValue());
			NameValuePair[] params = elem.getParameters();
			for (int i = 0; i < params.length; i++) {
				System.out.println(" " + params[i]);
			}
		}
	}
}
