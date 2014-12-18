/**
 * 
 */
package com.jing.xie;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author JXie
 * 
 */
public class MiddleSolution {

  private PriorityQueue<Integer> minQueue;
  private PriorityQueue<Integer> maxQueue;

  public MiddleSolution() {
    minQueue = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    maxQueue = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
  }

  public void insertValue(int value) {
    if (minQueue.isEmpty() && maxQueue.isEmpty()) {
      maxQueue.add(value);
    } else if (!maxQueue.isEmpty() && minQueue.isEmpty()) {
      maxQueue.add(value);
    } else if (maxQueue.isEmpty() && !minQueue.isEmpty()) {
      minQueue.add(value);
    } else {
      if (value < maxQueue.peek()) {
        maxQueue.add(value);
      } else {
        minQueue.add(value);
      }
    }
    while (maxQueue.size() > minQueue.size() + 1) {
      minQueue.add(maxQueue.poll());
    }
    while (minQueue.size() > maxQueue.size() + 1) {
      maxQueue.add(minQueue.poll());
    }
  }

  public int getMiddleValue() {
    if (minQueue.isEmpty() && maxQueue.isEmpty()) {
      return 0;
    }
    if (minQueue.size() == maxQueue.size()) {
      return (minQueue.peek() + maxQueue.peek()) >>> 1;
    } else if (maxQueue.size() > minQueue.size()) {
      return maxQueue.peek();
    } else {
      return minQueue.peek();
    }
  }
}
