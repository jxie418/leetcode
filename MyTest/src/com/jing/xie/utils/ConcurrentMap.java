package com.jing.xie.utils;

import java.util.Map;
public interface ConcurrentMap<K, V> extends Map<K, V> {
    boolean remove(Object key, Object value);    
    boolean replace(K key, V oldValue, V newValue);
    V replace(K key, V value);
}
