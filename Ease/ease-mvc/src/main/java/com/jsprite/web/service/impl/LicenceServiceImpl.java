package com.jsprite.web.service.impl;

import javax.annotation.Resource;

import com.jsprite.core.BaseService;
import com.jsprite.web.dao.LicenceDao;
import com.jsprite.web.model.LicenceModel;
import com.jsprite.web.service.LicenceService;

public class LicenceServiceImpl extends BaseService<LicenceModel> implements LicenceService {

	private LicenceDao licenceDao;
	
	public LicenceDao getLicenceDao() {
		return licenceDao;
	}

	@Resource
	public void setLicenceDao(LicenceDao licenceDao) {
		this.licenceDao = licenceDao;
	}
}
