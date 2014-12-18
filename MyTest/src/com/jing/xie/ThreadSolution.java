/**
 * 
 */
package com.jing.xie;

/**
 * @author JXie
 *
 */
public class ThreadSolution implements Runnable{
  static volatile int i = 0, j = 0;
  static void one() {
    i++;
    j++;
  }
  static void two() {
    System.out.println(" i = " + i + " j = " +j);
  }
  @Override
  public void run() {
      one();
      two();
  }
  
  
  public static void main(String[] args) {
    ThreadSolution thread1 = new ThreadSolution();
    ThreadSolution thread2 = new ThreadSolution();
    thread1.run();
    thread2.run();
  }
}
