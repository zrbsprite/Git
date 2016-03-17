package com.jsprite.core.cache;
import java.util.Iterator;
import java.util.LinkedHashMap;
/**
 *  先进先出
 *  FIFO实现
 *
 * @param <K>
 * @param <V>
 */
public class FIFOCache<K, V> extends AbstractCacheMap<K, V> {
 
	/**
	 * 
	 * @param cacheSize 缓存大小 0->不限制
	 * @param defaultExpire 存活时间 0->不限制
	 * @param stimeIndex 参考时间点,  1->最后一次访问时间, 0->初始化时间
	 */
    public FIFOCache(int cacheSize, long defaultExpire ,int stimeIndex) {
        super(cacheSize, defaultExpire ,stimeIndex);
        cacheMap = new LinkedHashMap<K, CacheObject<K, V>>(cacheSize + 1);
    }
 
    @Override
    protected int eliminateCache() {
 
        int count = 0;
        K firstKey = null;
 
        Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
        while (iterator.hasNext()) {
            CacheObject<K, V> cacheObject = iterator.next();
 
            if (cacheObject.isExpired()) {
                iterator.remove();
                count++;
            } else {
                if (firstKey == null)
                    firstKey = cacheObject.key;
            }
        }
 
        if (firstKey != null && isFull()) {//删除过期对象还是满,继续删除链表第一个
            cacheMap.remove(firstKey);
        }
 
        return count;
    }

}