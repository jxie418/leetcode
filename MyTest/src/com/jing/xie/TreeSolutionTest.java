package com.jing.xie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TreeSolutionTest {

  TreeNode root;

  @Before
  public void setup() {
    root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
  }

  @Test
  public void test() {
    TreeSolution.preorder(root);
    System.out.println();
    TreeSolution.mirror(root);
    TreeSolution.preorder(root);
    System.out.println();
  }
  
  @Test
  public void testIterator() {
    TreeSolution.preorder(root);
    System.out.println();
    TreeSolution.mirror2(root);
    TreeSolution.preorder(root);
  }
  
  @Test
  public void testTreeSum() {
    int [] num = {14,21,24,25,28,31,32,36,42,47};
    TreeNode root = createTree(num);
    assertTrue(TreeSolution.hasTwoNodes2(root, 35));
    assertTrue(TreeSolution.hasTwoNodes2(root, 61));
  }
  
  @Test
  public void testTreeClose() {
    int [] num = {14,21,24,25,28,31,32,36,42,47};
    TreeNode root = createTree(num);
    TreeNode p = TreeSolution.getClosestNode(root, 29);
    assertEquals(28,p.val);
    p = TreeSolution.getClosestNode(root, 33);
    assertEquals(32,p.val);
    p = TreeSolution.getClosestNode(root, 45);
    assertEquals(47,p.val);
  }
  
  @Test
  public void testGetDoubleLinkedListFromTree() {
    int [] num = {14,21,24,25,28,31,32,36,42,47};
    List<Integer> res = new ArrayList<Integer>();
    TreeNode root = createTree(num);
    DoubleLinkedNode head = TreeSolution.getDoubleLinkedListFromTree(root);
    DoubleLinkedNode n = head;
    while(n != null) {
      res.add(n.val);
      n = n.next;
    }
    System.out.println(Arrays.toString(res.toArray()));
  }
  
  @Test
  public void testTreeIterator() {
    int [] num = {14,21,24,25,28,31,32,36,42,47};
    List<Integer> res = new ArrayList<Integer>();
    TreeNode root = createTree(num);
    Iterator it = new BinaryTreeIterator(root);
    while(it.hasNext()) {
      TreeNode node =(TreeNode)it.next();
      res.add(node.val);
    }
    System.out.println(Arrays.toString(res.toArray()));
  }
  TreeNode createTree(int[] num) {
    return createTree(num, 0, num.length -1);
  }
  
  TreeNode createTree(int[] num, int low, int high) {
    if (low > high) {
      return null;
    }
    int mid = (low + high) >>> 1;
    TreeNode root  = new TreeNode(num[mid]);
    root.left = createTree(num, low, mid-1);
    root.right = createTree(num, mid + 1, high);
    return root;
  }
}
