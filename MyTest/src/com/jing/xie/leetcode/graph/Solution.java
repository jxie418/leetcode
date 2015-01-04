package com.jing.xie.leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {

  /***
   * clone Graph using Map.
   * 
   * @param node
   * @return
   */
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }
    Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
    map.put(node, newNode);
    queue.add(node);
    while (!queue.isEmpty()) {
      UndirectedGraphNode cur = queue.poll();
      List<UndirectedGraphNode> neighbors = cur.neighbors;
      for (UndirectedGraphNode neighbor : neighbors) {
        if (!map.containsKey(neighbor)) {
          UndirectedGraphNode copy = new UndirectedGraphNode(neighbor.label);
          map.put(neighbor, copy);
          map.get(cur).neighbors.add(copy);
          queue.add(neighbor);
        } else {
          map.get(cur).neighbors.add(map.get(neighbor));
        }
      }
    }
    return newNode;
  }
  /**
   * 
   * @param board
   */
  public void solve(char[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return;
    }
    int row = board.length - 1;
    int col = board[0].length - 1;
    for (int i = 0; i <= row; i++) {
      if (board[i][0] == 'O') {
        solvedfs(board, i, 0);
      }
      if (board[i][col] == 'O') {
        solvedfs(board, i, col);
      }
    }
    for (int i = 1; i < col; i++) {
      if (board[0][i] == 'O') {
        solvedfs(board, 0, i);
      }
      if (board[row][i] == 'O') {
        solvedfs(board, row, i);
      }
    }
    for (int i = 0; i <= row; i++) {
      for (int j = 0; j <= col; j++) {
        if (board[i][j] == 'P') {
          board[i][j] = 'O';
        } else {
          board[i][j] = 'X';
        }
      }
    }
  }

  public void solvedfs(char[][] board, int x, int y) {
    board[x][y] = 'P';
    Queue<Integer> queue = new LinkedList<Integer>();
    int code = x * board[0].length + y;
    queue.add(code);
    while (!queue.isEmpty()) {
      code = queue.poll();
      int row = code / board[0].length;
      int col = code % board[0].length;
      if (row - 1 >= 0 && board[row - 1][col] == 'O') {
        code = (row - 1) * board[0].length + col;
        queue.add(code);
        board[row - 1][col] = 'P';
      }
      if (col - 1 >= 0 && board[row][col - 1] == 'O') {
        code = (row) * board[0].length + col - 1;
        queue.add(code);
        board[row][col - 1] = 'P';
      }
      if (row + 1 < board.length && board[row + 1][col] == 'O') {
        code = (row + 1) * board[0].length + col;
        queue.add(code);
        board[row + 1][col] = 'P';
      }
      if (col + 1 < board[0].length && board[row][col + 1] == 'O') {
        code = row * board[0].length + col + 1;
        queue.add(code);
        board[row][col + 1] = 'P';
      }
    }
  }
  
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
      return false;
    }
    boolean [][] dp = new boolean[board.length][board[0].length];
    for (int i = 0 ; i < board.length ; i++) {
      for (int j = 0 ; j < board[0].length ; j++) {
        if (bfs(board, word, 0,dp, i,j)) {
          return true;
        }
      }
    }
    return false;
  }
  
  boolean bfs(char[][] board, String word, int index, boolean[][] dp, int x, int y) {
    if (index == word.length()) {
      return true;
    }
    if (x < 0 || x>=board.length || y <0 || y >= board[0].length) {
      return false;
    }
    if (board[x][y] != word.charAt(index)) {
      return false;
    }
    if (dp[x][y]) {
      return false;
    }
    dp[x][y] = true;
    if (bfs(board, word, index +1, dp, x+1, y)
           || bfs(board, word, index +1, dp, x, y+1)
           || bfs(board, word, index +1, dp, x-1, y)
           || bfs(board, word, index +1, dp, x, y-1)){
      return true;
    }
    dp[x][y] = false;
    return false;
  }
}
