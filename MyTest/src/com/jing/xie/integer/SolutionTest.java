package com.jing.xie.integer;

import static org.junit.Assert.*;
import static com.jing.xie.integer.Solution.*;

import org.junit.Test;

public class SolutionTest {

  @Test
  public void test() {
    assertEquals(10, Solution.add2(3, 7));
  }
  @Test
  public void testMax() {
    assertEquals(7, findMax(findMax(3, 7),4));
    assertEquals(3, Solution.findMax(3, -7));
  }
  
  @Test
  public void testMin() {
   
  }
}
