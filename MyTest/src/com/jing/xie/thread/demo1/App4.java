package com.jing.xie.thread.demo1;

public class App4 {
  private int count = 0;

  public synchronized void increment() {
    count++;
  }
  public static void main(String[] args) {
    App4 app = new App4();
    app.doWork();
  }

  public void doWork() {
    Thread t1 = new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 1000; i++) {
          increment();
        }
      }
    });
    Thread t2 = new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 1000; i++) {
          increment();
        }
      }
    });
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("Count is: " + count);
  }
}
