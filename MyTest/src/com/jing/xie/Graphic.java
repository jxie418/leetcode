package com.jing.xie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graphic {

  public static void dfs(Node node) {
    List<Integer> list = new ArrayList<Integer>();
    Stack<Node> stack = new Stack<Node>();
    stack.push(node);
    list.add(node.val);
    node.visited = true;
    while (!stack.isEmpty()) {
      Node n = stack.pop();
      for(Node neighbor : n.neighbor) {
        if (!neighbor.visited) {
          list.add(neighbor.val);
          stack.push(neighbor);
        }
      }
    }
    for (Integer i : list) {
      System.out.println(i);
    }
  }
  
  
  public static void bfs(Node node) {
    List<Integer> list = new ArrayList<Integer>();
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(node);
    list.add(node.val);
    node.visited = true;
    while(!queue.isEmpty()) {
      Node n = queue.poll();
      for (Node neighbor : n.neighbor) {
        if (!neighbor.visited) {
          list.add(neighbor.val);
          queue.add(neighbor);
        }
      }
    }
    for (Integer i : list) {
      System.out.println(i);
    }
  }
}
