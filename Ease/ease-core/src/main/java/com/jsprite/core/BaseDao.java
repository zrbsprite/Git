package com.jsprite.core;

import java.util.List;

public abstract class BaseDao implements IDao<BaseModel> {

	public <T> T findById(String id) {
		return null;
	}

	public List<BaseModel> query(BaseModel model) {
		return null;
	}

	public void add(BaseModel model) {
	}

	public boolean deleteById(String id) {
		return false;
	}

}
