package com.jing.xie.one;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {
  int [] num = {2,4,5,7,9};
  @Test
  public void test() throws Exception {
    Solution s = new Solution();
    String str ="123";
    assertEquals(123, s.stringToLong(str));
  }

  
  @Test
  public void testTree() {
    TreeNode root = createTree(num, 0, num.length -1);
    Solution s = new Solution();
    s.insert(root, 5);
    println(root);
    System.out.println();
    s.insert(root, 2);
    println(root);
    System.out.println();
    s.insert(root, 10);
    println(root);
    System.out.println();
    s.insert(root, 6);
    println(root);
    System.out.println();
    s.insert(root, 8);
    println(root);
    System.out.println();
    s.delete(root, 9);
    println(root);
    System.out.println();
    s.delete(root, 10);
    println(root);
    System.out.println();
    s.delete(root, 5);
    println(root);
    System.out.println();
    s.delete(root, 5);
    println(root);
    System.out.println();
    s.delete(root, 2);
    println(root);
    System.out.println();
    s.delete(root, 4);
    println(root);
    System.out.println();
    s.delete(root, 6);
    println(root);
    System.out.println();
    s.delete(root, 7);
    println(root);
    System.out.println();
    root = s.delete(root, 8);
    println(root);
    System.out.println();
    root = s.delete(root, 2);
    println(root);
    System.out.println();
  }
  
  TreeNode createTree(int[] num, int low, int high) {
    if (low > high) {
      return null;
    }
    int mid = (low + high) >>>1;
    TreeNode root = new TreeNode(num[mid]);
    root.left = createTree(num, low, mid -1);
    root.right = createTree(num, mid + 1, high);
    return root;
  }
  void println(TreeNode root) {
    if (root == null) {
      return ;
    }
    println(root.left);
    TreeNode p = root;
    while(p != null) {
      System.out.print(p.val);
      System.out.print(", ");
      p = p.middle;
    }
    println(root.right);
  }
}
