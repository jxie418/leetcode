package com.jing.xie.cc;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {

  @Test
  public void testCompress() {
     String s ="aabcccccaaa";
     assertEquals("a2b1c5a3", Solution.compress(s));
  }

}
