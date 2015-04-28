package com.jsprite.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ease_licence")
public class LicenceModel {

	@Id
	@GeneratedValue(generator="uuidGenerator")
	@GenericGenerator(name="uuidGenerator", strategy="org.hibernate.id.UUIDGenerator")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	//服务器mac地址
	private String macAddress;
	
	//认证类型
	private int type;
	
	//认证日期
	private Date registDate;
	
	//有效期止
	private Date lastDate;
	
	//序列号
	private String serializeCode;

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSerializeCode() {
		return serializeCode;
	}

	public void setSerializeCode(String serializeCode) {
		this.serializeCode = serializeCode;
	}
	
}
