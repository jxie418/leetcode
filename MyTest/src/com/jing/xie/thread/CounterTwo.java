package com.jing.xie.thread;

public class CounterTwo {
  volatile long count = 0;

  public synchronized void add(long value) {
    this.count ++;
    System.out.println(count);
  }
}
