package com.jing.xie.thread;

public class CounterThread extends Thread {
  protected CounterTwo counter = null;

  public CounterThread(CounterTwo counter) {
    this.counter = counter;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      counter.add(i);
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    CounterTwo counter = new CounterTwo();
    Thread threadA = new CounterThread(counter);
    Thread threadB = new CounterThread(counter);

    threadA.start();
    threadB.start();
  }
}
