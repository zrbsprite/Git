package com.cmcc.slience.http.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 从百度字典查询英文或汉语
 * @author Administrator
 *
 */
public class BaiduDictionaryUtil {
	
	/**
	 * 执行查询
	 * @param word
	 * @return
	 */
	public static String run(String word) {
		word = word.toLowerCase();
		CloseableHttpClient  httpClient = HttpClients.createDefault();
		URI uri = buildURI(word);
		if(uri==null){
			return null;
		}
		/*try {
			String str = URLDecoder.decode(uri.toString(), "UTF-8");
			uri = new URI(str);
		} catch (Exception e) {
			//使用正常的uri
		}*/
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
        int code = response.getStatusLine().getStatusCode();
        if(code!=200){
        	System.out.println(code);
        	return null;
        }
        InputStream is = null;
        String result = "";
        String regex = "explain:[\\s]*+\"(.*<br />)+\"";
        Pattern pt = Pattern.compile(regex);
		if (response != null) {
			HttpEntity entity = response.getEntity();
			try {
				is = entity.getContent();
				Reader reader = new InputStreamReader(is,"UTF-8");
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					Matcher mt = pt.matcher(line);
					if (mt.find()) {
						result = mt.group(1);
						/*result = result.replaceAll("<br />", "\n");
						result = StringEscapeUtils.unescapeHtml(result);*/
						result = result.replaceAll("(\\[.*\\]|[a-z]*\\.)", "<strong>$1</strong>");
						break;
					}
				}
				br.close();
				reader.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
        }
		return result;
	}
	
	
	/**
	 * 构建uri
	 * @param word
	 * @return
	 */
	private static URI buildURI(String word){
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		builder.setHost("dict.baidu.com");
		builder.setPath("/s");
		builder.setParameter("wd", word);
		URI uri = null;
		try {
			uri = builder.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			uri = null;
		}
		return uri;
	}
}
