package com.jing.xie.cc;

import com.jing.xie.ListNode;

public class Solution {
  public static String compress(String str) {
    StringBuilder sb = new StringBuilder();
    char last = str.charAt(0);
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      if (last == str.charAt(i)) {
        count++;
      } else {
        sb.append(last).append(count);
        last = str.charAt(i);
        count = 1;
      }
    }
    sb.append(last).append(count);
    return sb.toString();
  }

  public static void deleteDups(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    ListNode p = head;
    while (p != null) {
      ListNode pre = p;
      ListNode n = p.next;
      while (n != null) {
        if (n.val == p.val) {
          pre.next = n.next;
          n = n.next;
        } else {
          pre = n;
          n = n.next;
        }
      }
      p = p.next;
    }
  }

  public static ListNode nthToLast(ListNode head, int k) {
    if (k <= 0)
      return null;
    ListNode p1 = head;
    ListNode p2 = head;
    for (int i = 0; i < k - 1; i++) {
      if (p2 == null)
        return null;
      p2 = p2.next;
    }
    if (p2 == null)
      return null;
    while (p2.next != null) {
      p1 = p1.next;
      p2 = p2.next;
    }
    return p1;
  }

  public int search(int a[], int x) {
    int left = 0;
    int right = a.length - 1;
    while (left <= right) {
      int mid = (left + right) >>> 1;
      if (a[mid] == x) {
        return mid;
      } else if (a[mid] > a[left]) {
        if (x >= a[left] && x <= a[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else if (a[mid] < a[left]) {
        if (x >= a[mid] && x <= a[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      } else {
        left++;
      }
    }
    return -1;
  }

  public static boolean findElement(int[][] matrix, int elem) {
    int row = 0;
    int col = matrix[0].length - 1;
    while (row < matrix.length && col >= 0) {
      if (matrix[row][col] == elem) {
        return true;
      } else if (matrix[row][col] > elem) {
        col--;
      } else {
        row++;
      }
    }
    return false;
  }
}

class Question {
  private static RankNode root = null;

  public static void track(int number) {
    if (root == null) {
      root = new RankNode(number);
    } else {
      root.insert(number);
    }
  }

  public static int getRankOfNumber(int number) {
    return root.getRank(number);
  }
}

class RankNode {
  public int left_size = 0;
  public RankNode left, right;
  public int data = 0;

  public RankNode(int d) {
    data = d;
  }

  public void insert(int d) {
    if (d <= data) {
      if (left != null)
        left.insert(d);
      else
        left = new RankNode(d);
      left_size++;
    } else {
      if (right != null)
        right.insert(d);
      else
        right = new RankNode(d);
    }
  }

  public int getRank(int d) {
    if (d == data) {
      return left_size;
    } else if (d < data) {
      if (left == null)
        return -1;
      else
        return left.getRank(d);
    } else {
      int right_rank = right == null ? -1 : right.getRank(d);
      if (right_rank == -1)
        return -1;
      else
        return left_size + 1 + right_rank;
    }
  }
}
