/**
 * 
 */
package com.jing.xie.leetcode.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JXie
 * 
 */
public class PermutationSolution {

  /**
   * Template Soltuion for 2nd last line subsetsHelper(result, list, num, i + 1); not pos +1.
   * 
   * It can solve following
   */

  public List<List<Integer>> subsets(int[] num) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (num == null || num.length == 0) {
      return result;
    }
    Arrays.sort(num);
    subsetsHelper(result, new ArrayList<Integer>(), num, 0);
    return result;
  }

  private void subsetsHelper(List<List<Integer>> result, List<Integer> list, int[] num, int pos) {
    result.add(new ArrayList<Integer>(list));
    for (int i = pos; i < num.length; i++) {
      list.add(num[i]);
      subsetsHelper(result, list, num, i + 1);
      list.remove(list.size() - 1);
    }
  }

  /**
   * Restore IP Addresses Combinations Letter Combinations of a Phone Number
   */
  /**
   * Solution for SubSet one and SubSet.
   * 
   * @param num
   * @return
   */

  public List<List<Integer>> subsetsWithDup(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (num == null || num.length == 0) {
      return res;
    }
    Arrays.sort(num);
    subset2Helper(res, num, 0, new ArrayList<Integer>());
    return res;
  }

  private void subset2Helper(List<List<Integer>> set, int[] num, int pos, List<Integer> list) {
    set.add(list);
    for (int i = pos; i < num.length; i++) {
      /**
       * So subset one doesn't have this check.
       */
      if (i > pos && num[i - 1] == num[i]) {
        continue;
      }

      List<Integer> tmp = new ArrayList<Integer>(list);
      tmp.add(num[i]);
      subset2Helper(set, num, i + 1, tmp);
    }
  }

  /**
   * Permutations Permutations II Permutations Permutations
   * 
   * @param num
   * @return
   */
  public List<List<Integer>> permuteUnique(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (num == null || num.length == 0) {
      return res;
    }
    Arrays.sort(num);
    boolean[] dp = new boolean[num.length];
    permuteHelper(res, num, new ArrayList<Integer>(), dp);
    return res;
  }

  private void permuteHelper(List<List<Integer>> res, int[] num, List<Integer> list, boolean[] dp) {
    if (num.length == list.size()) {
      res.add(list);
    } else if (list.size() < num.length) {
      for (int i = 0; i < num.length; i++) {
        /**
         * Permutations doesn't have it.
         */
        if (i > 0 && !dp[i - 1] && num[i - 1] == num[i]) {
          continue;
        }
        if (!dp[i]) {
          List<Integer> tmp = new ArrayList<Integer>(list);
          tmp.add(num[i]);
          dp[i] = true;
          permuteHelper(res, num, tmp, dp);
          dp[i] = false;
        }
      }
    }
  }

  /**
   * Combination Sum
   * 
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (candidates == null || candidates.length == 0) {
      return res;
    }
    Arrays.sort(candidates);
    combinationSumhelper(res, candidates, target, 0, new ArrayList<Integer>());
    return res;
  }

  private void combinationSumhelper(List<List<Integer>> res, int[] candidates, int target, int pos, List<Integer> list) {
    if (target == 0) {
      res.add(list);
      return;
    } else if (target > 0) {
      for (int i = pos; i < candidates.length; i++) {
        List<Integer> tmp = new ArrayList<Integer>(list);
        tmp.add(candidates[i]);
        helper(res, candidates, target - candidates[i], i, tmp);
      }
    }
  }

  /**
   * Combination Sum II
   * 
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (candidates == null || candidates.length == 0) {
      return res;
    }
    Arrays.sort(candidates);
    helper(res, candidates, target, 0, new ArrayList<Integer>());
    return res;
  }

  private void helper(List<List<Integer>> res, int[] candidates, int target, int pos, List<Integer> list) {
    if (target == 0) {
      res.add(list);
      return;
    } else if (target > 0) {
      for (int i = pos; i < candidates.length; i++) {
        if (i > pos && candidates[i - 1] == candidates[i]) {
          continue;
        }
        List<Integer> tmp = new ArrayList<Integer>(list);
        tmp.add(candidates[i]);
        helper(res, candidates, target - candidates[i], i + 1, tmp);
      }
    }
  }

  /**
   * Palindrome Partitioning Palindrome Partitioning II
   * 
   * @param s
   * @return
   */
  public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<List<String>>();
    if (s == null || s.trim().length() == 0) {
      return res;
    }
    partitionHelper(res, s, new ArrayList<String>(), 0);
    return res;
  }

  void partitionHelper(List<List<String>> res, String s, List<String> list, int pos) {
    if (s.length() == pos) {
      res.add(list);
    } else {
      for (int i = pos + 1; i <= s.length(); i++) {
        String str = s.substring(pos, i);
        if (isPalindrome(str)) {
          List<String> newList = new ArrayList<String>(list);
          newList.add(str);
          partitionHelper(res, s, newList, i);
        }
      }
    }
  }

  boolean isPalindrome(String str) {
    StringBuilder sb = new StringBuilder(str.toLowerCase()).reverse();
    return str.equals(sb.toString());
  }

  public int minCut(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      dp[i] = s.length() - i;
    }
    boolean[][] table = new boolean[s.length()][s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j) && (j - i < 2 || table[i + 1][j - 1])) {
          table[i][j] = true;
          dp[i] = Math.min(dp[i], dp[j + 1] + 1);
        }
      }
    }
    return dp[0] - 1;
  }
}
