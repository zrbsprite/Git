package com.penzias.mangix.redis.service;

public interface RedisService {

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	public abstract Long del(String... keys);

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	public abstract Long del(byte[]... keys);

	/**
	 * 获取key
	 */
	public abstract String get(String key);

	/**
	 * 获取key
	 */
	public abstract byte[] get(byte[] key);

	/**
	 * 执行脚本
	 * 
	 * @param script
	 * @param resultType
	 * @param value
	 * @return
	 */
	public abstract <T> T eval(String script, Class<T> resultType,
			String... value);

	/**
	 * 执行脚本
	 * 
	 * @param scriptSha
	 * @param resultType
	 * @param value
	 * @return
	 */
	public abstract <T> T evalSha(String scriptSha, Class<T> resultType,
			String... value);

	/**
	 * list.add
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract Long lPush(String key, String value);

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract Long lPush(String key, String... value);

	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	public abstract Long lLen(String key);

	/**
	 * 删除目标redis全部DB缓存
	 */
	public abstract void flushAll();

	/**
	 * 预加载脚本
	 * 
	 * @param script
	 * @return sha值
	 */
	public abstract String scriptLoad(String script);

	public abstract Long lPush(String key, String value, Long seconds);

	/**
	 * key 添加有效时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	public abstract Boolean expire(String key, Long seconds);

	/**
	 * 判断map中是否存在
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract Boolean hExists(String key, String value);

	/**
	 * 获取并移除 队首
	 * @param key
	 * @return
	 */
	public abstract String lPop(String key);

}