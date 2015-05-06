package com.jsprite.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jsprite.core.BaseService;
import com.jsprite.web.dao.LicenceDao;
import com.jsprite.web.model.LicenceModel;
import com.jsprite.web.service.LicenceService;

@Service("licenceService")
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
