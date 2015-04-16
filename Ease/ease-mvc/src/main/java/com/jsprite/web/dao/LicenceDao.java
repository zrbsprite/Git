package com.jsprite.web.dao;

import com.jsprite.core.IDao;
import com.jsprite.web.model.LicenceModel;

public interface LicenceDao extends IDao<LicenceModel>{

	public LicenceModel getBySerializeCode(String code);
}
