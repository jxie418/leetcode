package com.jing.xie.s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

class StringComparator implements Comparator<String> {

  @Override
  public int compare(String o1, String o2) {
    return o2.length() - o1.length();
  }

}

public class Solution {

  public static int[] preProcessPattern(char[] ptrn) {
    int i = 0, j = -1;
    int ptrnLen = ptrn.length;
    int[] b = new int[ptrnLen + 1];
    b[i] = j;
    while (i < ptrnLen) {
      while (j >= 0 && ptrn[i] != ptrn[j]) {
        j = b[j];
      }
      i++;
      j++;
      b[i] = j;
    }
    return b;
  }

  /**
   * Based on the pre processed array, search for the pattern in the text
   * 
   * @param text
   *          text over which search happens
   * @param ptrn
   *          pattern that is to be searched
   */
  public static void searchSubString(char[] text, char[] ptrn) {
    int i = 0, j = 0;
    int ptrnLen = ptrn.length;
    int txtLen = text.length;
    int[] b = preProcessPattern(ptrn);
    while (i < txtLen) {
      while (j >= 0 && text[i] != ptrn[j]) {
        j = b[j];
      }
      i++;
      j++;
      if (j == ptrnLen) {
        j = b[j];
      }
    }
  }

  public static List<Integer> boyerMoore(String text, String pattern) {
    List<Integer> matches = new ArrayList<Integer>();
    int m = text.length();
    int n = pattern.length();
    Map<Character, Integer> rightMostIndeies = preprocessForBadCharacterShift(pattern);
    int alignedAt = 0;
    while (alignedAt + (n - 1) < m) {
      for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {
        int indexInText = alignedAt + indexInPattern;
        if (indexInText >= m) {
          break;
        }
        char x = text.charAt(indexInText);
        char y = pattern.charAt(indexInPattern);

        if (x != y) {
          Integer r = rightMostIndeies.get(x);
          if (r == null) {
            alignedAt = indexInText + 1;
          } else {
            int shift = indexInText - (alignedAt + r);
            alignedAt += shift > 0 ? shift : 1;
          }
          break;
        } else if (indexInPattern == 0) {
          matches.add(alignedAt);
          alignedAt++;
        }
      }
    }
    return matches;
  }

