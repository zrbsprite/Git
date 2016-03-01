package com.penzias.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.penzias.core.BasicServiceImpl;
import com.penzias.core.interfaces.BasicMapper;
import com.penzias.dao.${mapperName};
import com.penzias.entity.${entityName};
import com.penzias.entity.${exampleName};
import com.penzias.service.${serviceName};

@Service("${serviceVarName}")
public class ${serviceImplName} extends BasicServiceImpl<${exampleName}, ${entityName}> implements ${serviceName} {

	@Resource
	private ${mapperName} ${mapperVarName};
	
	@Override
	public BasicMapper<${exampleName}, ${entityName}> getMapper(){

		return ${mapperVarName};
	}

}
