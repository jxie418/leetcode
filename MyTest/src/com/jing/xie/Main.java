package com.jing.xie;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<Object>  list;

    try{
      System.out.println("1");
      System.exit(0);
    }catch(Exception ex) {
      System.out.print("3");
      return;
    } finally{
      System.out.println("2");
    }
  }
}
