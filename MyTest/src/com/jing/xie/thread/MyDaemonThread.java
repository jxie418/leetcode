package com.jing.xie.thread;

public class MyDaemonThread {

  private static class DaemonThread extends Thread {

    public DaemonThread() {
      setDaemon(true);
    }

    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new DaemonThread();
    thread.start();
    thread.join();
    System.out.println(thread.isAlive());
  }
}
