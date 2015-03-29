package com.jing.xie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author JXie
 * 
 */
public class Solution {

  private static PriorityQueue<Integer> queue;
  private static TreeMap<Integer, String> map;

  public static int kthLargestElement(int k, ArrayList<Integer> numbers) {
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {
        return i1.compareTo(i2);
      }
    });
    for (int i = 0; i < numbers.size(); i++) {
      if (queue.size() < k) {
        queue.add(numbers.get(i));
      } else {
        Integer p = queue.peek();
        if (numbers.get(i) > p) {
          queue.poll();
          queue.add(numbers.get(i));
        }
      }
    }
    return queue.isEmpty() ? 0 : queue.peek().intValue();
  }

  public static void addDataTo() {
    queue = new PriorityQueue<Integer>();
    queue.offer(1);
    queue.offer(3);
    queue.offer(8);
    queue.offer(5);
    queue.offer(2);
    queue.offer(9);
    while (!queue.isEmpty()) {
      System.out.println(queue.poll());
    }

    /*map = new TreeMap<Integer, String>();
    map.put(1, "K");
    map.put(3, "A");
    map.put(4, "D");
    map.put(8, "B");
    map.put(10, "C");
    
    while (!map.isEmpty()) {
        Entry<Integer, String> pollFirstEntry = map.pollFirstEntry();
        System.out.println(pollFirstEntry.getKey()+":" + pollFirstEntry.getValue());
        
    }
    Set<Integer> keys = map.descendingKeySet();
    Iterator<Integer> it = keys.iterator();
    while (it.hasNext()) {
      Integer next = it.next();
      String value = map.get(next);
      System.out.println(next + ":" + value);
    }*/
  }

  public static void buble(int[] num) {
    if (num == null || num.length == 0) {
      return;
    }
    for (int i = 0; i < num.length - 1; i++) {
      for (int j = 0; j < num.length - i - 1; j++) {
        if (num[j] > num[j + 1]) {
          swap(num, j, j + 1);
        }
      }
    }
  }

  static int[] copy;

  public static void merge(int[] num) {
    if (num == null || num.length == 0) {
      return;
    }
    copy = new int[num.length];
    int low = 0;
    int high = num.length - 1;
    devide(num, low, high);
  }

  public static void devide(int[] num, int low, int high) {
    if (num == null || num.length == 0) {
      return;
    }
    if (low >= high) {
      return;
    }
    int mid = (low + high) >>> 1;
    devide(num, low, mid);
    devide(num, mid + 1, high);
    merge(num, low, mid, high);
  }

  public static void merge(int[] num, int start1, int mid, int end2) {
    if (num == null || num.length == 0) {
      return;
    }
    for (int i = start1; i <= end2; i++) {
      copy[i] = num[i];
    }
    int k = start1;
    int start2 = mid + 1;
    while (start1 <= mid && start2 <= end2) {
      if (copy[start1] < copy[start2]) {
        num[k++] = copy[start1++];
      } else {
        num[k++] = copy[start2++];
      }
    }
    while (start1 <= mid) {
      num[k++] = copy[start1++];
    }
    while (start2 <= end2) {
      num[k++] = copy[start2++];
    }
  }

  public static void bubleSort(int[] num) {
    for (int i = 0; i < num.length - 1; i++) {
      for (int j = 0; j < num.length - i - 1; j++) {
        if (num[j] > num[j + 1]) {
          swap(num, j, j + 1);
        }
      }
    }
  }

  public static void QuickSort(int[] num, int low, int high) {
    int i = low, j = high;
    int mid = (low + high) >>> 1;
    while (i < j) {
      while (num[i] < num[mid]) {
        i++;
      }
      while (num[j] > num[mid]) {
        j--;
      }
      if (i <= j) {
        swap(num, i, j);
        i++;
        j--;
      }

      if (low < j) {
        QuickSort(num, low, j);
      }
      if (i < high) {
        QuickSort(num, i, high);
      }
    }
  }

  public static void quick(int[] num) {
    int low = 0;
    int high = num.length - 1;
    QuickSort(num, low, high);
  }

  public static void quick(int[] num, int low, int high) {
    if (low >= high) {
      return;
    }
    int mid = (low + high) >>> 1;
    int i = low;
    int j = high;
    while (i < j) {
      while (num[i] < num[mid]) {
        i++;
      }
      while (num[j] > num[mid]) {
        j--;
      }
      if (i <= j) {
        swap(num, i, j);
        i++;
        j--;
      }
    }
    if (low < j) {
      quick(num, low, j);
    }
    if (i < high) {
      quick(num, i, high);
    }
  }

  public static void bucketSort(int[] num) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i : num) {
      min = Math.min(min, i);
      max = Math.max(max, i);
    }
    int[] counter = new int[max - min + 1];
    for (int i : num) {
      counter[i - min]++;
    }
    int k = 0;
    for (int i = 0; i < counter.length; i++) {
      for (int j = 0; j < counter[i]; j++) {
        num[k] = i + min;
        k++;
      }
    }
  }

  public static void bucket(int[] num) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int n : num) {
      min = Math.min(min, n);
      max = Math.max(max, n);
    }
    int[] counter = new int[max - min + 1];
    for (int n : num) {
      counter[n - min]++;
    }
    int i = 0;
    for (int j = 0; j < counter.length; j++) {
      for (int k = 0; k < counter[j]; k++) {
        num[i] = j + min;
        i++;
      }
    }
  }

  private static void swap(int[] num, int index1, int index2) {
    int tmp = num[index1];
    num[index1] = num[index2];
    num[index2] = tmp;
  }

  public static void insertSort(int[] num) {
    for (int i = 1; i < num.length; i++) {
      int v = num[i];
      int j = i - 1;
      while (j >= 0 && num[j] > v) {
        num[j + 1] = num[j];
        j--;
      }
      num[j + 1] = v;
    }
  }

  public static void radixSort(int[] num) {
    int i, m = num[0], exp = 1, n = num.length;
    int[] b = new int[10];
    for (i = 1; i < n; i++)
      if (num[i] > m)
        m = num[i];
    while (m / exp > 0) {
      int[] bucket = new int[10];
      for (i = 0; i < n; i++)
        bucket[(num[i] / exp) % 10]++;
      for (i = 1; i < 10; i++)
        bucket[i] += bucket[i - 1];
      for (i = n - 1; i >= 0; i--)
        b[--bucket[(num[i] / exp) % 10]] = num[i];
      for (i = 0; i < n; i++)
        num[i] = b[i];
      exp *= 10;
    }
  }

  public static void selectSort(int[] num) {
    for (int i = 0; i < num.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < num.length; j++) {
        if (num[j] < num[min]) {
          min = j;
        }
      }
      if (min != i) {
        swap(num, i, min);
      }
    }
  }

  public static String getPath(int[] num, int n) {
    if (num == null || num.length == 0) {
      return "";
    }
    if (num[0] == n) {
      return "";
    }
    int index = getIndex(num, n);
    if (index == -1) {
      return "";
    }
    int root = num[0];
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < index; i++) {
      if (root > n) {
        if (num[i] > root) {
          continue;
        } else {
          if (num[i] > n) {
            sb.append("L");
          } else {
            sb.append("R");
          }
        }
      } else {
        if (num[i] < root) {
          continue;
        } else {
          if (num[i] > n) {
            sb.append("L");
          } else {
            sb.append("R");
          }
        }
      }
    }
    return sb.toString();
  }

  public static int getIndex(int[] num, int n) {

    for (int i = 0; i < num.length; i++) {
      if (num[i] == n) {
        return i;
      }
    }
    return -1;
  }

  public static int addTwo(int a, int b) {
    if (b == 0) {
      return a;
    }
    int value = a ^ b;
    int carry = (a & b) << 1;
    return addTwo(value, carry);
  }

  public static double pow(double a, int b) {
    if (b < 0) {
      a = 1 / a;
      b = -b;
    }
    if (b == 0) {
      return 1;
    }
    if (b == 1) {
      return a;
    }
    double value = pow(a, b / 2);
    return b % 2 == 0 ? value * value : value * value * a;
  }

  public static boolean findValue(int[][] table, int value) {
    int row = table.length - 1;
    int col = table[0].length - 1;
    int low = 0;
    int high = row * col - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int r = mid / table[0].length;
      int c = mid % table[0].length;
      if (table[r][c] == value) {
        return true;
      } else if (table[r][c] > value) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }

  public static List<Integer> unionArrays(int[] m, int[] n) {
    List<Integer> list = new ArrayList<Integer>();
    if (m == null && n == null) {
      return list;
    }
    if (m == null) {
      for (int i : n) {
        list.add(i);
      }
      return list;
    }
    if (n == null) {
      for (int i : m) {
        list.add(i);
      }
      return list;
    }
    int i = 0, j = 0;
    while (i < m.length && j < n.length) {
      if (m[i] > n[j]) {
        list.add(n[j]);
        j++;
      } else if (m[i] < n[j]) {
        list.add(m[i]);
        i++;
      } else {
        list.add(m[i]);
        i++;
        j++;
      }
    }
    while (i < m.length) {
      list.add(m[i]);
      i++;
    }
    while (j < n.length) {
      list.add(n[j]);
      j++;
    }
    return list;
  }

  public static List<Integer> getIntersection(int[] m, int[] n) {
    List<Integer> list = new ArrayList<Integer>();
    if (m == null || n == null || m.length == 0 || n.length == 0) {
      return list;
    }
    int i = 0, j = 0;
    while (i < m.length && j < n.length) {
      if (m[i] > n[j]) {
        j++;
      } else if (m[i] < n[j]) {
        i++;
      } else {
        list.add(m[i]);
        while (i + 1 < m.length && m[i] == m[i + 1])
          i++;
        while (j + 1 < n.length && n[j] == n[j + 1])
          j++;
        i++;
        j++;
      }
    }
    return list;
  }

  public static int lineAccess(int[] num, int t) {
    int count = 0;
    for (int i = 0; i < num.length; i++) {
      if (num[i] == t) {
        count++;
      }
    }
    return count;
  }

  public static int leftIndex(int[] num, int t, int low, int high) {
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (num[mid] == t) {
        return leftIndex(num, t, low, mid - 1);
      } else if (num[mid] > t) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  public static int rightIndex(int[] num, int t, int low, int high) {
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (num[mid] == t) {
        return rightIndex(num, t, mid + 1, high);
      } else if (num[mid] > t) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

  public static int findMin(int[] num) {
    return findMin(num, 0, num.length - 1);
  }

  public static int findMin(int[] num, int low, int high) {
    if (low > high)
      return num[0];
    if (low == high)
      return num[low];
    int mid = (low + high) >>> 1;
    if (mid < high && num[mid] > num[mid + 1])
      return num[mid + 1];
    if (mid > low && num[mid] < num[mid - 1])
      return num[mid];
    if (num[high] > num[mid])
      return findMin(num, low, mid - 1);
    return findMin(num, mid + 1, high);
  }

  public static int findMin2(int[] num) {
    if (num == null || num.length == 0) {
      return 0;
    }
    int low = 0;
    int high = num.length - 1;
    int min = num[0];
    while (low < high - 1) {
      int mid = (low + high) >>> 1;
      if (num[low] > num[mid]) {
        min = Math.min(min, num[mid]);
        high = mid - 1;
      } else if (num[low] < num[mid]) {
        min = Math.min(min, num[low]);
        low = mid + 1;
      } else {
        low++;
      }
    }
    min = Math.min(min, num[low]);
    min = Math.min(min, num[high]);
    return min;
  }

  // Binary Search if it can not find the value it will find the first element greater than key.
  public static int insertPosition(int[] a, int key) {
    int low = 0;
    int high = a.length - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = a[mid];

      if (midVal < key)
        low = mid + 1;
      else if (midVal > key)
        high = mid - 1;
      else
        return mid;
    }
    return low;
  }

  public static List<List<Integer>> subSet(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    return res;
  }

  public static void recurseSubSet() {

  }

  public static List<List<Integer>> combination(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    boolean[] dp = new boolean[num.length];
    recurse(res, num, new ArrayList<Integer>(), dp);
    return res;
  }

  public static void recurse(List<List<Integer>> res, int[] num, List<Integer> list, boolean[] dp) {
    if (num.length == list.size()) {
      res.add(list);
    } else if (list.size() < num.length) {
      for (int i = 0; i < num.length; i++) {
        if (dp[i])
          continue;
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.addAll(list);
        tmp.add(num[i]);
        dp[i] = true;
        recurse(res, num, tmp, dp);
        dp[i] = false;
      }
    }
  }

  public static void println(List<List<Integer>> res) {
    for (List<Integer> list : res) {
      System.out.println(Arrays.toString(list.toArray()));
    }
  }

  //
  public static void moveZeroToEnd(int[] num) {
    if (num == null || num.length == 0) {
      return;
    }
    int end = num.length - 1;
    int i = 0;
    while (i < end) {
      while (num[i] != 0) {
        i++;
      }
      while (num[end] == 0) {
        end--;
      }
      if (i < end) {
        num[i] = num[end];
        num[end] = 0;
      }
      i++;
      end--;
    }
  }

  public static List<String> wordBreak(String s, Set<String> dict) {
    List<String> res = new ArrayList<String>();
    if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
      return res;
    }
    recurse(res, s, "", dict);
    return res;
  }

  static void recurse(List<String> res, String s, String words, Set<String> dict) {
    if (s.length() == 0) {
      res.add(words);
    } else {
      for (int i = 1; i <= s.length(); i++) {
        String first = s.substring(0, i);
        String second = s.substring(i);
        if (dict.contains(first)) {
          String newWords = "";
          if (words.length() == 0) {
            newWords = first;
          } else {
            newWords = words + " " + first;
          }
          dict.remove(first);
          recurse(res, second, newWords, dict);
          dict.add(first);
        }
      }
    }
  }

  public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
    List<List<String>> res = new ArrayList<List<String>>();
    if (start == null || end == null || dict == null || dict.size() == 0) {
      return res;
    }
    Set<String> set = new HashSet<String>();
    set.add(start);
    recurse(res, start, end, dict, set);
    return res;
  }

  public static List<List<String>> findLadders2(String start, String end, Set<String> dict) {
    List<List<String>> res = new ArrayList<List<String>>();
    if (start == null || end == null || dict == null || dict.size() == 0) {
      return res;
    }
    Set<String> set = new HashSet<String>();
    set.add(start);
    Queue<String> queue = new LinkedList<String>();
    queue.add(start);
    while (!queue.isEmpty()) {
      String word = queue.poll();
      if (word.equals(end)) {
        set.add(end);
        res.add(new ArrayList<String>(set));
      }
      for (int i = 0; i < word.length(); i++) {
        char[] chars = word.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          if (c != chars[i]) {
            chars[i] = c;
            String newWord = new String(chars);
            if (dict.contains(newWord)) {
              set.add(newWord);
              queue.offer(newWord);
              dict.remove(newWord);
            }
          }
        }
      }
    }
    return res;
  }

  static void recurse(List<List<String>> res, String start, String end, Set<String> dict, Set<String> set) {
    if (start.equals(end)) {
      set.add(end);
      res.add(new ArrayList<String>(set));
    } else {
      for (int i = 0; i < start.length(); i++) {
        char[] chars = start.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          if (c != chars[i]) {
            chars[i] = c;
            String newWord = new String(chars);
            if (dict.contains(newWord)) {
              Set<String> tmp = new HashSet<String>();
              tmp.addAll(set);
              tmp.add(newWord);
              dict.remove(newWord);
              recurse(res, newWord, end, dict, tmp);
              dict.add(newWord);
            }
          }
        }
      }
    }
  }

  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    List<TreeLinkNode> currentLay = new ArrayList<TreeLinkNode>();
    currentLay.add(root);
    while (!currentLay.isEmpty()) {
      List<TreeLinkNode> nextLay = new ArrayList<TreeLinkNode>();
      for (int i = 0; i < currentLay.size() - 1; i++) {
        currentLay.get(i).next = currentLay.get(i + 1);
        setNextLay(currentLay, nextLay, i);
      }
      currentLay.get(currentLay.size() - 1).next = null;
      setNextLay(currentLay, nextLay, currentLay.size() - 1);
      currentLay = nextLay;
    }
  }

  private void setNextLay(List<TreeLinkNode> currentLay, List<TreeLinkNode> nextLay, int i) {
    if (currentLay.get(i).left != null) {
      nextLay.add(currentLay.get(i).left);
    }
    if (currentLay.get(i).right != null) {
      nextLay.add(currentLay.get(i).right);
    }
  }

  public static boolean isUniqueChars(String str) {
    int checker = 0;
    for (int i = 0; i < str.length(); ++i) {
      int val = str.charAt(i) - 'a';
      if ((checker & (1 << val)) > 0)
        return false;
      checker |= (1 << val);
    }
    return true;
  }

  public static char firstNotRepeatChar(String str) {
    int checker = 0;
    for (int i = 0; i < str.length(); ++i) {
      int val = str.charAt(i) - 'a';
      if ((checker & (1 << val)) > 0)
        return str.charAt(i);
      checker |= (1 << val);
    }
    return '\0';
  }

  public static int indexOf(String p, String t) {
    if (p == null || t == null) {
      return -1;
    }
    int len1 = p.length();
    int len2 = t.length();
    for (int i = 0; i <= len1 - len2; i++) {
      int j = 0;
      for (; j < len2; j++) {
        if (t.charAt(j) != p.charAt(i + j)) {
          break;
        }
      }
      if (j == len2) {
        return i;
      }
    }
    return -1;
  }

  public static void intersection() {
    Set<Integer> set = new HashSet<Integer>();
    System.out.println(set.add(1));
    System.out.println(set.add(1));
  }

  public static int findFirstMissingInteger(int[] num) {
    if (num == null || num.length == 0) {
      return -1;
    }
    int left = 0;
    int right = num.length - 1;
    while (left <= right) {
      int middle = (right + left) >>> 1;
      if (num[middle] != middle) {
        if (middle == 0 || num[middle - 1] == middle - 1)
          return middle;
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    return -1;
  }

  public static boolean isOneEditDistance(String s1, String s2) {
    if (s1 == null) {
      return s2 != null && s2.length() == 1;
    }
    if (s2 == null) {
      return s1 != null && s1.length() == 1;
    }
    if (s1.equals(s2)) {
      return false;
    }
    int len1 = s1.length();
    int len2 = s2.length();
    if (len1 < len2) {
      return isOneEditDistance(s2, s1);
    }
    int offset = len1 - len2;
    if (offset > 1) {
      return false;
    }
    int i = 0;
    while (i < len2 && s1.charAt(i) == s2.charAt(i)) {
      i++;
    }
    if (offset == 0) {
      i++;
    }
    while (i < len2 && s1.charAt(i + offset) == s2.charAt(i)) {
      i++;
    }
    return i == len2;
  }

  public static String getShortestString(String S) {
    int len = S.length() - 1;
    String tmp = subString(S, len - 1);
    String first = S.substring(0, S.length() - tmp.length());
    String end = new StringBuilder(first).reverse().toString();
    return first + tmp + end;
  }

  public static String subString(String S, int i) {
    while (i >= 0 && S.charAt(i) == S.charAt(i + 1)) {
      i--;
    }
    return S.substring(i + 1, S.length());
  }

  static int getLen(int residule, int b) {
    int count = 0;
    while (residule / b == 0) {
      count++;
      residule *= 10;
    }
    return count;
  }

  public static String caldec(int a, int b) {
    StringBuilder s = new StringBuilder();
    int residule = a % b;
    Set<Integer> set = new HashSet<Integer>();
    s.append(a / b);
    s.append(".");
    while (residule != 0 && !set.contains(residule)) {
      set.add(residule);
      s.append((residule * 10) / b);
      residule = (residule * 10) % b;
    }
    if (set.contains(residule)) {
      int count = getLen(residule, b);
      s.insert(s.length() - count, "(");
      s.append(")");
    }
    if (s.charAt(s.length() - 1) == '.') {
      s.deleteCharAt(s.length() - 1);
    }
    return s.toString();
  }

  /**
   * Heap sort
   */
  static int LEN;

  public static void maxHeap(int[] num, int i) {
    int left = 2 * i;
    int right = 2 * i + 1;
    int max = i;
    if (left <= LEN && num[left] > num[max]) {
      max = left;
    }
    if (right <= LEN && num[right] > num[max]) {
      max = right;
    }
    if (max != i) {
      swap(num, max, i);
      maxHeap(num, max);
    }
  }

  public static void buildHeap(int[] num) {
    LEN = num.length - 1;
    for (int i = LEN / 2; i >= 0; i--) {
      maxHeap(num, i);
    }
  }

  public static void heapSort(int[] num) {
    buildHeap(num);
    for (int i = LEN; i > 0; i--) {
      swap(num, i, 0);
      LEN--;
      maxHeap(num, 0);
    }
  }

  public static void minHeap(int[] num, int i) {
    int left = i << 1;
    int right = (i << 1) + 1;
    int max = i;
    if (left <= LEN && num[left] < num[max]) {
      max = left;
    }
    if (right <= LEN && num[right] < num[max]) {
      max = right;
    }
    if (max != i) {
      swap(num, max, i);
      minHeap(num, max);
    }
  }

  public static void buildMinHeap(int[] num) {
    LEN = num.length - 1;
    for (int i = LEN >>> 1; i >= 0; i--) {
      minHeap(num, i);
    }
  }

  public static void minHeapSort(int[] num) {
    buildMinHeap(num);
    for (int i = LEN; i > 0; i--) {
      swap(num, i, 0);
      LEN--;
      minHeap(num, 0);
    }
  }

  public static void radixsort(int[] num) {
    final int RADIX = 10;
    List<Integer>[] bucket = new ArrayList[RADIX];
    for (int i = 0; i < bucket.length; i++) {
      bucket[i] = new ArrayList<Integer>();
    }
    boolean maxLength = false;
    int tmp = -1, placement = 1;
    while (!maxLength) {
      maxLength = true;
      for (int i : num) {
        tmp = i / placement;
        bucket[tmp % RADIX].add(i);
        if (maxLength && tmp > 0) {
          maxLength = false;
        }
      }
      int a = 0;
      for (int b = RADIX - 1; b >= 0; b--) {
        for (Integer i : bucket[b]) {
          num[a++] = i;
        }
        bucket[b].clear();
      }
      placement *= RADIX;
    }
  }

  public static int partition(int[] num, int l, int h) {
    int privot = num[l];
    while (l < h) {
      while (num[h] >= privot && l < h) {
        h--;
      }
      num[l] = num[h];
      while (num[l] <= privot && l < h) {
        l++;
      }
      num[h] = num[l];
    }
    num[l] = privot;
    return l;
  }

  public static void quickSort(int[] num) {
    quickSort(num, 0, num.length - 1);
  }

  public static void quickSort(int[] num, int l, int h) {
    if (l < h) {
      int i = partition(num, l, h);
      quickSort(num, l, i - 1);
      quickSort(num, i + 1, h);
    }
  }

  public static int getTotalNumberOfOne(int[][] matrix) {
    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        max = Math.max(getTotalNumberOfOne(matrix, i, j), max);
      }
    }
    return max;
  }

  public static int getTotalNumberOfOne(int[][] matrix, int x, int y) {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] == 0) {
      return 0;
    }
    matrix[x][y] = 0;
    return 1 + getTotalNumberOfOne(matrix, x - 1, y) + getTotalNumberOfOne(matrix, x + 1, y)
        + getTotalNumberOfOne(matrix, x, y - 1) + getTotalNumberOfOne(matrix, x, y + 1);
  }

  public static int getPrime(int pos) {
    int count = 1;
    int num = 2;
    while (count != pos) {
      num++;
      if (isPrime(num)) {
        count++;
      }
    }
    return num;
  }

  public static boolean isPrime(int num) {
    for (int i = 2; i < num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static int findSingleNumber(int[] num) {
    int a = num[0];
    for (int i = 1; i < num.length; i++) {
      a = a ^ num[i];
    }
    return a;
  }

  public static char firstSingle(String str) {
    int[] dp = new int[256];
    for (int i = 0; i < str.length(); i++) {
      dp[str.charAt(i)]++;
    }
    for (int i = 0; i < str.length(); i++) {
      if (dp[str.charAt(i)] == 1) {
        return str.charAt(i);
      }
    }
    return '\0';
  }

  public static int counterof1(int num) {
    int count = 0;
    while (num != 0) {
      count += num & 1;
      num = num >> 1;
    }
    return count;
  }

  public static int getNumberof1(int num) {
    int count = 0;
    while (num != 0) {
      count += (num % 10 == 1) ? 1 : 0;
      num /= 10;
    }
    return count;
  }

  public static int getNumberof1Total(int num) {
    int count = 0;
    for (int i = 1; i <= num; i++) {
      count += getNumberof1(i);
    }
    return count;
  }

  public static int getMajor(int a[]) {
    int x = a[0], cnt = 0;
    for (int i = 0; i < a.length; i++) {
      if (cnt == 0) {
        x = a[i];
        cnt++;
      } else if (a[i] == x) {
        cnt++;
      } else {
        cnt--;
      }
    }
    return x;
  }

  public static List<Integer> getPro(int[] num) {
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < num.length; i++) {
      int value = 1;
      for (int j = 0; j < num.length; j++) {
        value *= i == j ? 1 : num[j];
      }
      res.add(value);
    }
    return res;
  }

  public static int read4(char[] buffer) {
    return 0;
  }

  public static int read(char[] buf, int n) {
    char[] buffer = new char[4];
    int readBytes = 0;
    boolean eof = false;
    while (!eof && readBytes < n) {
      int sz = read4(buffer);
      if (sz < 4) {
        eof = true;
      }
      int bytes = Math.min(n - readBytes, sz);
      System.arraycopy(buffer, 0, buf, readBytes, bytes);
      readBytes += bytes;
    }
    return readBytes;
  }

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int[] count = new int[256];
    int i = 0, numDistinct = 0, maxLen = 0;
    for (int j = 0; j < s.length(); j++) {
      if (count[s.charAt(j)] == 0)
        numDistinct++;
      count[s.charAt(j)]++;
      while (numDistinct > 2) {
        count[s.charAt(i)]--;
        if (count[s.charAt(i)] == 0)
          numDistinct--;
        i++;
      }
      maxLen = Math.max(j - i + 1, maxLen);
    }
    return maxLen;
  }

  /**
   * polite number的意思是一个正整数可以表示为几个连续的正整数的和。 3 = 1 + 2 5 = 2 + 3 6 = 1 + 2 + 3 15 = 1 + 2 + 3 + 4 + 5. 4 + 5 + 6 7 + 8
   * 
   * @param n
   * @return
   */
  public int getHowManyTimes(int n) {
    return 0;
  }

  /**
   * 
   missing number number[0,99] input: [0, 1, 2,50 , 52, 75] output: 3-49,51,53-74,76-99
   */
  public static List<String> getMissingNumbers(int[] num, int start, int end) {
    // TODO
    List<String> res = new ArrayList<String>();
    int pre = start - 1;
    for (int i = 0; i <= num.length; i++) {
      int cur = i == num.length ? end + 1 : num[i];
      if (cur - pre >= 2) {
        res.add(getRange(pre + 1, cur - 1));
      }
      pre = cur;
    }
    return res;
  }

  private static String getRange(int start, int end) {
    return start == end ? new Integer(end).toString() : start + "-" + end;
  }

  public static void randomShuffle(int[] num) {
    Random random = new Random();
    for (int i = 0; i < num.length; i++) {
      int j = random.nextInt(num.length - i) + i;
      swap(num, i, j);
    }
  }

  int MaxValue = Integer.MAX_VALUE / 10;

  public int reverse(int x) {
    int ret = 0;
    while (x != 0) {
      // handle overflow/underflow
      // 2147483647
      if (Math.abs(ret) > MaxValue && x % 10 >= 8) {
        return 0;
      }
      ret = ret * 10 + x % 10;
      x /= 10;
    }
    return ret;
  }

  public boolean isPalindrome(int x) {
    if (x < 0)
      return false;
    int div = 1;
    while (x / div >= 10) {
      div *= 10;
    }
    while (x != 0) {
      int l = x / div;
      int r = x % 10;
      if (l != r)
        return false;
      x = (x % div) / 10;
      div /= 100;
    }
    return true;
  }

  public static List<Integer> getResult(int[] num) {
    List<Integer> res = new ArrayList<Integer>();
    res.add(1);
    for (int i = 1; i < num.length; i++) {
      int e = res.get(i - 1) * num[i - 1];
      System.out.println(e);
      res.add(e);
    }
    int val = 1;
    for (int i = num.length - 1; i >= 0; i--) {
      int element = res.get(i) * val;
      res.set(i, element);
      System.out.println(element);
      val *= num[i];
      System.out.println(val);

    }
    return res;
  }

  public static void moveZeroToEnd2(int[] num) {
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num.length - i - 1; j++) {
        if (num[j] == 0) {
          swap(num, j, j + 1);
        }
      }
    }
  }

  public static void moveValue(int[] num) {
    moveZeroToEnd2(num);
    int i = 0;
    while (i < num.length - 1) {
      if (num[i] == num[i + 1]) {
        num[i] += num[i + 1];
        for (int j = i + 2; j < num.length; j++) {
          num[j - 1] = num[j];
        }
        i++;
      } else {
        i += 2;
      }
    }
  }

  public static Collection<String> getValues(Collection<String> list) {
    while (list.size() != 0) {
      Collection<String> res = new ArrayList<String>();
      for (String str : list) {
        if (str.contains("*")) {
          res.add(str.replaceFirst("\\*", "1"));
          res.add(str.replaceFirst("\\*", "0"));
        }
      }
      if (res.size() != 0) {
        list = res;
      } else {
        return list;
      }
    }
    return list;
  }

  public static int numberOfOne(int num) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      count += num & 1;
      num = num >> 1;
      if (num == 0) {
        return count;
      }
    }
    return count;
  }

  public static String longestsubstringwithtwocharacter(String str) {
    int count = 0;
    int maxLen = 0;
    String res = "";
    int[] table = new int[256];
    int j = 0;
    for (int i = 0; i < str.length(); i++) {
      if (table[str.charAt(i)] == 0) {
        count++;
      }
      table[str.charAt(i)]++;
      if (i - j + 1 > maxLen) {
        maxLen = i - j + 1;
        res = str.substring(j, i + 1);
      }
      while (count > 2) {
        table[str.charAt(j)]--;
        if (table[str.charAt(j)] == 0) {
          count--;
        }
        j++;
      }
    }
    return res;
  }

  public int getMajorNumber(int[] num) {
    int major = num[0];
    int count = 1;
    for (int i = 1; i < num.length; i++) {
      if (count == 0) {
        major = num[i];
        count = 1;
      } else if (major == num[i]) {
        count++;
      } else {
        count--;
      }
    }
    return major;
  }

  public static int getLongsubArray(int[] array, int target) {
    int maxLen = 0;
    int sumSoFar = 0;
    int i = 0, j = 0;
    while (i < array.length) {
      if (sumSoFar < target) {
        sumSoFar += array[i];
        i++;
      } else if (sumSoFar > target) {
        sumSoFar -= array[j];
        j++;
      } else {
        maxLen = Math.max(maxLen, i - j + 1);
        if (array[i] > array[j]) {
          sumSoFar -= array[j];
          j++;
        } else if (array[i] < array[j]) {
          sumSoFar += array[i];
          i++;
        } else {
          i++;
          j++;
        }
      }
    }
    return maxLen;
  }

  public static int findMax(int[] num) {
    int max = 0;
    int l = 0;
    int r = num.length - 1;
    while (l <= r) {
      int mid = (l + r) >>> 1;
      if ((mid == 0 || num[mid - 1] < num[mid]) && (mid == num.length - 1 || num[mid + 1] < num[mid])) {
        max = num[mid];
      } else if (num[mid - 1] > num[mid]) {
        r = mid;
      } else {
        l = mid;
      }
    }
    return max;
  }

  /**
   * dp[2] = dp[0] * dp[1] + dp[1] * dp[0];
   * 
   * dp[3] = dp[0] * dp[2] + dp[1] * dp[1] + dp[2] * dp[0];
   **/
  public static int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }
    return dp[n];
  }

  public static String getBiggerOne(String s1, String s2) {
    int[] table1 = getIntegerTable(s1);
    int[] table2 = getIntegerTable(s2);
    for (int i = table1.length - 1; i >= 0; i--) {
      if (table1[i] > table2[i]) {
        return s1;
      } else if (table1[i] < table2[i]) {
        return s2;
      }
    }
    return s1;
  }

  private static int[] getIntegerTable(String s1) {
    int[] table1 = new int[10];
    for (int i = 0; i < s1.length(); i++) {
      table1[s1.charAt(i) - '0']++;
    }
    return table1;
  }

  public static List<Integer> getNPrime(int n) {
    List<Integer> list = new ArrayList<Integer>();
    int v = 2;
    int i = 0;
    while (i != n) {
      if (isPrime2(v)) {
        list.add(v);
        i++;
      }
      v++;
    }
    return list;
  }

  public static boolean isPrime2(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static List<Integer> getXORList(List<Integer> a, List<Integer> b) {
    List<Integer> res = new ArrayList<Integer>();
    for (Integer i : a) {
      if (!b.contains(i)) {
        res.add(i);
      }
    }
    return res;
  }

  public static String largestNumber(int[] num) {
    if (num == null || num.length == 0) {
      return "";
    }
    Integer[] copy = new Integer[num.length];
    for (int i = 0; i < copy.length; i++) {
      copy[i] = new Integer(num[i]);
    }
    Arrays.sort(copy, new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String value1 = sb1.append(i2).append(i1).toString();
        String value2 = sb2.append(i1).append(i2).toString();
        return value1.compareTo(value2);
      }
    });
    int i = 0;
    while (i < copy.length && copy[i] == 0) {
      i++;
    }
    StringBuilder sb = new StringBuilder();
    while (i < copy.length) {
      sb.append(copy[i++]);
    }
    return sb.length() == 0 ? "0" : sb.toString();
  }

  public static void moveZeroToEnd3(int[] num) {
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num.length - i - 1; j++) {
        if (num[j] == 0) {
          num[j] = num[j + 1];
          num[j + 1] = 0;
        }
      }
    }
  }

  private void reverse(ArrayList<Integer> nums, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
      int temp = nums.get(i);
      nums.set(i, nums.get(j));
      nums.set(j, temp);
    }
  }

  public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
    for (int index = 0; index < nums.size() - 1; index++) {
      if (nums.get(index) > nums.get(index + 1)) {
        reverse(nums, 0, index);
        reverse(nums, index + 1, nums.size() - 1);
        reverse(nums, 0, nums.size() - 1);
        return;
      }
    }
  }
}
