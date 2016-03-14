package com.penzias.mangix.redis.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.penzias.mangix.redis.service.RedisService;

@Service()
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	private byte[] getStringSerialize(String value) {
		return redisTemplate.getStringSerializer().serialize(value);
	}

	private String getStringDeserialize(byte[] value) {
		return redisTemplate.getStringSerializer().deserialize(value);
	}

	private byte[][] getByteParams(String... params) {
		byte[][] p = new byte[params.length][];
		for (int i = 0; i < params.length; i++)
			p[i] = getStringSerialize(params[i]);
		return p;
	}

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void set(String key, String value) {
		set(getStringSerialize(key), getStringSerialize(value));
	}

	/**
	 * 将字符串值 value 关联到 key 。
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void set(byte[] key, byte[] value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) {
				connection.set(key, value);
				return null;
			}
		});
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	@Override
	public Long del(String... keys) {
		return del(getByteParams(keys));
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	@Override
	public Long del(byte[]... keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.del(keys);
			}
		});
	}

	/**
	 * 获取key
	 */
	@Override
	public String get(String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection
						.get(getStringSerialize(key)));
			}
		});
	}

	/**
	 * 获取key
	 */
	@Override
	public byte[] get(byte[] key) {
		return redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) {
				return connection.get(key);
			}
		});
	}

	/**
	 * 执行脚本
	 * 
	 * @param script
	 * @param resultType
	 * @param value
	 * @return
	 */
	@Override
	public <T> T eval(String script, Class<T> resultType, String... value) {
		String scriptSha = scriptLoad(script);
		return evalSha(scriptSha, resultType, value);
	}

	/**
	 * 执行脚本
	 * 
	 * @param scriptSha
	 * @param resultType
	 * @param value
	 * @return
	 */
	@Override
	public <T> T evalSha(String scriptSha, Class<T> resultType, String... value) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) {
				ReturnType returnType =null;
				if (resultType == null) {
					returnType= ReturnType.STATUS;
				}else if (resultType.isAssignableFrom(String.class)) {
					returnType= ReturnType.STATUS;
				}else if (resultType.isAssignableFrom(List.class)) {
					returnType= ReturnType.MULTI;
				}else if (resultType.isAssignableFrom(Boolean.class)){
					returnType= ReturnType.BOOLEAN;
				}else if (resultType.isAssignableFrom(Long.class)) {
					returnType= ReturnType.INTEGER;
				}
				return connection.evalSha(scriptSha, returnType, value.length, getByteParams(value));
			}
		});
	}

	/**
	 * list.add
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Long lPush(String key, String value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(getStringSerialize(key),
						getStringSerialize(value));
			}
		});
	}
	
	@Override
	public Long lPush(String key, String value,Long seconds) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				byte[] keyB = getStringSerialize(key);
				Long res =	connection.lPush(keyB, getStringSerialize(value));
				if(seconds!=null && seconds>0) connection.expire(keyB, seconds);
				return res;
			}
		});
	}

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Boolean expire(String key,Long seconds) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				byte[] keyB = getStringSerialize(key);
				return connection.expire(keyB, seconds);
			}
		});
	}

	/**
	 * list.addall
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Long lPush(String key, String... value) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(getStringSerialize(key),
						getByteParams(value));
			}
		});
	}
	
	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Long lLen(String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) {
				return connection.lLen(getStringSerialize(key));
			}
		});
	}
	
	/**
	 * list.size
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Boolean hExists(String key,String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) {
				return connection.hExists(getStringSerialize(key),getStringSerialize(value));
			}
		});
	}

	/**
	 * 删除目标redis全部DB缓存
	 */
	@Override
	public void flushAll() {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.flushAll();
				return null;
			}
		});
	}

	/**
	 * 预加载脚本
	 * 
	 * @param script
	 * @return sha值
	 */
	@Override
	public String scriptLoad(String script) {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return connection.scriptLoad(getStringSerialize(script));
			}
		});
	}
	
	@Override
	public String lPop(String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) {
				return getStringDeserialize(connection.lPop(getStringSerialize(key)));
			}
		});
	}

}
