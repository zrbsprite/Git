package com.jsprite.core;

import java.util.List;

public abstract class BaseDao implements IDao<BaseModel> {

	@Override
	public <T> T findById(String id) {
		return null;
	}

	@Override
	public List<BaseModel> query(BaseModel model) {
		return null;
	}

	@Override
	public void add(BaseModel model) {
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

}
