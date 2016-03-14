package com.jsprite.core.cache;
import java.util.HashMap;
import java.util.Iterator;
 
/**
 * 保留最多访问,删除最少访问
 * LFU实现
 *
 * @param <K>
 * @param <V>
 */
public class LFUCache<K,V> extends AbstractCacheMap<K, V> {
	/**
	 * 保留最多访问,删除最少访问
	 * @param cacheSize 缓存大小 0->不限制
	 * @param defaultExpire 存活时间 0->不限制
	 * @param stimeIndex 参考时间点,  1->最后一次访问时间, 0->初始化时间
	 */
    public LFUCache(int cacheSize, long defaultExpire,int stimeIndex) {
        super(cacheSize, defaultExpire,stimeIndex);
        cacheMap = new HashMap<K, CacheObject<K,V>>(cacheSize+1) ;
    }
    /**
     * 实现删除过期对象 和 删除访问次数最少的对象 
     * 
     */
    @Override
    protected int eliminateCache() {
        Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
        int count  = 0 ;
        long minAccessCount = Long.MAX_VALUE  ;
        while(iterator.hasNext()){
            CacheObject<K, V> cacheObject = iterator.next();
            if(cacheObject.isExpired() ){
                iterator.remove(); 
                count++ ;
                continue ;
            }else{
                minAccessCount  = Math.min(cacheObject.accessCount , minAccessCount)  ;
            }
        }
        if(count > 0 ) return count ;
        if(minAccessCount != Long.MAX_VALUE ){
            iterator = cacheMap.values().iterator();
            while(iterator.hasNext()){
                CacheObject<K, V> cacheObject = iterator.next();
                cacheObject.accessCount  -=  minAccessCount ;
                if(cacheObject.accessCount <= 0 ){
                    iterator.remove();
                    count++ ;
                }
            }
        }
        return count;
    }
 
}