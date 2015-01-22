package com.jing.xie.thread;

/**
 * Volatile variable if two thread(t1 and t2) are accessing the same object; and update a variable which is declared as
 * volatile then it means t1 and t2 can make their own local cache of the object except the variable which is declared
 * as a volatile so the volatile variable will have only one main copy which will be update by different threads and
 * updation by one thread to the volatile variable will immediatly reflect to the other thread. So the volatile is used
 * in the thread context;
 * 
 * @author JXie
 * 
 */
public class Counter extends Thread {
  private volatile boolean counting = true;
  private int counter = 1;

  public void run() {
    while (counting) {
      System.out.println(counter);
      counter++;
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stopCounting() {
    counting = false;
  }
}
