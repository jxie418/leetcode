package com.jing.xie;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer,Integer> {

    private static final long serialVersionUID = 1L;
    private int capacity;
    
    public LRUCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Integer v = super.get(key);
        return v == null ? -1 : v.intValue(); 
        
    }
    
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public void set(int key, int value) {
        super.put(key, value);
    }
}