  private static Map<Character, Integer> preprocessForBadCharacterShift(String pattern) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int length = pattern.length();
    for (int i = 0; i < length; i++) {
      char c = pattern.charAt(i);
      map.put(c, length - i - 1);
    }
    return map;
  }

  public static String getLongestWord(String[] words) {
    Map<String, Boolean> map = new HashMap<String, Boolean>();
    for (String word : words) {
      map.put(word, true);
    }
    Arrays.sort(words, new StringComparator());
    for (String word : words) {
      if (canBuild(word, true, map)) {
        System.out.println(word);
        return word;
      }
    }
    return "";
  }

  public static boolean canBuild(String str, boolean isOriginalWord, Map<String, Boolean> map) {
    if (map.containsKey(str) && !isOriginalWord) {
      return map.get(str);
    }
    for (int i = 1; i < str.length(); i++) {
      String left = str.substring(0, i);
      String right = str.substring(i);
      if (map.containsKey(left) && map.get(left) == true && canBuild(right, false, map)) {
        return true;
      }
    }
    map.put(str, false);
    return false;
  }

  public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
    List<List<String>> res = new ArrayList<List<String>>();
    Queue<String> queue = new LinkedList<String>();
    queue.add(start);
    while (!queue.isEmpty()) {
      String word = queue.poll();
      if (word.equals(end)) {
        System.out.println("find:" + end);
      }
      for (int i = 0; i < word.length(); i++) {
        char[] wordArray = word.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          if (wordArray[i] != c) {
            wordArray[i] = c;
            String newWord = new String(wordArray);
            if (dict.contains(newWord)) {
              System.out.println(word + "-->" + newWord);
              queue.add(newWord);
              dict.remove(newWord);
            }
          }
        }
      }
    }
    return res;
  }

  public static List<List<String>> findLadders2(String start, String end, Set<String> dict) {
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> currentLay = new LinkedList<String>();
    currentLay.add(start);
    res.add(currentLay);
    while (!currentLay.isEmpty()) {
      List<String> nextLay = new ArrayList<String>();
      for (String word : currentLay) {
        for (int i = 0; i < word.length(); i++) {
          char[] wordArray = word.toCharArray();
          for (char c = 'a'; c <= 'z'; c++) {
            if (wordArray[i] != c) {
              wordArray[i] = c;
              String newWord = new String(wordArray);
              if (dict.contains(newWord)) {
                nextLay.add(newWord);
                dict.remove(newWord);
              }
            }
          }
        }
      }
      currentLay = nextLay;
      if (currentLay.size() != 0) {
        res.add(currentLay);
      }
    }
    return res;
  }

  static List<List<String>> getshortestPath(List<List<String>> path) {
    int minLen = Integer.MAX_VALUE;
    for (int i = 0; i < path.size(); i++) {
      minLen = Math.min(minLen, path.get(i).size());
    }
    List<List<String>> res = new ArrayList<List<String>>();
    for (int i = 0; i < path.size(); i++) {
      if (minLen == path.get(i).size()) {
        res.add(path.get(i));
      }
    }
    return res;
  }

  public static List<List<String>> findLadders3(String start, String end, Set<String> dict) {
    if (start == null || start.length() == 0 || end == null || end.length() == 0 || dict == null || dict.size() == 0) {
      return new ArrayList<List<String>>();
    }
    Node startNode = new Node(start);
    List<String> firstPath = new ArrayList<String>();
    firstPath.add(start);
    startNode.path.add(firstPath);
    Node endNode = new Node(end);
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(startNode);
    while (!queue.isEmpty()) {
      Node word = queue.poll();
      if (word.val.equals(end)) {
        for (List<String> path : word.path) {
          List<String> newPath = new ArrayList<String>(path);
          newPath.add(end);
          endNode.addPath(newPath);
        }
      }
      for (int i = 0; i < word.val.length(); i++) {
        char[] wordArray = word.val.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          wordArray[i] = c;
          String newWord = new String(wordArray);
          if (!newWord.equals(word.val)) {
            if (newWord.equals(end)) {
              for (List<String> path : word.path) {
                List<String> newPath = new ArrayList<String>(path);
                newPath.add(newWord);
                endNode.addPath(newPath);
              }
              break;
            } else {
              Node newNode = new Node(newWord);
              boolean toAdd = false;
              for (List<String> path : word.path) {
                if (dict.contains(newWord) && !path.contains(newWord)) {
                  List<String> newPath = new ArrayList<String>(path);
                  newPath.add(newWord);
                  newNode.addPath(newPath);
                  toAdd = true;
                }
              }
              if (toAdd)
                queue.add(newNode);
            }
          }
        }
      }
    }
    return endNode.path;
  }

  public static List<List<String>> dfsPathDP(
      List<List<String>> levels,
      int level,
      String start,
      String end,
      HashMap<String, List<List<String>>> cache) {
    if (cache.containsKey(start)) {
      return cache.get(start);
    }
    if (level > levels.size()) {
      return null;
    }
    if (level == levels.size()) {
      if (start.equals(end)) {
        List<List<String>> endLay = new ArrayList<List<String>>();
        List<String> endPath = new ArrayList<String>();
        endPath.add(end);
        endLay.add(endPath);
        return endLay;
      } else {
        return null;
      }
    }
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> words = levels.get(level);
    for (int i = 0; i < words.size(); i++) {
      List<List<String>> newLay = dfsPathDP(levels, level + 1, words.get(i), end, cache);
      if (newLay == null) {
        continue;
      }
      for (List<String> paths : newLay) {
        List<String> newPath = new ArrayList<String>();
        newPath.add(start);
        newPath.addAll(paths);
        res.add(newPath);
      }
    }
    cache.put(start, res);
    return res;
  }

  public static boolean isMatch(String s, String p) {
    if (p == null) {
      return s == null;
    }
    if (p.length() == 0) {
      return s.length() == 0;
    }
    if (p.length() < 2 || p.charAt(1) != '*') {
      if (s.length() < 1 || p.charAt(0) != '?' || p.charAt(0) != s.charAt(0)) {
        return false;
      }
      return isMatch(s.substring(1), p.substring(1));
    } else {
      int len = s.length();
      int i = -1;
      while (i < len && (i < 0 || p.charAt(0) == '?' || p.charAt(0) == s.charAt(0))) {
        if (isMatch(s.substring(i + 1), p.substring(2))) {
          return true;
        }
        i++;
      }
    }
    return false;
  }

  public boolean isMatch2(String s, String p) {
    int i = 0;
    int j = 0;
    int start = -1;
    int mark = -1;
    while (i < s.length()) {
      if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') {
        start = j++;
        mark = i;
      } else if (start != -1) {
        j = start + 1;
        i = mark++;
      } else {
        return false;
      }
    }
    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }
    return j == p.length();
  }

  public void solveSudoku(char[][] board) {
    solve(board);
  }

  boolean solve(char[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          for (char c = '1'; c <= '9'; c++) {
            board[i][j] = c;
            if (isValid(board, i, j) && solve(board)) {
              return true;
            }
            board[i][j] = '.';
          }
          return false;
        }
      }
    }
    return true;
  }

  boolean isValid(char[][] board, int x, int y) {
    for (int i = 0; i < 9; i++) {
      if (i != x && board[x][y] == board[i][y]) {
        return false;
      }
    }
    for (int j = 0; j < 9; j++) {
      if (j != y && board[x][y] == board[x][j]) {
        return false;
      }
    }
    for (int i = 3 * (x / 3); i < 3 * (x / 3 + 1); i++) {
      for (int j = 3 * (y / 3); j < 3 * (y / 3 + 1); j++) {
        if (x != i && y != j && board[x][y] == board[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  public List<String[]> solveNQueens(int n) {
    List<String[]> res = new ArrayList<String[]>();
    int[] local = new int[n];
    dfsNQueues(res, local, 0, n);
    return res;
  }

  void dfsNQueues(List<String[]> res, int[] local, int cur, int n) {
    if (cur == n) {
      printlnBoard(res, local, n);
    } else {
      for (int i = 0; i < n; i++) {
        local[i] = cur;
        if (isValid(local, cur)) {
          dfsNQueues(res, local, cur + 1, n);
        }
      }
    }
  }

  boolean isValid(int[] local, int cur) {
    for (int i = 0; i < cur; i++) {
      if (local[i] == local[cur] || Math.abs(local[i] - local[cur]) == cur - i) {
        return false;
      }
    }
    return true;
  }

  void printlnBoard(List<String[]> res, int[] local, int n) {
    String[] s = new String[n];
    for (int i = 0; i < n; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < n; j++) {
        if (local[i] == j) {
          sb.append("Q");
        } else {
          sb.append(".");
        }
      }
      s[i] = sb.toString();
    }
  }

  public int largestRectangleArea(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int maxValue = 0;
    int i = 0;
    Stack<Integer> stack = new Stack<Integer>();
    while (i < height.length) {
      if (stack.isEmpty() || height[stack.peek()] < height[i]) {
        stack.push(i++);
      } else {
        int pos = stack.pop();
        maxValue = Math.max(maxValue, height[pos] * (stack.isEmpty() ? i : i - pos - 1));
      }
    }
    while (!stack.isEmpty()) {
      int pos = stack.pop();
      maxValue = Math.max(maxValue, height[pos] * (stack.isEmpty() ? height.length : height.length - pos - 1));
    }
    return maxValue;
  }

  public static void sortString(String[] a) {
    int wordLen = a[0].length();
    int R = 256;
    String[] copy = new String[a.length];
    for (int i = wordLen - 1; i >= 0; i--) {
      int[] count = new int[R + 1];
      for (int j = 0; j < a.length; j++) {
        count[a[j].charAt(i) + 1]++;
      }
      System.out.println(Arrays.toString(count));
      for (int k = 0; k < R; k++) {
        count[k + 1] += count[k];
      }
      System.out.println(Arrays.toString(count));
      for (int j = 0; j < a.length; j++) {
        copy[count[a[j].charAt(i)]++] = a[j];
      }
      for (int j = 0; j < a.length; j++) {
        a[j] = copy[j];
      }
    }
  }

  public int strcmp(String s1, String s2) {
    if (s1 == null) {
      if (s2 == null) {
        return 0;
      } else {
        return -1;
      }
    }
    if (s2 == null) {
      return 1;
    }
    int i = 0, j = 0;
    while (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
      i++;
      j++;
    }
    if (i == s1.length() && j == s2.length()) {
      return 0;
    }
    if (i == s1.length()) {
      return -1;
    }
    if (j == s2.length()) {
      return 1;
    }
    return s1.charAt(i) - s2.charAt(j);
  }
}
