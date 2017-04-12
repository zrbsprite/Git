package ${servicePath}.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyht.common.BaseServiceImpl;
import com.zyht.common.BaseDao;
import ${daoPath}.${mapperName};
import ${entityPath}.${entityName};
import ${entityPath}.${exampleName};
import ${servicePath}.${serviceName};

@Service("${serviceVarName}")
public class ${serviceImplName} extends BaseServiceImpl<${entityName}, ${exampleName}> implements ${serviceName} {

	@Resource
	private ${mapperName} ${mapperVarName};
	
	@Override
	public BaseDao<${entityName}, ${exampleName}> dao(){

		return ${mapperVarName};
	}
}
