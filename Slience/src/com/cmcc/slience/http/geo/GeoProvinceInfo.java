package com.cmcc.slience.http.geo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GeoProvinceInfo {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		FileReader in = null;
		BufferedReader br = null;
		try {
			in = new FileReader(dir + "/WebContent/html/jQuery/province.html");
			br = new BufferedReader(in);
			String line = "";
			StringBuffer sb = new StringBuffer();
			Pattern p = Pattern.compile("<li\\s*value=\"(\\d*)\"><a href=.*>(\\S*)</a></li>");
			while((line=br.readLine())!=null){
				Matcher m = p.matcher(line);
				if(m.find()){
					String val = m.group(1);
					String name = m.group(2);
					sb.append("<option value='"+val+"'>"+name+"</option>");
					sb.append("\n");
				}
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)
					br.close();
				if(in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
