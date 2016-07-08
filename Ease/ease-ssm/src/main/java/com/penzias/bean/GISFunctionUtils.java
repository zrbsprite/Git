package com.penzias.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class GISFunctionUtils {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String GeocodingAPIURL="http://api.map.baidu.com/geocoder/v2/"
			+ "?ak=8fdb90b5c858bda85705faef17f5c80f&output=json&coordtype=wgs84ll&location=";
	
	private static final Map<Integer, String> statusInfo = new HashMap<Integer, String>();
	
	private static final BasicCookieStore cookieStore = new BasicCookieStore();
	private static final CookieSpecProvider easySpecProvider = new DefaultCookieSpecProvider();
	private static final Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider>create()
			.register(CookieSpecs.DEFAULT, easySpecProvider).build();
	private static final RequestConfig requestConfig = RequestConfig.custom()
			.setCookieSpec("easy").setSocketTimeout(10000)
			.setConnectTimeout(10000).build();

	static{
		statusInfo.put(0, "正常");
		statusInfo.put(1, "服务器内部错�?);
		statusInfo.put(2, "请求参数非法");
		statusInfo.put(3, "权限校验失败");
		statusInfo.put(4, "配额校验失败");
		statusInfo.put(5, "ak不存在或者非�?);
		statusInfo.put(101, "服务禁用");
		statusInfo.put(102, "不通过白名单或者安全码不对");
		statusInfo.put(200, "无权�?);
		statusInfo.put(300, "配额错误");
	}
	
	/**
	 * <b>作�?</b> Bob<br/>
	 * <b>修改时间�?/b>2016�?�?3�?- 下午1:47:19<br/>
	 * <b>功能说明�?/b>	获取指定坐标的地理信�?br/>
	 * @param loc 地理信息如：116.30814954222,40.056885091681
	 * @return  json对象形如：{"result":{"addressComponent":{"adcode":"110102","city":"北京�?,"country":"中国","country_code":0,"direction":"附近",
	 * 						"distance":"38","district":"西城�?,"province":"北京�?,"street":"湾子�?,"street_number":"甲一�?},"business":"西客�?广外大街,马连�?,
	 * 						"cityCode":131,"formatted_address":"北京市西城区湾子街甲一�?,"location":{"lat":39.89534418269883,"lng":116.33283972795194},
	 * 						"poiRegions":[{"direction_desc":"�?,"name":"马连道北�?,"tag":"房地�?}],"pois":[],"sematic_description":"马连道北里内"},"status":0}
	 * @throws HttpException 说明此次http请求异常，需要调用者处理此异常
	 */
	public JSONObject fetchPosition(String loc) throws HttpException{
		String url = GeocodingAPIURL.concat(loc);
		
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieSpecRegistry(r)
				.setDefaultCookieStore(cookieStore)
				.setDefaultRequestConfig(requestConfig).build();
		HttpGet get = new HttpGet(url);
		get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setHeader("Accept-Encoding","gzip, deflate, sdch");
		get.setHeader("Cache-Control","max-age=0");
		get.setHeader("Connection","keep-alive");
		get.setHeader("User-Agent","Mozilla/5.0 (Windws NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		JSONObject result = null;
		CloseableHttpResponse response = null;
		try{
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			String postResponse = IOUtils.toString(is, "utf-8");
			System.out.println(postResponse);
			result = JSONObject.parseObject(postResponse);
			int error=result.getIntValue("status");
			if(error!=0){
				String errorMsg;
				if(error>=200){
					errorMsg = statusInfo.get(200);
				}else if(error>=200){
					errorMsg = statusInfo.get(300);	
				}else{
					errorMsg = statusInfo.get(error);
				}
				logger.error("请求接口获取用户地理信息异常�? + errorMsg);
				throw new HttpException(errorMsg);
			}
		} catch (HttpException e){
			logger.error("发送http请求异常，查询用户地理位置信息异常�?);
			e.printStackTrace();
			throw e;
		} catch (IOException e){
			logger.error("发送http请求异常，查询用户地理位置信息IO异常�?);
			e.printStackTrace();
			throw new HttpException("http请求IO异常");
		}finally{
			try{
				client.close();
			} catch (IOException e){
				e.printStackTrace();
			}
			if(null!=response){
				try{
					response.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}