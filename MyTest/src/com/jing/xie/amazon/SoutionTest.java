/**
 * 
 */
package com.jing.xie.amazon;

import java.util.List;

import org.junit.Test;

/**
 * @author jxie
 *
 */
public class SoutionTest {

  @Test
  public void test() {
    Solution solution = new Solution();
    solution.itemPurchased("Item1");
    solution.itemPurchased("Item2");
    solution.itemPurchased("Item1");
    solution.itemPurchased("Item1");
    solution.itemPurchased("Item2");
    solution.itemPurchased("Item3");
    List<String> list = solution.getTopTenProducts();
    System.out.println(list.toArray());
  }

}
