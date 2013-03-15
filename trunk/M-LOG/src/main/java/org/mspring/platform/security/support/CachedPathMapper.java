package org.mspring.platform.security.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CachedPathMapper extends PathMapper {
    private Map cacheMap;
    private Map cacheAllMap;
    
    public CachedPathMapper(Map cacheMap, Map cacheAllMap) {
        this.cacheMap = Collections.synchronizedMap(cacheMap);
        this.cacheAllMap = Collections.synchronizedMap(cacheAllMap);
    }

    public String get(String path) {
        if (this.cacheMap.containsKey(path)) {
            return (String) this.cacheMap.get(path);
        }

        String result = super.get(path);

        this.cacheMap.put(path, result);
        return result;
    }

    public Collection getAll(String path) {
        if (this.cacheAllMap.containsKey(path)) {
            return (Collection) this.cacheAllMap.get(path);
        }

        Collection result = super.getAll(path);

        this.cacheAllMap.put(path, result);
        return result;
    }

    public void put(String key, String pattern) {
        if (this.cacheMap.containsKey(key)) {
            this.cacheMap.remove(key);
        }
        if (this.cacheAllMap.containsKey(key)) {
            this.cacheAllMap.remove(key);
        }

        super.put(key, pattern);
    }
}