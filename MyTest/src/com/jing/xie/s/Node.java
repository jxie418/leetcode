package com.jing.xie.s;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String val;
    List<List<String>> path;
    int minLen = Integer.MAX_VALUE;
    
    public Node(String val) {
      this.val = val;
      path = new ArrayList<List<String>>();
    }
    
    public void addPath(List<String> newPath) {
      minLen = Math.min(newPath.size(), minLen);
      if (newPath.size() <=minLen) {
        path.add(newPath);
      }
    } 
}
