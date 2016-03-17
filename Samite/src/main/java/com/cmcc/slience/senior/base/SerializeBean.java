package com.cmcc.slience.senior.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 描述：java对象的序列化和反序列化<br>
 * 作者：ZRB <br>
 * 修改日期：2015年3月23日下午4:03:19 <br> <br/>
 */
public class SerializeBean implements Serializable{

	private static final long serialVersionUID = -4033990082678175799L;
	
	private int age;
	
	private String name;
 	
	private Date birthday;
	
	private double money;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public static void main(String[] args){
		String path = "E:\\项目\\Eclipse\\Slience\\temp";
		File file = new File(path+"/"+"cat.txt");
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			SerializeBean bean = new SerializeBean();
			bean.setAge(27);
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.YEAR, 1988);
			calendar.set(Calendar.MONTH, 10);
			calendar.set(Calendar.DAY_OF_MONTH, 25);
			bean.setBirthday(calendar.getTime());
			bean.setMoney(80000.25);
			bean.setName("张北洛");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bean);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fos = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fos);
			SerializeBean bean = (SerializeBean) ois.readObject();
			System.out.println(bean.getName());
			ois.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
