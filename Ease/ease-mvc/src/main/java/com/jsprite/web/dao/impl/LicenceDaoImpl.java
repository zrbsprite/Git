package com.jsprite.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jsprite.core.BaseDao;
import com.jsprite.web.dao.LicenceDao;
import com.jsprite.web.model.LicenceModel;

public class LicenceDaoImpl extends BaseDao<LicenceModel> implements LicenceDao {

	@Override
	public void fillCriteria(DetachedCriteria criteria, LicenceModel model) {
		//criteria.add(Restrictions.idEq(model.getId()));
		criteria.add(Restrictions.eq("macAddress", model.getMacAddress()));
	}

	@Override
	public LicenceModel getBySerializeCode(String code) {
		LicenceModel model = new LicenceModel();
		model.setSerializeCode(code);
		List<LicenceModel> list = this.getHibernateTemplate().findByExample(model);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
}
