package com.jing.xie.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

  private static final int NTHREDS = 10;
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
    for (int i = 0; i < 500; i++) {
      Runnable worker = new MyRunnable(100000L +i);
      worker.run();
      executor.equals(worker);
    }
    executor.shutdown();
    System.out.print("Finished all threads");
  }
}
