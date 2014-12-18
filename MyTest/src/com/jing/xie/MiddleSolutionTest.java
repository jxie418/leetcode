package com.jing.xie;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MiddleSolutionTest {

  @Test
  public void testGetMiddleValue() {
    Random random = new Random();
    MiddleSolution solution = new MiddleSolution();
    int [] num = new int[11];
    for (int i = 0 ; i < num.length ; i++) {
      int nextInt = random.nextInt(10);
      solution.insertValue(nextInt);
      num[i] = nextInt;
    }
    Arrays.sort(num);
    System.out.println(Arrays.toString(num));
    int mid = num.length >>>1;
    System.out.println(solution.getMiddleValue());
    assertEquals(num[mid], solution.getMiddleValue());
  }

}
