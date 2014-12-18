package com.jing.xie.one;

public class Solution {
  /**
   * This is the method to convert String to long integer.
   * 
   * @param s
   *          Input String.
   * @return It is the long value of the String.
   * @throws Exception
   *           If the input is invalid.
   */
  public long stringToLong(String s) throws Exception {
    if (!isValidString(s)) {
      throw new Exception("Invalid Input String!");
    }
    s = s.trim();
    double value = 0;
    boolean flag = true;
    if (s.charAt(0) == '+') {
      flag = true;
      s = s.substring(1);
    } else if (s.charAt(0) == '-') {
      flag = false;
      s = s.substring(1);
    }
    for (int i = 0; i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'; i++) {
      value = value * 10 + s.charAt(i) - '0';
    }
    if (!flag) {
      value = -value;
    }
    if (value > Long.MAX_VALUE) {
      return Long.MAX_VALUE;
    }
    if (value < Long.MIN_VALUE) {
      return Long.MIN_VALUE;
    }
    return (long) value;
  }

  // To Filter Valid input string
  private boolean isValidString(String s) {
    if (s == null || s.trim().length() == 0) {
      return false;
    }
    String regex = "[-+]?(\\d+)";
    return s.trim().matches(regex);
  }

  /**
   * Insert node into the Tree.
   * 
   * @param root
   * @param n
   * @return
   */
  public TreeNode insert(TreeNode root, int n) {
    if (root == null) {
      return new TreeNode(n);
    }
    if (root.val > n) {
      root.left = insert(root.left, n);
    } else if (root.val < n) {
      root.right = insert(root.right, n);
    } else {
      TreeNode node = new TreeNode(n);
      node.middle = root.middle;
      root.middle = node;
    }
    return root;
  }

  /**
   * Delete the node from the TreeNode.
   * 
   * @param root
   * @param n
   * @return
   */
  public TreeNode delete(TreeNode root, int n) {
    if (root == null) {
      return null;
    }
    if (root.val > n) {
      root.left = delete(root.left, n);
    } else if (root.val < n) {
      root.right = delete(root.right, n);
    } else {
      TreeNode p = root.middle;
      if (p != null) {
        root.middle = p.middle;
      } else {
        if (root.right == null) {
          return root.left;
        }
        if (root.left == null) {
          return root.right;
        }
        TreeNode t = root;
        root.val = min(t.right);
        root.right = delete(t.right, root.val);
      }
    }
    return root;
  }

  // Find minimum value from the tree.
  private int min(TreeNode root) {
    if (root.left == null) {
      return root.val;
    } else
      return min(root.left);
  }
}

// Tree Node definition.
class TreeNode {
  int val;
  TreeNode left, right, middle;

  TreeNode(int i) {
    this.val = i;
    left = null;
    right = null;
    middle = null;
  }
}
