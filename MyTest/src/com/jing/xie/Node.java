package com.jing.xie;

import java.util.ArrayList;
import java.util.List;

public class Node {

  public int val;
  public List<Node> neighbor;
  public boolean visited;
  
  public Node(int i) {
    this.val = i;
    neighbor = new ArrayList<Node>();
    visited = false;
  }
}
