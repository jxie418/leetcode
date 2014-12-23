package com.jing.xie.leetcode.tree;

import java.util.Iterator;
import java.util.Stack;

import com.jing.xie.TreeNode;



/**
 * @author JXie
 * Implement iterator of Binary Search Tree
 */
public class BinaryTreeIterator implements Iterator {
  private Stack<TreeNode> stack = new Stack<TreeNode>();
  private TreeNode root;
  public BinaryTreeIterator(TreeNode root) {
    this.root = root;
    stack = new Stack<TreeNode>();
  }
  @Override
  public boolean hasNext() {
    return !stack.isEmpty() || root != null;
  }

  @Override
  public TreeNode next() {
    while(root != null) {
      stack.push(root);
      root = root.left;
    }
    if (stack.isEmpty()) {
      return null;
    }
    TreeNode res = stack.pop();
    root = res.right;
    return res;
  }

  @Override
  public void remove() {
  }
}
