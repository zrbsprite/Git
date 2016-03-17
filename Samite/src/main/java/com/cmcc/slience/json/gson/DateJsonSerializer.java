package com.cmcc.slience.json.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * �������л�ʵ�ù�����
 * @author Administrator
 *
 */
public class DateJsonSerializer implements JsonSerializer<Date> {

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(date.getTime());
	}

}
