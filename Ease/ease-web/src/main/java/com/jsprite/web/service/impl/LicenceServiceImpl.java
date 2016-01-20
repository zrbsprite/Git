package com.jsprite.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jsprite.core.BaseService;
import com.jsprite.core.IDao;
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

	/**方法名称：getBaseDao <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年1月20日上午11:27:17 
	 * @see com.jsprite.core.BaseService#getBaseDao() 
	 * @return
	 */
	@Override
	public IDao<LicenceModel> getBaseDao() {
		return licenceDao;
	}
}
