package com.jing.xie.leetcode.linkedlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jing.xie.ListNode;

/** 
 * 
*/
public class Solution {

  /**
   * Remove Duplicates from Sorted List
   * 
   * @param head
   * @return
   */
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode p = head;
    while (p != null) {
      ListNode pre = p;
      ListNode q = p.next;
      while (q != null) {
        if (pre.val == q.val) {
          pre.next = q.next;
          q.next = null;
          q = pre.next;
        } else {
          pre = q;
          q = q.next;
        }
      }
      p = p.next;
    }
    return head;
  }

  /***
   * Remove Duplicates from Sorted List2
   * 
   * @param head
   * @return
   */
  public ListNode deleteDuplicates2(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode p = head;
    while (p != null) {
      ListNode q = p.next;
      while (q != null && p.val == q.val) {
        q = q.next;
      }
      if (q != p.next) {
        pre.next = q;
        p = q;
      } else {
        pre = p;
        p = p.next;
      }
    }
    return dummy.next;
  }

  /**
   * Partition List
   * 
   * @param head
   * @param x
   * @return
   */
  public ListNode partition(ListNode head, int x) {
    ListNode dummy1 = new ListNode(0);
    ListNode pre1 = dummy1;
    ListNode dummy2 = new ListNode(0);
    ListNode pre2 = dummy2;
    ListNode p = head;
    while (p != null) {
      if (p.val < x) {
        pre1.next = p;
        pre1 = pre1.next;
      } else {
        pre2.next = p;
        pre2 = pre2.next;
      }
      p = p.next;
    }
    pre2.next = null;
    pre1.next = dummy2.next;
    return dummy1.next;
  }

  /**
   * Merge Two Sorted Lists
   * 
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode pre = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        pre.next = l1;
        l1 = l1.next;
      } else {
        pre.next = l2;
        l2 = l2.next;
      }
      pre = pre.next;
    }
    if (l1 != null) {
      pre.next = l1;
    }
    if (l2 != null) {
      pre.next = l2;
    }
    return dummy.next;
  }

  /**
   * Reverse Linked List II
   * 
   * @param head
   * @param m
   * @param n
   * @return
   */
  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode pre = new ListNode(0);
    pre.next = head;
    head = pre;
    ListNode n1 = head;
    int p = m - 1;
    while (p > 0) {
      n1 = n1.next;
      p--;
    }

    pre = n1;
    n1 = n1.next;
    p = n - m;
    while (n1.next != null && p > 0) {
      ListNode temp = n1.next;
      n1.next = temp.next;
      temp.next = pre.next;
      pre.next = temp;
      p--;
    }
    return head.next;
  }

  /**
   * Sort List
   * 
   * @param head
   * @return
   */
  public ListNode sortList(ListNode head) {
    return divide(head);
  }

  public ListNode divide(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode first = head;
    ListNode second = head;
    ListNode pre = head;
    while (second != null && second.next != null) {
      pre = first;
      first = first.next;
      second = second.next.next;
    }
    pre.next = null;
    return mergeTwoLists(divide(head), divide(first));
  }

  /**
   * Reorder List
   * 
   * @param head
   */
  public void reorderList(ListNode head) {
    if (head == null) {
      return;
    }
    ListNode first = head;
    ListNode second = head;
    while (second != null && second.next != null) {
      first = first.next;
      second = second.next.next;
    }
    second = first.next;
    first.next = null;
    second = reverse(second);
    first = head;
    while (second != null) {
      ListNode l1 = first.next;
      ListNode l2 = second.next;
      first.next = second;
      second.next = l1;
      first = l1;
      second = l2;
    }
  }

  ListNode reverse(ListNode head) {
    ListNode newHead = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = newHead;
      newHead = head;
      head = next;
    }
    return newHead;
  }

  /**
   * Linked List Cycle
   * 
   * @param head
   * @return
   */
  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }

  /***
   * Linked List Cycle II
   * 
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        break;
      }
    }
    if (fast == null || fast.next == null) {
      return null;
    }
    fast = head;
    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }
    return fast;
  }

  /***
   * Merge k Sorted Lists
   * 
   * @param lists
   * @return
   */
  public ListNode mergeKLists(List<ListNode> lists) {
    if (lists == null || lists.size() == 0) {
      return null;
    }
    return divide(lists, 0, lists.size() - 1);
  }

  private ListNode divide(List<ListNode> lists, int left, int right) {
    if (left < right) {
      int middle = (left + right) / 2;
      return mergeTwoLists(divide(lists, left, middle), divide(lists, middle + 1, right));
    }
    return lists.get(left);
  }

  /**
   * Copy List with Random Pointer
   * 
   * @param head
   * @return
   */
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null)
      return null;
    Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);

    RandomListNode p = head;
    RandomListNode q = newHead;
    map.put(head, newHead);

    p = p.next;
    while (p != null) {
      RandomListNode temp = new RandomListNode(p.label);
      map.put(p, temp);
      q.next = temp;
      q = temp;
      p = p.next;
    }

    p = head;
    q = newHead;
    while (p != null) {
      if (p.random != null)
        q.random = map.get(p.random);
      else
        q.random = null;

      p = p.next;
      q = q.next;
    }
    return newHead;
  }

  /**
   * Swap pairs and recurse. Swap Nodes in Pairs
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode nextPairs = head.next.next;
    ListNode newHead = head.next;
    head.next.next = head;
    head.next = swapPairs(nextPairs);
    return newHead;
  }

  /**
   * 
   * @param head
   * @param n
   * @return
   */
  public ListNode rotateRight(ListNode head, int n) {
    int len = getLen(head);
    if (len == 0 || len == 1) {
      return head;
    }
    if (n >= len) {
      n = n % len;
    }
    if (n == 0) {
      return head;
    }
    n = len - n;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode p = head;
    while (p != null && n-- > 0) {
      pre = p;
      p = p.next;
    }
    ListNode q = p;
    while (q.next != null) {
      q = q.next;
    }
    pre.next = null;
    q.next = head;
    return p;
  }

  int getLen(ListNode head) {
    int count = 0;
    while (head != null) {
      ++count;
      head = head.next;
    }
    return count;
  }

  /***
   * Reverse Nodes in k-Group
   * 
   * @param head
   * @param k
   * @return
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    int i = 0;
    while (head != null) {
      i++;
      if (i % k == 0) {
        pre = reverse(pre, head.next);
        head = pre.next;
      } else {
        head = head.next;
      }
    }
    return dummy.next;
  }

  /**
   * Reverse Nodes in k-Group
   * 
   * @param pre
   * @param next
   * @return
   */
  public ListNode reverse(ListNode pre, ListNode next) {
    if (pre == null || pre.next == null) {
      return pre;
    }
    ListNode newPre = pre.next;
    ListNode p = pre.next;
    ListNode last = next;
    while (p != next) {
      ListNode N = p.next;
      p.next = last;
      last = p;
      p = N;
    }
    pre.next = last;
    return newPre;
  }

  /**
   * Intersection of Two Linked Lists
   * 
   * @param headA
   * @param headB
   * @return
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int len1 = getLen(headA);
    int len2 = getLen(headB);
    ListNode p = len1 >= len2 ? headA : headB;
    ListNode q = len1 >= len2 ? headB : headA;
    int diff = Math.abs(len1 - len2);
    while (p != null && diff-- > 0) {
      p = p.next;
    }
    while (p != null && q != null) {
      if (p == q) {
        return p;
      }
      p = p.next;
      q = q.next;
    }
    return null;
  }

  /**
   * Insertion Sort List
   * 
   * @param head
   * @return
   */
  public ListNode insertionSortList(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode pre = dummy;
    while (head != null) {
      pre = dummy;
      ListNode p = dummy.next;
      while (p.next != null && p.val < head.val) {
        pre = p;
        p = p.next;
      }
      ListNode n = head.next;
      pre.next = head;
      head.next = p;
      head = n;
    }
    return dummy.next;
  }

  /**
   * Add Two Numbers
   * 
   * @param l1
   * @param l2
   * @return
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode pre = dummy;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int value1 = l1 == null ? 0 : l1.val;
      int value2 = l2 == null ? 0 : l2.val;
      int value = value1 + value2 + carry;
      carry = value / 10;
      pre.next = new ListNode(value % 10);
      pre = pre.next;
      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
    }
    if (carry > 0) {
      pre.next = new ListNode(carry);
    }
    return dummy.next;
  }

  /***
   * Here is the method to find middle in linked list. use tow pointer. one is slow and one is fast.
   */

  public ListNode findMiddle(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  /***
   * Remove Nth Node From End of List
   * 
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n <= 0) {
      return head;
    }
    int len = getLen(head);
    int fromBegin = len - n;

    if (fromBegin == 0) {
      head = head.next;
    } else if (fromBegin > 0) {
      ListNode p = head;
      while (--fromBegin > 0) {
        p = p.next;
      }
      p.next = p.next.next;
    }
    return head;
  }

  /**
   * 
   1.Insert a node into a sorted circular linked list ( all next element is larger except for the last one), the given
   * head can point to any node
   * 
   * 1 -> 3 -> 5 ->7 ^ | | | | _ _ _ |
   * 
   * 如果node的值是2，则插入1和3之间；如果node的值是8或者0，插入7和1之间。 要考虑node值重复的情况，虽然结果一样，但要和面试官讨论新的节点插入的位置，可 能插入在最开始或最后，我不记得了。 例如插入3,
   * 结果是1->3->3'->5->7或者1->3'->3->5->7
   */
  public static void insertNode(ListNode head, ListNode newNode) {
    if (head == null) {
      head = newNode;
      newNode.next = head;
      return;
    }
    ListNode pre = head;
    ListNode p = head.next;
    while (p != head) {
      if (newNode.val > pre.val && p.val > newNode.val) {
        newNode.next = p;
        pre.next = newNode;
      } else if (newNode.val == pre.val || p.val == newNode.val) {
        newNode.next = p;
        pre.next = newNode;
      } else if (p.val < newNode.val) {
        pre = p;
        p = p.next;
      }
    }
  }

}
