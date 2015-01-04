package com.jing.xie.leetcode.ds;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, Integer> {
  private int capacity;

  public LRUCache(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  public int get(int key) {
    Integer value = super.get(key);
    if (value != null) {
      return value;
    }
    return -1;
  }

  protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
    return size() > capacity;
  }

  public void set(int key, int value) {
    super.put(key, value);
  }
}