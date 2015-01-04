package com.jing.xie.leetcode.ds;

import java.util.Stack;

public class MinStack {
  Stack<Integer> stack = new Stack<Integer>();
  Stack<Integer> stackMin = new Stack<Integer>();

  public void push(int x) {
    if (stackMin.isEmpty() || stackMin.peek() >= x) {
      stackMin.push(x);
    }
    stack.push(x);
  }

  public void pop() {
    if (stack.isEmpty()) {
      return;
    }
    if (!stackMin.isEmpty() && stack.peek() <= stackMin.peek()) {
      stackMin.pop();
    }
    stack.pop();
  }

  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peek();
  }

  public int getMin() {
    if (stackMin.isEmpty()) {
      return -1;
    }
    return stackMin.peek();
  }
}
