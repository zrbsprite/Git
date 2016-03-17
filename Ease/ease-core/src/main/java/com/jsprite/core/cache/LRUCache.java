package com.jsprite.core.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 保留最近访问, 删除最早访问的. LRU 实现
 * 
 * @author Wen
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends AbstractCacheMap<K, V> {

	/**
	 * 保留最近访问, 删除最早访问的.
	 * 
	 * @param cacheSize
	 *            缓存大小 0->不限制
	 * @param defaultExpire
	 *            存活时间 0->不限制
	 * @param stimeIndex
	 *            参考时间点, 1->最后一次访问时间, 0->初始化时间
	 */
	@SuppressWarnings("serial")
	public LRUCache(int cacheSize, long defaultExpire, int stimeIndex) {

		super(cacheSize, defaultExpire, stimeIndex);

		// linkedHash已经实现LRU算法
		// 是通过双向链表来实现，当某个位置被命中，通过调整链表的指向将该位置调整到头位置，新加入的内容直接放在链表头，如此一来，最近被命中的内容就向链表头移动，需要替换时，链表最后的位置就是最近最少使用的位置
		this.cacheMap = new LinkedHashMap<K, CacheObject<K, V>>(cacheSize + 1, 1f, true) {

			/**
			 * 方法名称：removeEldestEntry<br>
			 * 描述：如果返回false表示不删除 <br>
			 * 作者：ZRB <br>
			 * 修改日期：2016年3月14日下午4:35:36
			 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
			 * @param eldest
			 * @return
			 *
			 */
			@Override
			protected boolean removeEldestEntry(
					Map.Entry<K, CacheObject<K, V>> eldest) {

				return LRUCache.this.removeEldestEntry(eldest);
			}

		};
	}

	private boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {

		if (cacheSize == 0)
			return false;

		return size() > cacheSize;
	}

	/**
	 * 只需要实现清除过期对象就可以了,linkedHashMap已经实现LRU
	 */
	@Override
	protected int eliminateCache() {
		if (!isNeedClearExpiredObject()) {
			return 0;
		}
		Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			CacheObject<K, V> cacheObject = iterator.next();
			if (cacheObject.isExpired()) {
				iterator.remove();
				count++;
			}
		}
		return count;
	}

}