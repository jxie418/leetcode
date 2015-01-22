/**
 * 
 */
package com.jing.xie.thread;

import java.util.Scanner;

/**
 * @author JXie
 *
 */
public class Test {

  /**
   * @param args
   */
  public static void main(String[] args) {
     Counter c = new Counter();
     c.start();
     
     Scanner stopCounter = new Scanner(System.in);
     stopCounter.nextLine();
     c.stopCounting();
  }

}
