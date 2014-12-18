package com.jing.xie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


/**
 * @author JXie
 * 
 */
public class TreeSolution {

  public static void preorder(TreeNode root) {
    if (root == null) {
      return;
    }
    System.out.print(root.val);
    preorder(root.left);
    preorder(root.right);
  }

  public static TreeNode mirror(TreeNode root) {
    if (root == null) {
      return null;
    } else {
      TreeNode tmp = root.left;
      root.left = root.right;
      root.right = tmp;
      mirror(root.left);
      mirror(root.right);

      return root;
    }
  }

  public static TreeNode mirror2(TreeNode root) {
    if (root == null) {
      return null;
    }
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node.left == null && node.right == null) {
        continue;
      } else if (node.left != null && node.right != null) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        queue.offer(node.left);
        queue.offer(node.right);
      } else if (node.left != null && node.right == null) {
        node.right = node.left;
        node.left = null;
        queue.offer(node.right);
      } else if (node.left == null && node.right != null) {
        node.left = node.right;
        node.right = null;
        queue.offer(node.left);
      }
    }
    return root;
  }

  public static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root.left == p || root.right == p || root.left == q || root.right == q) {
      return root;
    }
    TreeNode l = findLCA(root.left, p, q);
    TreeNode r = findLCA(root.right, p, q);
    if (l != null && r != null) {
      return root;
    }
    return l == null ? r : l;
  }

  public static TreeNode fromPreOrderAndInOrder(int[] preOrder, int[] inOrder) {
    return fromPreOrderAndInOrder(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
  }

  public static TreeNode fromPreOrderAndInOrder(
      int[] preOrder,
      int[] inOrder,
      int startpre,
      int endpre,
      int instart,
      int inend) {
    if (startpre > endpre || instart > inend) {
      return null;
    }
    int rootValue = preOrder[startpre];
    TreeNode root = new TreeNode(rootValue);
    int k = 0;
    for (int i = 0; i < inOrder.length; i++) {
      if (inOrder[i] == rootValue) {
        k = i;
        break;
      }
    }
    /**
     * 
     root.left = fromPreOrderAndInOrder(postOrder, inOrder, startpost, startpre + k - (instart+1), instart, k - 1);
     * root.right = fromPreOrderAndInOrder(postOrder, inOrder, startpost + k - instart, endpre, k + 1, inend-1);
     */
    root.left = fromPreOrderAndInOrder(preOrder, inOrder, startpre + 1, startpre + k - instart, instart, k - 1);
    root.right = fromPreOrderAndInOrder(preOrder, inOrder, startpre + k + 1 - instart, endpre, k + 1, inend);

    return root;
  }

  public static List<Integer> findAllPositions(String S, String[] L) {
    List<Integer> res = new ArrayList<Integer>();
    if (S == null || S.length() == 0 || L == null || L.length == 0) {
      return res;
    }
    Map<String, Integer> wordMap = new HashMap<String, Integer>();
    for (String word : L) {
      wordMap.put(word, wordMap.get(word) == null ? 1 : wordMap.get(word) + 1);
    }
    int wordLen = L[0].length();
    int len = S.length();
    for (int i = 0; i < wordLen; i++) {
      int count = 0;
      int start = i;
      Map<String, Integer> numMap = new HashMap<String, Integer>();
      for (int end = start; end < len - wordLen + 1; end += wordLen) {
        String tmpWord = S.substring(end, end + wordLen);
        if (wordMap.containsKey(tmpWord)) {
          numMap.put(tmpWord, numMap.get(tmpWord) == null ? 1 : numMap.get(tmpWord) + 1);
          if (numMap.get(tmpWord) <= wordMap.get(tmpWord)) {
            count++;
          } else {
            while (numMap.get(tmpWord) > wordMap.get(tmpWord)) {
              String str = S.substring(start, start + wordLen);
              if (numMap.containsKey(str)) {
                numMap.put(str, numMap.get(str) - 1);
                if (numMap.get(str) < wordMap.get(str)) {
                  count--;
                }
              }
              start += wordLen;
            }
          }
          while (count == L.length) {
            res.add(start);
            String str = S.substring(start, start + wordLen);
            if (numMap.containsKey(str)) {
              numMap.put(str, numMap.get(str) - 1);
              count--;
            }
            start += wordLen;
          }
        } else {
          numMap.clear();
          count = 0;
          start = end + wordLen;
        }
      }
    }
    return res;
  }

  public static boolean hasTwoNodes2(TreeNode root, int sum) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    boolean done = false;
    while (!done) {
      if (root != null) {
        stack.add(root);
        root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        root = stack.pop();
        if (map.containsKey(root.val)) {
          return true;
        } else {
          map.put(sum - root.val, root.val);
        }
        root = root.right;
      }
    }
    return false;
  }

  public static boolean hasTwoNodes(TreeNode root, int sum) {
    Stack<TreeNode> nextStack = getNextNodeStack(root);
    Stack<TreeNode> preStack = getPreviousNodeStack(root);
    TreeNode next = getNextNode(nextStack);
    TreeNode pre = getPriousNode(preStack);
    while (next != null && pre != null && pre != next) {
      int value = next.val + pre.val;
      if (value == sum) {
        return true;
      }
      if (value < sum) {
        next = getNextNode(nextStack);
      } else {
        pre = getPriousNode(preStack);
      }
    }
    return false;
  }

  static Stack<TreeNode> getNextNodeStack(TreeNode root) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
    return stack;
  }

  static Stack<TreeNode> getPreviousNodeStack(TreeNode root) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while (root != null) {
      stack.push(root);
      root = root.right;
    }
    return stack;
  }

  static TreeNode getNextNode(Stack<TreeNode> stack) {
    TreeNode node = null;
    if (!stack.isEmpty()) {
      node = stack.pop();
      TreeNode right = node.right;
      while (right != null) {
        stack.push(right);
        right = right.right;
      }
    }
    return node;
  }

  static TreeNode getPriousNode(Stack<TreeNode> stack) {
    TreeNode node = null;
    if (!stack.isEmpty()) {
      node = stack.pop();
      TreeNode left = node.left;
      while (left != null) {
        stack.push(left);
        left = left.left;
      }
    }
    return node;
  }

  public static TreeNode getClosestNode(TreeNode root, int value) {
    if (root == null) {
      return null;
    }
    TreeNode p = root;
    int min = Math.abs(root.val - value);
    Stack<TreeNode> stack = new Stack<TreeNode>();
    boolean done = false;
    while (!done) {
      if (root != null) {
        stack.add(root);
        root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        root = stack.pop();
        int diff = Math.abs(root.val - value);
        if (min > diff) {
          min = diff;
          p = root;
        }
        root = root.right;
      }
    }
    return p;
  }

  public TreeNode bstInsert(TreeNode root, int value) {
    if (root == null) {
      return new TreeNode(value);
    }
    if (root.val == value) {
      return root;
    } else if (root.val > value) {
      root.left = bstInsert(root.left, value);
    } else {
      root.right = bstInsert(root.right, value);
    }
    return root;
  }
  public TreeNode delete(TreeNode root, int n) {
    if (root == null) {
      return null;
    }
    if (root.val > n) {
      root.left = delete(root.left, n);
    } else if (root.val < n) {
      root.right = delete(root.right, n);
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
    return root;
  }
  private int min(TreeNode root) {
    if (root.left == null) {
      return root.val;
    } else
      return min(root.left);
  }
  
  public static DoubleLinkedNode getDoubleLinkedListFromTree(TreeNode root) {
    DoubleLinkedNode dummy = new DoubleLinkedNode(0);
    DoubleLinkedNode pre = dummy;
    Stack<TreeNode> stack = new  Stack<TreeNode>();
    boolean done = false;
    while(!done) {
      if(root != null) {
        stack.push(root);
        root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        root = stack.pop();
        DoubleLinkedNode node = new DoubleLinkedNode(root.val);
        pre.next = node;
        node.previous = pre;
        pre = node;
        root = root.right;
      }
    }
    if (dummy.next != null) {
      dummy.next.previous = null;
    }
    return dummy.next;
  }
}
