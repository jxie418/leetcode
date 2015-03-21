package com.jing.xie.leetcode.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.jing.xie.TreeNode;

public class Solution {

  /***
   * Question 1 word break;
   */
  
  public boolean wordBreak(String s, Set<String> dict) {
    if (s == null || s.length()== 0 || dict == null || dict.size() == 0) {
      return false;
    }
    boolean [] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
        
      for (int j = 0 ; j < i ; j++) {
        if (dp[j] && dict.contains(s.subSequence(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()] ;
  }
  /***
   * Wildcard Matching
   * @param s
   * @param p
   * @return
   */
  public static boolean isMatch(String s, String p) {
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
  /**
   * Unique Paths
   * @param m
   * @param n
   * @return
   */
  public int uniquePaths(int m, int n) {
    if (m <=0 || n<=0) {
      return 0;
    }
    int [][] dp = new int[m][n];
    for (int i = 0 ; i < m ; i++){
      for (int j = 0 ; j < n ; j ++) {
        if (i == 0 || j==0) {
          dp[i][j] = 1; 
        } else {
          dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
      }
    }
    return dp[m-1][n-1];
  }
  
  /**
   * Unique Paths II
   */
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
      return 0;
    }
    int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

    for (int i = 0; i < dp.length; i++) {
      if (obstacleGrid[i][0] == 0) {
        dp[i][0] = 1;
      } else {
        for (int j = i; j < dp.length; j++) {
          dp[j][0] = 0;
        }
        break;
      }
    }

    for (int j = 0; j < dp[0].length; j++) {
      if (obstacleGrid[0][j] == 0) {
        dp[0][j] = 1;
      } else {
        for (int i = j; i < dp[0].length; i++) {
          dp[0][i] = 0;
        }
        break;
      }
    }
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (obstacleGrid[i][j] == 1) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }
    return dp[dp.length - 1][dp[0].length - 1];
  }
  /**
   * Unique Binary Search Trees 
   * @param n
   * @return
   */
  public int numTrees(int n) {
    if (n == 0) return 1;
    if (n == 1) return 1;
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <=n; i++) {
       for(int j = 1; j <= i;j++) {
           dp[i]+=dp[i-j] * dp[j -1];
       }
    }
    return dp[n];
  }
  /***
   * 
   * @param n
   * @return
   */
  public List<TreeNode> generateTrees(int n) {
    return recurse(1, n);
}

  List<TreeNode> recurse(int low, int high) {
    List<TreeNode> result = new ArrayList<TreeNode>();
    if (low > high) {
      result.add(null);
    } else if (low == high) {
      result.add(new TreeNode(low));
    } else {
      for (int i = low; i <= high; i++) {
        List<TreeNode> left = recurse(low, i - 1);
        List<TreeNode> right = recurse(i + 1, high);
        for (TreeNode n1 : left) {
          for (TreeNode n2 : right) {
            TreeNode root = new TreeNode(i);
            root.left = n1;
            root.right = n2;
            result.add(root);
          }
        }
      }
    }
    return result;
  }
  /**
   * Triangle 
   * @param triangle
   * @return
   */
  public int minimumTotal(List<List<Integer>> triangle) {
    for (int i = triangle.size() - 2; i >= 0; i--) {
      for (int j = 0; j < triangle.get(i).size(); j++) {
        triangle.get(i).set(
            j,
            triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
      }
    }
    return triangle.get(0).get(0);
  }
  /**
   * Scramble String
   * @param s1
   * @param s2
   * @return
   */
  public boolean isScramble(String s1, String s2) {
    if (s1 == null) {
      return s2 == null;
    }
    if (s2 == null) {
      return false;
    }
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.equals(s2)) {
      return true;
    }
    int[] dp = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      dp[s1.charAt(i) - 'a']++;
      dp[s2.charAt(i) - 'a']--;
    }
    for (int i : dp) {
      if (i != 0)
        return false;
    }
    for (int i = 1; i < s1.length(); i++) {
      String s11 = s1.substring(0, i);
      String s12 = s1.substring(i);
      String s21 = s2.substring(0, i);
      String s22 = s2.substring(i);
      if (isScramble(s11, s21) && isScramble(s12, s22)) {
        return true;
      }
      s21 = s2.substring(0, s1.length() - i);
      s22 = s2.substring(s1.length() - i);
      if (isScramble(s11, s22) && isScramble(s12, s21)) {
        return true;
      }
    }
    return false;
  }
  /***
   * Minimum Path Sum 
   * @param grid
   * @return
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int[][] dp = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = grid[i][j];
        } else if (i == 0) {
          dp[i][j] = dp[i][j - 1] + grid[i][j];
        } else if (j == 0) {
          dp[i][j] = dp[i - 1][j] + grid[i][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
      }
    }
    return dp[grid.length - 1][grid[0].length - 1];
  }
  
  public int maxSubArray(int[] A) {
    int sumSoFar = 0;
    int maxSub = Integer.MIN_VALUE;
    for (int i = 0 ; i < A.length; i++) {
      sumSoFar = Math.max(A[i], sumSoFar + A[i]);
      maxSub = Math.max(maxSub, sumSoFar);
    }
    return maxSub;
  }
  /**
   * Maximum Product Subarray 
   * @param A
   * @return
   */
  public int maxProduct(int[] A) {
    int maxValue = A[0];
    int minValue = A[0];
    int maxResult = A[0];
    for (int i = 1; i < A.length ; i++) {
      int copyMaxValue = maxValue;
      maxValue = Math.max(A[i], Math.max(maxValue*A[i], minValue * A[i]));
      minValue = Math.min(A[i], Math.min(copyMaxValue * A[i], minValue * A[i]));
      maxResult = Math.max(maxResult, maxValue);
    }
    return maxResult;
  }
  /***
   * Maximal Rectangle
   * @param height
   * @return
   */
  public int largestRectangleArea(int[] height) {
    Stack<Integer> stack = new Stack<Integer>();
    int i = 0;
    int maxArea = 0;
    int t;
    while (i < height.length) {
      if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
        stack.push(i++);
      } else {
        t = stack.pop();
        maxArea = Math.max(maxArea, height[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
      }
    }
    while (!stack.isEmpty()) {
      t = stack.pop();
      maxArea = Math.max(maxArea, height[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
    }
    return maxArea;
  }

  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    int[][] height = new int[row][col];
    int maxArea = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (matrix[i][j] == '0') {
          height[i][j] = 0;
        } else {
          height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
        }
      }
    }
    for (int i = 0; i < row; i++) {
      int area = largestRectangleArea(height[i]);
      if (area > maxArea) {
        maxArea = area;
      }
    }
    return maxArea;
  }
  /***
   * Longest Valid Parentheses 
   * @param s
   * @return
   */
  public int longestValidParentheses(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int count = 0 ;
    int len = 0;
    int maxResult = 0;
    for (int i =0 ; i <s.length(); i++ ) {
      if (s.charAt(i)=='(') {
        count++;
        len ++;
      } else if (s.charAt(i) ==')') {
        count--;
        len++;
        if (count == 0) {
          maxResult = Math.max(maxResult, len);
        } else if (count < 0) {
          count = 0 ;
          len = 0;
        }
      }
    }
    count = 0 ; 
    len = 0;
    for (int i =s.length() -1 ; i >=0; i-- ) {
      if (s.charAt(i)==')') {
        count++;
        len ++;
      } else if (s.charAt(i) =='(') {
        count--;
        len++;
        if (count == 0) {
          maxResult = Math.max(maxResult, len);
        } else if (count < 0) {
          count = 0 ;
          len = 0;
        }
      }
    }
    return maxResult;
  }
  /**
   * 
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1 == null || s2 == null || s3 == null) {
      return false;
    }
    if (s1.length() + s2.length() != s3.length()) {
      return false;
    } 
    boolean [][] dp = new boolean[s1.length() + 1][s2.length()+1];
    dp[0][0] = true;
    for (int i =1 ; i <= s1.length(); i++) {
      if (s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0]) {
        dp[i][0] = true;
      }
    }
    for (int j = 1; j <= s2.length(); j++) {
      if (s2.charAt(j-1) == s3.charAt(j -1) && dp[0][j-1]) {
        dp[0][j] = true;
      }
    }
    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
         dp[i][j] = ((s1.charAt(i-1) == s3.charAt(i+j-1)&&dp[i-1][j]) ||(s2.charAt(j-1) == s3.charAt(i+j-1)&&dp[i][j-1])); 
      }
    }
    return dp[s1.length()][s2.length()];
  }
  /***
   * Edit Distance 
   * @param word1
   * @param word2
   * @return
   */
  public int minDistance(String word1, String word2) {
    if (word1 == null && word2 == null) {
      return 0 ;
    } else if (word1 == null) {
      return word2.length();
    } else if (word2 == null) {
      return word1.length();
    }
    int [][] dp = new int[word1.length()+1][word2.length()+1];
    dp[0][0] = 0;
    for (int i = 0 ; i <= word1.length(); i++) {
       for (int j = 0 ; j <= word2.length(); j++) {
         if (i == 0) {
           dp[i][j] = j;
         } else if (j == 0) {
           dp[i][j] = i;
         } else if (word1.charAt(i-1) == word2.charAt(j-1)) {
             dp[i][j] = dp[i-1][j-1];
         } else {
           dp[i][j] =Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) +1; 
         }
       }
    }
    return dp[word1.length()][word2.length()];
  }
  /**
   * Distinct Subsequences 
   * @param S
   * @param T
   * @return
   */
  public int numDistinct(String S, String T) {
    int[][] dp = new int[T.length() + 1][S.length() + 1];
    dp[0][0] = 1;
    for (int i = 0; i <= T.length(); i++) {
      for (int j = 0; j <= S.length(); j++) {
        if (i == 0) {
          dp[i][j] = 1;
        } else if (j == 0) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = dp[i][j - 1] + ((T.charAt(i - 1) == S.charAt(j - 1)) ? dp[i - 1][j - 1] : 0);
        }
      }
    }
    return dp[T.length()][S.length()];
  }
  /**
   * 
   * @param s
   * @return
   */
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (s.charAt(0) == '0') {
      return 0;
    }
    int[] number = new int[s.length() + 1];
    number[0] = 1;
    number[1] = 1;
    int tmp;
    for (int i = 2; i <= s.length(); i++) {
      tmp = Integer.parseInt(s.substring(i - 1, i));
      if (tmp != 0) {
        number[i] = number[i - 1];
      }
      if (s.charAt(i - 2) != '0') {
        tmp = Integer.parseInt(s.substring(i - 2, i));
        if (tmp >= 10 && tmp <= 26) {
          number[i] += number[i - 2];
        }
      }
    }
    return number[s.length()];
  }
  /***
   * Climbing Stairs 
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    int value1 = 1, value2 = 2;
    if (n == 0 || n == 1 || n == 2) {
      return n;
    }
    for (int i = 3; i <= n; i++) {
      int value = value1 + value2;
      value1 = value2;
      value2 = value;
    }
    return value2;
  }
  /**
   * Best Time to Buy and Sell Stock 
   * @param prices
   * @return
   */
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int minPrice = Integer.MAX_VALUE;
    int max = 0;
    for (int price : prices) {
      minPrice = Math.min(minPrice, price);
      max = Math.max(max, price - minPrice);
    }
    return max;
  }
  /**
   * Best Time to Buy and Sell Stock III
   * @param prices
   * @return
   */
  public int maxProfit3(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int n = prices.length;

    int buy1 = prices[0], sell2 = prices[n - 1];
    int max1 = 0, max2 = 0;
    int[] profit = new int[n];
    for (int i = 0; i < n; i++) {
      buy1 = Math.min(prices[i], buy1);
      max1 = Math.max(max1, prices[i] - buy1);
      profit[i] += max1;
      int j = n - i - 1;
      sell2 = Math.max(prices[j], sell2);
      max2 = Math.max(max2, sell2 - prices[j]);
      profit[j] += max2;
    }
    int res = 0;
    for (int v : profit) {
      res = Math.max(res, v);
    }
    return res;
  }
  /***
   * Best Time to Buy and Sell Stock II
   * @param prices
   * @return
   */
  public int maxProfit2(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int sum = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        sum += prices[i] - prices[i - 1];
      }
    }
    return sum;
  }
  /***
   * 
   * @param A
   * @return
   */
  public boolean canJump(int[] A) {
    if (A== null || A.length ==0) {
      return true;
    }
    int canReach = 0;
    for (int i = 0 ; i <= canReach && i < A.length ; i++) {
       canReach = Math.max(canReach, i+ A[i]);
    }
    return canReach >= A.length -1;
  }
  /***
   * Jump Game II
   * @param A
   * @return
   */
  public int jump(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int lastReach = 0;
    int reach = 0;
    int step = 0;
    for (int i = 0; i <= reach && i < A.length; i++) {
      if (i > lastReach) {
        lastReach = reach;
        step++;
      }
      reach = Math.max(reach, i + A[i]);
    }
    return step;
  }
  /**
   * Gas Station
   * @param gas
   * @param cost
   * @return
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int total = 0;
    int sum = 0;
    int index = -1;
    for (int i = 0; i < gas.length; i++) {
      total += gas[i] - cost[i];
      sum += gas[i] - cost[i];
      if (sum < 0) {
        sum = 0;
        index = i;
      }
    }
    return total < 0 ? -1 : index + 1;
  }
  /**
   * Candy
   * @param ratings
   * @return
   */
  public int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) {
      return 0;
    }
    int [] dp = new int[ratings.length];
    for (int i = 0 ; i < dp.length ; i++) {
      dp[i] = 1;
    }
    for (int i = 2; i < dp.length;i++) {
      if (ratings[i-1] < ratings[i]) {
        dp[i] += dp[i-1] +1;
      }
    }
    for (int i = dp.length -2; i >=0; i--) {
      if (ratings[i] > ratings[i+1] && dp[i] <= dp[i+1]) {
        dp[i] += dp[i+1] +1;
      }
    }
    int max = 0 ;
    for (int v : dp) {
      max += v;
    }
    return max;
  }
  
  public int kSum(int A[], int k, int target) {
    if (A.length < k)
      return 0;
    int[][][] d = new int[k + 1][A.length + 1][target + 1];
    for (int i = 1; i <= A.length; i++)
      if (A[i - 1] <= target) {
        for (int j = i; j <= A.length; j++)
          d[1][j][A[i - 1]] = 1;
      }

    for (int i = 2; i <= k; i++)
      for (int j = i; j <= A.length; j++)
        for (int v = 1; v <= target; v++) {
          d[i][j][v] = 0;
          if (j > i)
            d[i][j][v] += d[i][j - 1][v];
          if (v >= A[j - 1])
            d[i][j][v] += d[i - 1][j - 1][v - A[j - 1]];
        }
    return d[k][A.length][target];
  }

}
