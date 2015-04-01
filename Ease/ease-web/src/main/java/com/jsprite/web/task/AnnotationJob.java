package com.jsprite.web.task;

import com.jsprite.core.annotation.TriggerMethod;
import com.jsprite.core.annotation.TriggerType;

@TriggerType(cronExpression="50,55 * * * * ?")
public class AnnotationJob {
	
	@TriggerMethod
	public void excute(){
		
	}
}
