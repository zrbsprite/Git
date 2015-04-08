package com.cmcc.slience.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cmcc.slience.test.entity.Cat;
import com.cmcc.slience.test.j2se.EncryptionUtil;

public class Test {

	public static void main(String[] args) {
//		testRegex();
		/*HttpEntity entity = DownLoadIcon.getEntity("light");
		DownLoadIcon.getPicFromWebsite(entity);*/
		
//		String str = "http://1517.oadz.com/jcnt;C1;1517;.;75wmbKMngXbzBM+bu5sBPsElzag=;?1&http%3A//localhost%3A8080/dgidfg/personalityDomainApply.htm&http%3A//localhost%3A8080/dgidfg/my-order.htm&-&INPUT*-*426*317&-&ozsru=-&ozscr=1366*768&ozpoc=-&ozprm=-&oznvs=-&ozrec=-&ozrand=1397457021";
//		System.out.println(URLDecoder.decode(str));
		
		/*
		List<String> list = new ArrayList<String>();
		list.add("1234");
		list.add("3212");
		list.add("9870");
		list.add("23456");
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = list;
		list2.addAll(list);
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			if(list.get(i).equals("1234")){
				str="1111";
				list2.remove(list.get(i));
				list3.remove(list.get(i));
				break;
			}
		}
		for(int i=0;i<list2.size();i++){
			System.out.println(list2.get(i));
		}
		System.out.println("--------------------------------------------");
		for(int i=0;i<list3.size();i++){
			System.out.println(list3.get(i));
		}*/
		
		/*List<Cat> cats = new ArrayList<Cat>(){{
			add(new Cat("123","123"));
			add(new Cat("234","234"));
			add(new Cat("345","345"));
			add(new Cat("567","567"));
			add(new Cat("456","456"));
		}};
		
		List<Cat> cats1 = cats;*/
		/*for(Cat cat:cats1){
			System.out.println(cat.toString());
		}
		*/
		/*System.out.println(cats.size());
		Cat c = null;
		for(Cat cat : cats){
			if(cat.getName().equals("123")){
//				cat.setName("111111");
				c = cat;
			}
		}*/
	/*	cats.remove(c);
		for(Cat cat:cats){
			System.out.println(cat.toString());
		}
		enOrDecode();
		*/
		
		testArray();
	}
	
	/**
	 * 测试正则表达式
	 */
	public static void testRegex(){
		String line = "explain: \"inquiry；[计] query；polling；inquire about；<br />\" + netExplain + zhExplain";
		 String regex = "explain:[\\s]*+\"(.*<br />)+\"";
		 Pattern pt = Pattern.compile(regex);
		 Matcher mt = pt.matcher(line);
		if (mt.find()) {
			String result = mt.group(1);
			result = result.replaceAll("(\\[.*\\]|[a-z]*\\.)", "<strong>$1</strong>");
			System.out.println(result);
		}
	}
	
	public static void enOrDecode(){
		String str = EncryptionUtil.encrypt("zhangribo");
		System.out.println(str);
		System.out.println(EncryptionUtil.decrypt("626BB1CC7C299D304EEB0BC65799BD5E"));
	}
	
	
	public static void testArray(){
		String[] str = new String[3];
		System.out.println(str.length);
		
		List<String> list = new ArrayList<String>(3);
		list.add("123");
		list.add("234");
		System.out.println(list.size());
		String[] array = list.toArray(new String[0]);
		System.out.println(array[0]+"==========="+array[1]);
	}
}