package com.cmcc.slience.htmlparser;

import java.net.URL;
import java.net.URLConnection;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;

public class HelloHtmlparser {

	public static void main(String[] args) {
		String spec = "http://www.baidu.com";
		try {
			URL url = new URL(spec);
			URLConnection connection = url.openConnection();
			Parser parser = new Parser(connection);
			TextExtractingVisitor nv = new TextExtractingVisitor();
			parser.visitAllNodesWith(nv);
			//获得萃取后的网页内容
			String et = nv.getExtractedText();
			System.out.println(et);
			
			NodeFilter filter = new LinkStringFilter("www.baidu.com");
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            nodes.toHtml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
