package com.jing.xie.ar;

public class Solution {
  public static String longestSubstring(String str1, String str2) {
    if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int[][] table = new int[str1.length()][str2.length()];
    int maxLen = 0;
    int lastStart = 0;
    for (int i = 0; i < str1.length(); i++) {
      for (int j = 0; j < str2.length(); j++) {
        if (str1.charAt(i) == str2.charAt(j)) {
          if (i == 0 || j == 0) {
            table[i][j] = 1;
          } else {
            table[i][j] = table[i - 1][j - 1] + 1;
          }
        }
        if (maxLen < table[i][j]) {
          maxLen = table[i][j];
          int thisStart = i - table[i][j] + 1;
          if (lastStart == thisStart) {
            sb.append(str1.charAt(i));
          } else {
            lastStart = thisStart;
            sb = new StringBuilder();
            sb.append(str1.substring(thisStart, i + 1));
          }
        }
      }
    }
    return sb.toString();
  }

  public static String longestCommoneSubsequence(String str1, String str2) {
    int[][] table = new int[str1.length() + 1][str2.length() + 1];
    for (int i = str1.length() - 1; i >= 0; i--) {
      for (int j = str2.length() - 1; j >= 0; j--) {
        if (str1.charAt(i) == str2.charAt(j)) {
          table[i][j] = table[i + 1][j + 1] + 1;
        } else {
          table[i][j] = Math.max(table[i + 1][j], table[i][j + 1]);
        }
      }
    }
    int i = 0, j = 0;
    StringBuilder sb = new StringBuilder();
    while (i < str1.length() && j < str2.length()) {
      if (str1.charAt(i) == str2.charAt(j)) {
        sb.append(str1.charAt(i));
        i++;
        j++;
      } else if (table[i + 1][j] >= table[i][j + 1]) {
        i++;
      } else {
        j++;
      }
    }
    return sb.toString();
  }

  public static int longestIncreasingSubsequence(int[] num) {
    int[] table = new int[num.length];
    table[0] = 1;
    for (int i = 1; i < num.length; i++) {
      int maxn = 0;
      for (int j = 0; j < i; j++) {
        if (num[j] < num[i] && table[j] > maxn) {
          maxn = table[j];
        }
      }
      table[i] = maxn + 1;
    }
    int max = 0;
    for (int i = 0; i < table.length; i++) {
      max = Math.max(max, table[i]);
    }
    return max;
  }

  public static int longestDecreasingSubsequence(int[] num) {
    int[] table = new int[num.length];
    table[0] = 1;
    for (int i = 1; i < num.length; i++) {
      int maxn = 0;
      for (int j = 0; j < i; j++) {
        if (num[j] > num[i] && table[j] > maxn) {
          maxn = table[j];
        }
      }
      table[i] = maxn + 1;
    }
    int max = 0;
    for (int i = 0; i < table.length; i++) {
      max = Math.max(max, table[i]);
    }
    return max;
  }
}
