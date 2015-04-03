package com.jsprite.core.wraper;

/**
 * 使用装饰模式处理字符串
 * @author JSprite
 *	齐天大圣模型：猕猴接口-》猴子（具体的）
 *			猕猴接口-》-》变化接口
 *			变化接口--鱼	
 *			变化接口--鸟	
 * filter便是 ：猕猴接口类
 */
public interface Filter {

	public String doFilter(String msg);
}
