package com.jing.xie.cc;

import java.util.LinkedList;

import com.jing.xie.ListNode;

public class Solution {
  public static String compress(String str) {
    StringBuilder sb = new StringBuilder();
    char last = str.charAt(0);
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      if (last == str.charAt(i)) {
        count ++;
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
    if (head == null || head.next == null ) {
      return;
    }
    ListNode p = head;
    while(p != null) {
      ListNode pre = p;
      ListNode n = p.next;
      while(n != null) {
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
    if (k <= 0) return null;
    ListNode p1 = head;
    ListNode p2 = head;
    for (int i = 0 ; i < k - 1; i++) {
      if (p2 == null) return null;
      p2 = p2.next;
    }
    if (p2 == null) return null;
    while(p2.next != null) {
      p1 = p1.next;
      p2 = p2.next;
    }
    return p1;
  }
}
