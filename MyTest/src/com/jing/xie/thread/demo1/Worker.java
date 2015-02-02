/**
 * 
 */
package com.jing.xie.thread.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * @author jxie
 *
 */
public class Worker {
  private Random random = new Random();
  
private List<Integer> list1 = new ArrayList<Integer>();
private List<Integer> list2 = new ArrayList<Integer>();  
public void stageOne() {
  try {
    Thread.sleep(1);
  } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  list1.add(random.nextInt(10));
}
public void stageTwo() {
  try {
    Thread.sleep(1);
  } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  list2.add(random.nextInt(100));
}

public void process() {
  for (int i = 0; i < 1000; i++) {
    stageOne();
    stageTwo();
  }
}
public void main() {
  System.out.println("Starting...");
  long start = System.currentTimeMillis();
  
  long end = System.currentTimeMillis();
  System.out.println("");
}
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
  }

}
