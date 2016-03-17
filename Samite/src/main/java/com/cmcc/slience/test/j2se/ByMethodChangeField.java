package com.cmcc.slience.test.j2se;

import java.util.ArrayList;
import java.util.List;

import com.cmcc.slience.test.entity.Cat;

public class ByMethodChangeField{
	
	public void jrun(){
		int i = 10;
		change(i);
		System.out.println("int:"+i);
		
		Integer k = new Integer(123);
		change(k);
		System.out.println("Integer:"+k.toString());
		
		Cat cat = new Cat("chan","121");
		change(cat);
		System.out.println("Cat Name:"+cat.getName());
		
		String str = "123";
		change(str);
		System.out.println("string: "+str);
		
		String s = new String("123");
		change(s);
		System.out.println("String: "+s.toString());
		
		List<String> list = new ArrayList<String>();
		list.add("old");
		change(list);
		System.out.println(list.get(0));
		
		List<Cat> cats = new ArrayList<Cat>();
		Cat cat0 = new Cat("old","2");
		cats.add(cat0);
		change2(cats);
		System.out.println("cat's name:"+cats.get(0).getName());
	}
	
	private void change(int i){
		i++;
	}
	
	private void change(String str){
		str += "zrb";
	}
	
	private void change(Integer k){
		k += 10;
	}
	
	private void change(Cat cat){
		cat.setName("123");
	}
	
	private void change(List<String> list){
		String str = list.get(0);
		//ֻ��strָ����list��get(0)���ַ���ֵ������list��get(0)ֵ����ԭ����
		System.out.println("ԭ�����ַ���ֵ��"+str);
		str = "changed";
		System.out.println("str is changed��"+str);
	}
	
	private void change2(List<Cat> list){
		Cat cat = list.get(0);
		//cat��ָ����list.get(0)���޸���cat��name����û�޸�ָ��
		System.out.println("ԭ�����ַ���ֵ��"+cat.getName());
		cat.setName("changed");
		System.out.println("cat's name is changed��"+cat.getName());
	}
	
	
	
	public static void main(String[] args) {
		ByMethodChangeField bean = new ByMethodChangeField();
		bean.jrun();
	}
	
}
