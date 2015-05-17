package com.jsprite.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jsprite.core.EntityArrayable;
import com.jsprite.core.SortKeyValueEntity;
import com.jsprite.core.annotation.Arrayable;

public class EntityFields2ArrayUtil {

	/**
	 * 方法名称: fieldsToArrays<br>
	 * 描述：将实体的属性写入到数组中，实体类必须实现EntityArrayable接口
	 * 作者: ruibo
	 * 修改日期：2015年5月17日下午6:48:46
	 * @param arrayable
	 * @return
	 * @see com.jsprite.core.EntityArrayable
	 */
	public static Object[] fieldsToArrays(EntityArrayable arrayable){
		Class<? extends EntityArrayable> clazz = arrayable.getClass();
		Field[] fields = clazz.getDeclaredFields();
		int len = fields.length;
		String[] orders = arrayable.fetchFieldsOrders();
		if(null!=orders){
			if(orders.length!=len){
				LogUtils.warn("实体属性数目和排序数目不一致，将使用排序属性，不包含在内的属性将被忽略！");
			}
			Object[] array = new Object[orders.length];
			for(int i=0; i<orders.length; i++){
				Field field;
				try {
					field = clazz.getDeclaredField(orders[i]);
					field.setAccessible(true);
					Object value = field.get(arrayable);
					array[i] = value;
				} catch (Exception e) {
					array[i] = " ";
				}
			}
			return array;
		}else{
			Object[] array = new Object[len];
			for(int i=0; i<len; i++){
				Field field = fields[i];
				field.setAccessible(true);
				try {
					Object value = field.get(arrayable);
					array[i] = value;
				} catch (Exception e) {
					array[i] = " ";
				}
			}
			return array;
		}
	}
	
	/**
	 * 方法名称: fieldsToArrays<br>
	 * 描述：使用注解形式的解析</br>
	 * 作者: ruibo
	 * 修改日期：2015年5月17日下午6:48:27
	 * @param obj
	 * @return
	 */
	public static Object[] fieldsToArrays(Object obj){
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<SortKeyValueEntity> list = new ArrayList<SortKeyValueEntity>();
		for(Field field : fields){
			if(field.isAnnotationPresent(Arrayable.class)){
				Arrayable arrayable = field.getAnnotation(Arrayable.class);
				int order = arrayable.order();
				SortKeyValueEntity entity = new SortKeyValueEntity();
				entity.setOrder(order);
				field.setAccessible(true);
				Object value = " ";
				try {
					value = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				entity.setVlaue(value);
				list.add(entity);
			}
		}
		Collections.sort(list);
		if(null==list || list.size()<=0)
			return null;
		Object[] result = new Object[list.size()];
		for(int i=0; i<list.size(); i++){
			result[i] = list.get(i).getVlaue();
		}
		return result;
	}
}
