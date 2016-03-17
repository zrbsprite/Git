package com.jsprite.core.cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 
/**
 * 默认实现
 */
public abstract class AbstractCacheMap<K,V> implements Cache<K,V> {
 
    class CacheObject<K2,V2> {
        CacheObject(K2 key, V2 value, long ttl,int stimeIndex) {
            this.key = key;
            this.cachedObject = value;
            this.ttl = ttl;
            this.createTime = System.currentTimeMillis();
            this.lastAccess = this.createTime;
            this.stimeIndex = stimeIndex;
        }
 
        final K2 key;
        final V2 cachedObject;
        long createTime;        // 创建时间
        long lastAccess;        // 最后访问时间
        long accessCount;       // 访问次数
        long ttl;               // 对象存活时间(time-to-live)
        int stimeIndex;
        
        /**
         * 判断是否有效, false-无效 true-有效
         * @return
         */
        boolean isExpired() {
            if (ttl == 0) {
                return false;
            }
            
            long tage = 0;
            if(stimeIndex==1){
            	tage = lastAccess;
            }else if(stimeIndex==0){
            	tage = createTime;
            }else{
            	return false;
            }
            
			return tage + ttl < System.currentTimeMillis();
        }
        
        V2 getObject() {
            lastAccess = System.currentTimeMillis();
            accessCount++;
            return cachedObject;
        }
    }
 
    protected Map<K,CacheObject<K,V>> cacheMap;
 
    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private final Lock readLock = cacheLock.readLock();
    private final Lock writeLock = cacheLock.writeLock();
 
 
 
    protected int cacheSize;      // 缓存大小 , 0 -> 无限制
     
    protected  boolean existCustomExpire ; //是否设置默认过期时间
     
    @Override
	public int getCacheSize() {
        return cacheSize;
    }
 
    protected long defaultExpire;     // 默认过期时间, 0 -> 永不过期
    
    protected int stimeIndex;     // 默认参考时间, 0->最后一次访问时间, 1-初始化时间
   
    /**
     * 
     * @param cacheSize 缓存大小 , 0 -> 无限制
     * @param defaultExpire 默认过期时间, 0 -> 永不过期
     * @param stimeIndex 默认参考时间选项, 0->最后一次访问时间, 1-初始化时间
     */
    public AbstractCacheMap(int cacheSize ,long defaultExpire,int stimeIndex){
        this.cacheSize  = cacheSize ;
        this.defaultExpire  = defaultExpire ;
        this.stimeIndex = stimeIndex;
    }
    
//    public AbstractCacheMap(int cacheSize ,long defaultExpire){
//        this.cacheSize  = cacheSize ;
//        this.defaultExpire  = defaultExpire ;
//        this.stimeIndex = 0;
//    }
 
     
    @Override
	public long getDefaultExpire() {
        return defaultExpire;
    }
 
 
    protected boolean isNeedClearExpiredObject(){
        return defaultExpire > 0 || existCustomExpire ;
    }
 
     
    @Override
	public void put(K key, V value) {
        put(key, value, defaultExpire );
    }
 
 
    @Override
	public void put(K key, V value, long expire) {
        writeLock.lock();
 
        try {
            CacheObject<K,V> co = new CacheObject<K,V>(key, value, expire,stimeIndex);
            if (expire != 0) {
                existCustomExpire = true;
            }
            if (isFull()) {
                eliminate() ;
            }
            cacheMap.put(key, co);
        }
        finally {
            writeLock.unlock();
        }
    }
 
 
 
    /**
     * {@inheritDoc}
     */
    @Override
	public V get(K key) {
        readLock.lock();
        try {
            CacheObject<K,V> co = cacheMap.get(key);
            if (co == null) {
                return null;
            }
            if (co.isExpired() == true) {
                cacheMap.remove(key);
                return null;
            }
 
            return co.getObject();
        }
        finally {
            readLock.unlock();
        }
    }
     
    @Override
	public final int eliminate() {
        writeLock.lock();
        try {
            return eliminateCache();
        }
        finally {
            writeLock.unlock();
        }
    }
     
    /**
     * 淘汰对象具体实现
     * 
     * @return
     */
    protected abstract int eliminateCache(); 
 
 
     
    @Override
	public boolean isFull() {
        if (cacheSize == 0) {//o -> 无限制
            return false;
        }
        return cacheMap.size() >= cacheSize;
    }
 
     
    @Override
	public void remove(K key) {
        writeLock.lock();
        try {
            cacheMap.remove(key);
        }
        finally {
            writeLock.unlock();
        }
    }
 
     
    @Override
	public void clear() {
        writeLock.lock();
        try {
            cacheMap.clear();
        }
        finally {
            writeLock.unlock();
        }
    }
 
    @Override
	public int size() {
        return cacheMap.size();
    }
 
     
    @Override
	public boolean isEmpty() {
        return cacheMap.isEmpty();//   size() == 0;
    }
    
	@Override
	public List<K> getAllKey() {
		return new ArrayList<K>(cacheMap.keySet());
	}
}