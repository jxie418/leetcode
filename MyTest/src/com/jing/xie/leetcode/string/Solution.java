package com.jing.xie.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Solution {
  /**
   *   
    0.16  
6 ) 1.00
    0 
    1 0       <-- Remainder=1, mark 1 as seen at position=0.
    - 6 
      40      <-- Remainder=4, mark 4 as seen at position=1.
    - 36 
       4      <-- Remainder=4 was seen before at position=1, so the fractional part which is 16 starts repeating at position=1 => 1(6).
The key insight here is to notice that once the remainder starts repeating, so does the divided result.
You will need a hash table that maps from the remainder to its position of the fractional part. Once you found a repeating remainder, you may enclose the reoccurring fractional part with parentheses by consulting the position from the table.
The remainder could be zero while doing the division. That means there is no repeating fractional part and you should stop right away.
Just like the question Divide Two Integers, be wary of edge case such as negative fractions and nasty extreme case such as -2147483648 / -1.
   */
  public static String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    if (denominator == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    if ((numerator < 0) ^ (denominator < 0)) {
      sb.append("-");
    }

    long num = numerator, den = denominator;
    num = Math.abs(num);
    den = Math.abs(den);
    long res = num / den;
    sb.append(res);
    long rem = (num % den) * 10;
    if (rem == 0)
      return sb.toString();
    Map<Long, Integer> map = new HashMap<Long, Integer>();
    sb.append(".");
    while (rem != 0) {
      if (map.containsKey(rem)) {
        int beg = map.get(rem);
        String part1 = sb.substring(0, beg);
        String part2 = sb.substring(beg);
        return part1 + "(" + part2 + ")";
      }
      map.put(rem, sb.length());
      res = rem / den;
      sb.append(res);
      rem = (rem % den) * 10;
    }
    return sb.toString();
  }
}
