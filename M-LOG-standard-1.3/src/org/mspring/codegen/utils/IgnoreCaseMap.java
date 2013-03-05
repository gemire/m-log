/**
 * 
 */
package org.mspring.codegen.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class IgnoreCaseMap<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 1L;
    private Map<String, K> keyMap = new HashMap<String, K>();

    public IgnoreCaseMap(Map<K, V> m) {
        super(m);
        for (K key : m.keySet()) {
            if (key instanceof String) {
                keyMap.put(key.toString().toLowerCase(), key);
            }
        }
    }

    public IgnoreCaseMap() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.TreeMap#containsKey(java.lang.Object)
     */
    @Override
    public boolean containsKey(Object key) {
        String keyStr = key.toString().toLowerCase();
        if (keyMap.containsKey(keyStr) == false) {
            return false;
        }
        Object realKey = keyMap.get(keyStr);

        return super.containsKey(realKey);
    }

    public V get(Object key) {
        String keyStr = key.toString().toLowerCase();
        if (keyMap.containsKey(keyStr) == false) {
            return null;
        }
        Object realKey = keyMap.get(keyStr);

        return super.get(realKey);
    }

    public V put(K paramK, V paramV) {
        if (paramK instanceof String) {
            String keyStr = ((String) paramK).toLowerCase();
            keyMap.put(keyStr, paramK);
        }
        return super.put(paramK, paramV);
    }
}
