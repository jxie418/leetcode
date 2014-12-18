package com.jing.xie.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
  public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
    dict.add(end);

    // Key: the dictionary string; Value: HashSet<ArrayList<String>>
    Map<String, HashSet<ArrayList<String>>> map = new HashMap<String, HashSet<ArrayList<String>>>();
    Queue<String> queue = new LinkedList<String>();

    ArrayList<String> startPath = new ArrayList<String>();
    startPath.add(start);
    HashSet<ArrayList<String>> startSet = new HashSet<ArrayList<String>>();
    startSet.add(startPath);
    queue.offer(start);
    map.put(start, startSet);

    ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

    while (!queue.isEmpty()) {
      String str = queue.poll();

      if (str.equals(end)) {
        ret.addAll(map.get(end));
        return ret;
      }

      for (int i = 0; i < str.length(); i++) {
        for (int j = 0; j <= 25; j++) {
          // transform it into another word
          String newStr = replace(str, i, (char) ('a' + j));

          // if a new word is explored
          if (dict.contains(newStr)) {
            if (!map.containsKey(newStr)) {
              // construct a new path set
              HashSet<ArrayList<String>> prevSet = map.get(str);
              HashSet<ArrayList<String>> newSet = new HashSet<ArrayList<String>>();
              for (ArrayList<String> path : prevSet) {
                ArrayList<String> newPath = new ArrayList<String>(path);
                newPath.add(newStr);
                newSet.add(newPath);
              }

              map.put(newStr, newSet);
              queue.offer(newStr);
            } else {
              HashSet<ArrayList<String>> prevSet = map.get(str);
              HashSet<ArrayList<String>> newSet = map.get(newStr);

              Iterator<ArrayList<String>> prevIt = prevSet.iterator();
              Iterator<ArrayList<String>> newIt = newSet.iterator();

              // increase the path set
              if (prevIt.next().size() + 1 == newIt.next().size()) {
                for (ArrayList<String> path : prevSet) {
                  ArrayList<String> newPath = new ArrayList<String>(path);
                  newPath.add(newStr);
                  newSet.add(newPath);
                  // queue.offer(newStr); // will cause TLE!!!
                }
              }
            }
          }
        }
      }
    }

    return ret; // return an empty set
  }

  // replace the index of the given string with the given char
  private String replace(String str, int index, char c) {
    StringBuilder sb = new StringBuilder(str);
    sb.setCharAt(index, c);
    return sb.toString();
  }
}
