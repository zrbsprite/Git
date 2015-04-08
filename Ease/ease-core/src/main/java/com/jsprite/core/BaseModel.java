package com.jsprite.core;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public abstract class BaseModel {

	@Id
	@GeneratedValue(generator="uuidGenerator")
	@GenericGenerator(name="uuidGenerator", strategy="uuid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
