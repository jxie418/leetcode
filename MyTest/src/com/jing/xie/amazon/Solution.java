package com.jing.xie.amazon;

import java.util.Map.Entry;
import java.util.*;
public class Solution {
  private Comparator cp = new ProductComparator();
  private Map<String,Integer> map = new TreeMap<String,Integer>(); 
  public void itemPurchased(String pid) {
      Integer value = map.get(pid);
      map.put(pid, value == null ? 1 : value + 1);
  }
  public int getRanking(String pid) {
      Integer value = map.get(pid);
      return value == null ? 0 : value.intValue();
  }
  public List<String> getTopTenProducts() {
      List<String> list =new ArrayList<String>(10);
      List<Map.Entry<String,Integer>> tmp = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
      Collections.sort(tmp,cp);
      int size = 10;
      for (Map.Entry<String,Integer> entry: tmp) {
          if (size-- > 0) {
              list.add(entry.getKey());
          } else {
              break;
          }
      }
      return list;
  }
}

class Product implements Comparable<Product>{
  private String pid;
  private int  rank;
  public Product(String pid, int rank) {
    this.pid ="";
    this.rank  = rank;
  }
  public void increase() {
    ++rank;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pid == null) ? 0 : pid.hashCode());
    result = prime * result + rank;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (pid == null) {
      if (other.pid != null)
        return false;
    } else if (!pid.equals(other.pid))
      return false;
    if (rank != other.rank)
      return false;
    return true;
  }
  public String getPid() {
    return pid;
  }
  public void setPid(String pid) {
    this.pid = pid;
  }
  public int getRank() {
    return rank;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }
  @Override
  public int compareTo(Product o) {
    return rank - o.getRank();
  }

  @Override
  public String toString() {
    return pid;
  }
  
}
class ProductComparator implements Comparator<Map.Entry<String,Integer>> {
  @Override
  public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
    return e2.getValue().compareTo(e1.getValue());
 }
}