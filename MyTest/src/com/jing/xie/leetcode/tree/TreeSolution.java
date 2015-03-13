package com.jing.xie.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.jing.xie.ListNode;
import com.jing.xie.TreeLinkNode;
import com.jing.xie.TreeNode;

public class TreeSolution {

  /**
   * 
    Search Range in a Binary Search Tree
    Binary Tree Serialize and Deserialize
    Binary Tree Upside Down 
   * 
   */
  /**
   * Minimum Depth of Binary Tree
   * @param root
   * @return
   */
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (root.left == null) {
      return right + 1;
    }
    if (root.right == null) {
      return left + 1;
    }
    return Math.min(left, right) + 1;
  }
  /**
   * Maximum Depth of Binary Tree
   * @param root
   * @return
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
  }
  /**
   *Insert a Node in Binary Search Tree

    Search Range in a Binary Search Tree
   */
  public TreeNode insertNode(TreeNode root, int value) {
    if (root == null) {
      return new TreeNode(value);
    } else if (root.val > value) {
       root.left = insertNode(root.left, value);
    } else {
      root.right = insertNode(root.right, value);
    }
    return root;
  }
  
  public int findMin(TreeNode root) {
    if (root.left == null) {
      return root.val;
    } else {
      return findMin(root.left);
    }
  }
  /***
   * Delete a Node in Binary Search Tree
   * @param root
   * @param value
   * @return
   */
  public TreeNode deleteNode(TreeNode root, int value) {
    if (root == null ) {
      return null;
    } else if (root.val > value) {
      root.left = deleteNode(root.left, value);
    } else if (root.val < value) {
      root.right = deleteNode(root.right, value);
    } else {
      if (root.left == null)  {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }
      TreeNode t = root;
      root.val = findMin(t.right);
      root.right = deleteNode(t.right, root.val);
    }
    return root;
  }
  
  /**
   * Populating Next Right Pointers in Each Node
   * @param root
   */
  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    TreeLinkNode p = root.next;
    while (p != null) {
      if (p.left != null) {
        p = p.left;
        break;
      }
      if (p.right != null) {
        p = p.right;
        break;
      }
      p = p.next;
    }
    if (root.right != null) {
      root.right.next = p;
    }
    if (root.left != null) {
      root.left.next = root.right == null ? p : root.right;
    }
    connect(root.right);
    connect(root.left);
  }
  /***
   * Unique Binary Search Trees 
   * @param n
   * @return
   */
  public int numTrees(int n) {
    int [] dp = new int[n + 1];
    dp[0]  = 1;
    dp[1] = 1;
    for (int i = 2; i<= n; i++) {
      for (int j = 0 ; j < i; j++) {
        dp[i] += dp[j] * dp[i-j -1];
      }
    }
    return dp[n];
  }
  /***
   * Unique Binary Search Trees 
   * @param n
   * @return
   */
  public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
  }

  public List<TreeNode> generateTrees(int low, int high) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if (low > high) {
      res.add(null);
      return res;
    }
    for (int i =low; i <= high; i++) {
      List<TreeNode> leftArray = generateTrees(low, i - 1);
      for (TreeNode leftNode : leftArray) {
        List<TreeNode> rightArray = generateTrees(i + 1, high);
        for (TreeNode rightNode : rightArray) {
          TreeNode root = new TreeNode(i);
          root.left = leftNode;
          root.right = rightNode;
          res.add(root);
        }
      }
    }
    return res;
  }
  /***
   * Flatten Binary Tree to Linked List 
   * @param root
   */
  public void flatten(TreeNode root) {
    while (root != null) {
      if (root.left != null) {
        TreeNode p = root.left;
        while (p.right != null) {
          p = p.right;
        }
        p.right = root.right;
        root.right = root.left;
        root.left = null;
      }
      root = root.right;
    }
  }
  /**
   * Validate Binary Search Tree
   */
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean isValidBST(TreeNode root, long min, long max) {
    if (root == null) {
      return false;
    }
    return root.val > min && root.val < max && isValidBST(root.left, min, root.val)
        && isValidBST(root.right, root.val, max);
  }
  
  /**
   * Recover Binary Search Tree
   */
  TreeNode pre, firstNode, secondNode;
  public void recoverTree(TreeNode root) {
    find(root);
    swap(firstNode, secondNode);
  }
  private void find(TreeNode root) {
    if (root == null) {
      return;
    }
    find(root.left);
    if (pre != null && pre.val > root.val) {
        if (firstNode == null) {
          firstNode = pre;
        }
        secondNode = root;
    }
    pre = root;
    find(root.right);
  }
  private void swap(TreeNode first, TreeNode second) {
    int value = first.val;
    first.val = second.val;
    second.val = value;
  }
  /**
   * Convert Sorted Array to Binary Search Tree
   * @param num
   * @return
   */
  public TreeNode sortedArrayToBST(int[] num) {
    return sortedArrayToBST(num, 0, num.length -1);
  }
  
  private TreeNode sortedArrayToBST(int[] num, int low, int high) {
    if (low > high) {
      return null;
    }
    int mid = (low + high) >>> 1;
    TreeNode root = new TreeNode(num[mid]);
    root.left = sortedArrayToBST(num, low, mid -1);
    root.right = sortedArrayToBST(num, mid + 1, high);
    return root;
  }
  
  /***
   * Convert Sorted List to Binary Search Tree
   */
  ListNode head;
  public TreeNode sortedListToBST(ListNode head) {
    this.head = head;
    int len = getLen(head);
    return sortedListToBST(0, len -1);
  }
  TreeNode sortedListToBST(int low, int high) {
    if (low > high) {
      return null;
    }
    int mid = (low + high) >>> 1;
    TreeNode left = sortedListToBST(low, mid -1);
    TreeNode root = new TreeNode(head.val);
    head = head.next;
    TreeNode right = sortedListToBST(mid +1, high);
    root.left = left;
    root.right = right;
    return root;
  }
  
  int getLen(ListNode head) {
    int count = 0 ;
    while(head != null) {
      count++;
      head = head.next;
    }
    return count;
  }
  
  /**
   * Binary Tree Preorder Traversal
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    boolean done = false;
    while(!done) {
      if (root != null) {
         stack.push(root);
         res.add(root.val);
         root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        root = stack.pop();
        root = root.right;
      }
    }
    return res;
  }
  /**
   * Binary Tree Inorder Traversal
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    boolean done = false;
    while(!done) {
      if (root != null) {
         stack.push(root);
         root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        root = stack.pop();
        res.add(root.val);
        root = root.right;
      }
    }
    return res;
  }
  /**
   * Binary Tree PostOrder Traversal
   * @param root
   * @return
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    boolean done = false;
    TreeNode pre = null;
    while(!done) {
      if (root != null) {
         stack.push(root);
         root = root.left;
      } else if (stack.isEmpty()) {
        done = true;
      } else {
        TreeNode p = stack.peek();
        if (p.right != null && p.right != pre) {
          root = p.right;
        } else {
          res.add(p.val);
          stack.pop();
          pre = p;
        }
      }
    }
    return res;
  }
  /**
   * Balanced Binary Tree
   * @param root
   * @return
   */
  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    int left = getDepth(root.left);
    int right = getDepth(root.right);
    return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
  }

  int getDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = getDepth(root.left);
    int right = getDepth(root.right);
    return Math.max(left, right) + 1;
  }
  /***
   * Binary Tree Maximum Path Sum
   * @param root
   * @return
   */
  public int maxPathSum(TreeNode root) {
    int[] max = {Integer.MIN_VALUE};
    findMax(root, max);
    return max[0];
 }
 
 public int findMax(TreeNode root, int[] max) {
      if (root == null) {
       return 0;
     }
     int left = findMax(root.left, max);
     int right = findMax(root.right, max);
     int res = Math.max(root.val, Math.max(root.val+ left, root.val + right));
     max[0] = Math.max(max[0], Math.max(root.val + left + right, res));
     return res;
 }

  /**
   * Same Tree
   * 
   * @param p
   * @param q
   * @return
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null) {
      return q == null;
    }
    if (q == null) {
      return false;
    }
    if (p.val != q.val) {
      return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
  
  /**
   * Symmetric Tree
  **/
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetric(root.left, root.right);
  }

  boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null) {
      return right == null;
    }
    if (right == null) {
      return false;
    }
    if (left.val != right.val) {
      return false;
    }
    return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
  }
  /**
   *Sum Root to Leaf Numbers
   */
  public int sumNumbers(TreeNode root) {
    return sumNumbers(root, 0);
  }

  public int sumNumbers(TreeNode root, int value) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return root.val + value * 10;
    }
    value = value * 10 + root.val;
    return sumNumbers(root.left, value) + sumNumbers(root.right, value);
  }
  /**
   * Path Sum.
   * @param root
   * @param sum
   * @return
   */
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
          return false;
    }
    if (root.val == sum && root.left == null && root.right == null) {
          return true;
    }
    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
  }
  /**
   * Path Sum ii.
   */
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (root == null) {
      return res;
    }
    recurse(res, root, sum, new ArrayList<Integer>());
    return res;
  }

  void recurse(List<List<Integer>> res, TreeNode root, int sum, List<Integer> list) {
    if (root == null) {
      return;
    }
    if (root.val == sum && root.left == null && root.right == null) {
      list.add(root.val);
      res.add(list);
      return;
    }
    List<Integer> leftArray = new ArrayList<Integer>(list);
    leftArray.add(root.val);
    recurse(res, root.left, sum - root.val, leftArray);
    List<Integer> rightArray = new ArrayList<Integer>(list);
    rightArray.add(root.val);
    recurse(res, root.right, sum - root.val, rightArray);
  }
  /**
   * Binary Tree Level Order Traversal 
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    if (root == null) {
      return list;
    }
    List<TreeNode> current = new ArrayList<TreeNode>();
    current.add(root);
    while (!current.isEmpty()) {
      List<TreeNode> nextLay = new ArrayList<TreeNode>();
      List<Integer> values = new ArrayList<Integer>();
      for (TreeNode node : current) {
        values.add(node.val);
        if (node.left != null) {
          nextLay.add(node.left);
        }
        if (node.right != null) {
          nextLay.add(node.right);
        }
      }
      list.add(values);
      current = nextLay;
    }
    return list;
  }
  /**
   * Binary Tree Level Order Traversal II
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    if (root == null) {
      return list;
    }
    List<TreeNode> current = new ArrayList<TreeNode>();
    current.add(root);
    while (!current.isEmpty()) {
      List<TreeNode> nextLay = new ArrayList<TreeNode>();
      List<Integer> values = new ArrayList<Integer>();
      for (TreeNode node : current) {
        values.add(node.val);
        if (node.left != null) {
          nextLay.add(node.left);
        }
        if (node.right != null) {
          nextLay.add(node.right);
        }
      }
      list.add(values);
      current = nextLay;
    }
    Collections.reverse(list);
    return list;
  }
  /**
   * Binary Tree Zigzag Level Order Traversal
   * @param root
   * @return
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    if (root == null) {
      return list;
    }
    boolean leftToRigh = true;
    List<TreeNode> current = new ArrayList<TreeNode>();
    current.add(root);
    while (!current.isEmpty()) {
      List<TreeNode> nextLay = new ArrayList<TreeNode>();
      List<Integer> values = new ArrayList<Integer>();
      for (TreeNode node : current) {
        values.add(node.val);
        if (node.left != null) {
          nextLay.add(node.left);
        }
        if (node.right != null) {
          nextLay.add(node.right);
        }
      }
      if (leftToRigh) {
        list.add(values);
      } else {
        Collections.reverse(values);
        list.add(values);
      }
      leftToRigh = !leftToRigh;
      current = nextLay;
    }
    
    return list;
  }
  /**
   * Construct Binary Tree from Preorder and Inorder
   * @param preorder
   * @param inorder
   * @return
   */
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, 0, preorder.length -1, inorder, 0, inorder.length -1);
  }
  
  public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int k = 0;
    for (int i = inStart; i <= inEnd; i++) {
      if (inorder[i] == preorder[preStart]) {
        k = i;
        break;
      }
    }
    root.left = buildTree(preorder, preStart + 1, preStart + k - inStart, inorder, inStart, k - 1);
    root.right = buildTree(preorder, preStart + k + 1 - inStart, preEnd, inorder, k + 1, inEnd);
    return root;
  }
  /**
   * Construct Binary Tree from Inorder and Postorder
   * @param inorder
   * @param postorder
   * @return
   */
  public TreeNode buildTree2(int[] inorder, int[] postorder) {
    return buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  public TreeNode buildTree2(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
    if (postStart > postEnd || inStart > inEnd) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[postEnd]);
    int k = 0;
    for (int i = inStart; i <= inEnd; i++) {
      if (inorder[i] == postorder[postEnd]) {
        k = i;
        break;
      }
    }
    root.left = buildTree(inorder, inStart, k - 1, postorder, postStart, postStart + k - inStart - 1);
    root.right = buildTree(inorder, k + 1, inEnd, postorder, postStart + k - inStart, postEnd - 1);
    return root;
  }
  
  public int findSuccessor(TreeNode root, TreeNode node) {
    if (node.right != null) {
      return findMin(node.right);
    }
    int v = root.val;
    while(root != null) {
      if (node.val < root.val) {
        v = root.val;
        root = root.left;
      } else if (node.val > root.val) {
        v = root.val;
        root = root.right;
      } else {
        break;
      }
    }
    return v;
  }
  /**
   * 1：print all path from root to leaf
   * @param root
   * @return
   */
  public List<List<Integer>> getAllThePath(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>> ();
    helperAllPath(res, new ArrayList<Integer>(), root);
    return res;
  }
  
  public void helperAllPath(List<List<Integer>> res, List<Integer> list, TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      list.add(root.val);
      res.add(list);
      return;
    }
    List<Integer> leftList = new ArrayList<Integer>(list);
    leftList.add(root.val);
    helperAllPath(res, leftList, root.left);
    List<Integer> rightList = new ArrayList<Integer>(list);
    rightList.add(root.val);
    helperAllPath(res, rightList, root.right);
  }
}
