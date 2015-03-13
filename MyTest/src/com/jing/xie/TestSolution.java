package com.jing.xie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import java.nio.*;
/**
 * @author JXie
 *
 */
public class TestSolution {

  private int[] number = { 5, 3, 10, 4, 2, 9, 11 };
  private int[] m = { 1, 3,3,3,3,3,3,3, 4, 5, 7 };
  private int[] n = { 2, 3,3,3, 5, 6 };
  private int[] union = { 1, 2, 3, 4, 5, 6, 7 };
  private int[] intersection = { 3, 5 };
  private int[] num = { 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 6, 8, 9 };
  private List<Integer> list;
  private List<Integer> listIntersection;

  @Before
  public void setup() {
    list = new ArrayList<Integer>();
    for (int i : union) {
      list.add(i);
    }
    listIntersection = new ArrayList<Integer>();
    for (int i : intersection) {
      listIntersection.add(i);
    }
  }

  @Test
  public void test() {
    assertEquals("LL", Solution.getPath(number, 2));
    assertEquals("RR", Solution.getPath(number, 11));
    assertEquals("RL", Solution.getPath(number, 9));
    assertEquals("LR", Solution.getPath(number, 4));
  }

  @Test
  public void testAddTwo() {
    int n1 = 5;
    int n2 = 9;
    assertEquals(Solution.addTwo(n1, n2), 14);
  }

  @Test
  public void testUnion() {
    List<Integer> result = Solution.unionArrays(m, n);
    assertEquals(result, list);
  }

  @Test
  public void testIntersection() {
    List<Integer> result = Solution.getIntersection(m, n);
    assertEquals(result, listIntersection);
  }
  
  @Test
  public void testPosition() {
    int i = Solution.leftIndex(num, 3, 0, num.length -1);
    int l = Solution.rightIndex(num, 3, 0, num.length -1);
    assertEquals(Solution.lineAccess(num, 3), l-i+1);
    i = Solution.leftIndex(num, 10, 0, num.length -1);
    l = Solution.rightIndex(num, 10, 0, num.length -1);
    assertEquals(Solution.lineAccess(num, 10), l-i+1);
  }
  
  @Test
  public void testCopy() {
    System.out.println(num);
    int[] output = new int[num.length *2];
    System.out.println(Arrays.toString(num));
    for (int i = 0 ; i < num.length -1 ;i++) {
      System.arraycopy(num, i+1, num, i, num.length-i-1);
      num[num.length -1-i] = 0;
      System.out.println(Arrays.toString(num));
    }
  }
  
  @Test
  public void testLinkedArray() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    for(int i : num) {
      list.add(i);
    }
  }
  
  @Test
  public void testBinarySearch() {
    System.out.println(num.length);
    System.out.println(Solution.insertPosition(num, 10));
    System.out.println(num[Solution.insertPosition(num, 10) >= num.length ? num.length - 1:Solution.insertPosition(num, 10)]);
  }
  
  @Test
  public void testCombination() {
    int [] num = {1, 2, 3};
    Solution.println(Solution.combination(num));
  }
  
  @Test
  public void testMoveZeroToEnd() {
    int [] num = {1,0,3, 0,5,0,4,9,0};
    Solution.moveZeroToEnd(num);
    System.out.println(Arrays.toString(num)); 
  }
  
  @Test
  public void testWorkBreak() {
    String s ="catsanddog";
    Set<String> dict = new HashSet<String>();
    dict.add("cat");
    dict.add("cats");
    dict.add("and");
    dict.add("sand");
    dict.add("dog");
    List<String> res = Solution.wordBreak(s, dict);
    System.out.println(Arrays.toString(res.toArray())); 
    s ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    String[] words = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
    dict = new HashSet<String>();
    dict.addAll(Arrays.asList(words));
    res = Solution.wordBreak(s, dict);
    System.out.println(Arrays.toString(res.toArray()));
  }
  
  @Test
  public void testFindLadders() {
    String start = "a";
    String end ="c";
    String[] words= {"a","c","d"};
    Set<String> dict = new HashSet<String>();
    dict.addAll(Arrays.asList(words));
    List<List<String>> res = Solution.findLadders2(start, end, dict);
    System.out.println(Arrays.toString(res.toArray()));
  }
  
  @Test
  public void binaryString(){
    System.out.println(Integer.toBinaryString(0x55555555));
    System.out.println(Integer.toBinaryString(1>>31));
    System.out.println(Integer.toBinaryString(-1>>>31));
    System.out.println(Integer.toBinaryString(0xFF000000));
    System.out.println(Integer.bitCount(0xFF0000));
    System.out.println(Integer.toBinaryString(0xFF00));
    System.out.println(Integer.bitCount(0xFF00));
    int i = 15;
    System.out.println(Integer.toBinaryString(i));
    char [] carray ={'a','b','c'};
    System.out.println(new String(carray,1,2));
    int count = 0 ;
    System.out.println(Integer.bitCount(i));
    while(i >0) {
      int j = i & 1;
      if ((i & 1) > 0) 
        count++;
      i >>>=1;
    }
    System.out.println(count);
  }
  @Test
  public void reverseInt() {
    int i = 0xff335533;
    System.out.println(Integer.toBinaryString(i));
    //i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
    System.out.println(Integer.toBinaryString(i));
    //i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
    System.out.println(Integer.toBinaryString(i));
    //i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
    System.out.println(Integer.toBinaryString(i));
    System.out.println(Integer.toBinaryString(i<<24));
    System.out.println(Integer.toBinaryString(((i & 0xff00) << 8)));
    System.out.println(Integer.toBinaryString(((i >>> 8) & 0xff00)));
    System.out.println(Integer.toBinaryString((i >>> 24)));
    i = (i << 24) | ((i & 0xff00) << 8) |
        ((i >>> 8) & 0xff00) | (i >>> 24);
    System.out.println(i);
    System.out.println(Integer.toBinaryString(i));
  }
  @Test
  public void TestBitCount() {
    int i = 0xff335533;
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i)); 
    i = i - ((i >>> 1) & 0x55555555);
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i)); 
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i)); 
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i)); 
    i = i + (i >>> 8);
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i)); 
    i = i + (i >>> 16);
    System.out.println(i); 
    System.out.println(Integer.toBinaryString(i));  
    System.out.println(Integer.toBinaryString(i & 0x3f));  
    System.out.println(i & 0x3f);
    System.out.println(5 & -5);
  }
  @Test
  public void testIndexOf() {
    String s1 = "abcd";
    String s2 = "cd";
    int i = Solution.indexOf(s1, s2);
    assertEquals(i, 2);
    s2 ="z";
    i = Solution.indexOf(s1, s2);
    assertEquals(i, -1);
  }
  
  @Test
  public void testIntersection2() {
    Solution.intersection();
    int a = 0 ;
    int b = 1;
    int combine = (b<<16) | a;
    System.out.println(combine);
    String str = "/...";
    String[] strArray = str.split("/");
    System.out.println(Arrays.toString(strArray));
  }
  @Test
  public void testFindFirstMissingInteger() {
    int[] num = {0, 1, 2, 4, 6};
    assertEquals(3, Solution.findFirstMissingInteger(num));
  }
  @Test
  public void testOneEdit() {
    String s1 = "12";
    String s2 = "13";
    assertTrue(Solution.isOneEditDistance(s1, s2));
    assertFalse(Solution.isOneEditDistance("12", "12"));
    assertTrue(Solution.isOneEditDistance("12", "123"));
    assertTrue(Solution.isOneEditDistance("13", "123"));
    assertTrue(Solution.isOneEditDistance("23", "123"));
    assertTrue(Solution.isOneEditDistance("33", "23"));
    assertTrue(Solution.isOneEditDistance("133", "123"));
  }
  @Test
  public void testgetShortest(){
    assertEquals("abcba", Solution.getShortestString("abc"));
    assertEquals("abba", Solution.getShortestString("abb"));
    assertEquals("aaaa", Solution.getShortestString("aaaa"));
  }
  @Test
  public void testCaldec() {
    assertEquals("33.(3)",Solution.caldec(100, 3));
    assertEquals("0.(09)",Solution.caldec(1, 11));
    assertEquals("0.5",Solution.caldec(1, 2));
    assertEquals("0.(18)",Solution.caldec(2, 11));
  }
  @Test
  public void compare() {
    String a = "eBay";
    String b = "eBay";
    assertTrue(a==b);
    a = new String("eBay");
    b = new String("eBay");
    assertFalse(a==b);
  }
  
  @Test
  public void testfirstRepeatCharacter(){
    String str = "babcbcd";
    assertEquals('a', Solution.firstNotRepeatChar(str));
  }
  
  @Test 
  public void testHeapSort() {
    System.out.println(Arrays.toString(number));
    Solution.heapSort(number);
    System.out.println(Arrays.toString(number));
  }
  @Test 
  public void testHeapSort2() {
    System.out.println(Arrays.toString(number));
    Solution.minHeapSort(number);
    System.out.println(Arrays.toString(number));
  }
  @Test 
  public void testRadixSort2() {
    System.out.println(Arrays.toString(number));
    Solution.radixsort(number);
    System.out.println(Arrays.toString(number));
  }
  
  @Test
  public void testQuickSort() {
    System.out.println(Arrays.toString(number));
    Solution.quickSort(number);
    System.out.println(Arrays.toString(number));
  }
  
  @Test
  public void testNumberOne() {
    int[][] num = createMatrix();
    assertEquals(4, Solution.getTotalNumberOfOne(num));
    num = createMatrix();
    num[0][0] = 0;
    assertEquals(3, Solution.getTotalNumberOfOne(num));
  }

  private int[][] createMatrix() {
    int[][]  num = new int[2][2];
    for(int i =0 ; i < 2; i++) {
      for (int j = 0 ; j < 2; j++) {
        num[i][j] = 1;
      }
    }
    return num;
  }
  
  @Test
  public void testPrime() {
    assertEquals(2, Solution.getPrime(1));
    assertEquals(3, Solution.getPrime(2));
    assertEquals(5, Solution.getPrime(3));
    assertEquals(7, Solution.getPrime(4));
    assertEquals(11, Solution.getPrime(5));
    assertEquals(13, Solution.getPrime(6));
  }
  @Test
  public void testFindSingleNumber() {
    int [] num = {1,2,3,4,4};
    assertEquals(4, Solution.findSingleNumber(num));
  }
  
  @Test
  public void testfirstSingle() {
    String str = "abaccdeff";
    assertEquals('b', Solution.firstSingle(str));
  }
  @Test
  public void testCountNumber1() {
    assertEquals(2, Solution.counterof1(3));
    assertEquals(1, Solution.counterof1(4));
    assertEquals(1, Solution.counterof1(8));
    assertEquals(3, Solution.counterof1(7));
  }
  @Test
  public void testGetMajor(){
    int [] num= {2,3,4,1,1,1,1,1};
    assertEquals(1, Solution.getMajor(num));
  }
  @Test
  public void testgetPro() {
    int [] num = {1, 2, 3, 4, 5};
    List<Integer> res = Solution.getPro(num);
    System.out.println(Arrays.toString(res.toArray()));
    List<Integer> res2 = Solution.getResult(num);
    System.out.println(Arrays.toString(res2.toArray()));
  }
  
  @Test
  public void testRandom() {
    int [] num = new int[10];
    for (int i = 0 ; i < num.length;i++) {
      num[i] = i+1;
    }
    Solution.randomShuffle(num);
    System.out.println(Arrays.toString(num));
  }
  @Test
  public void testGetMissingNumber() {
    int [] num = {0, 1, 2,50 , 52, 75};
    List<String> res = Solution.getMissingNumbers(num, 0, 99);
    System.out.println(Arrays.toString(res.toArray()));
  }
  
  @Test
  public void testMoveZeroToEnd2() {
    int [] num = {2,2,0,8,0};
    Solution.moveZeroToEnd3(num);
    System.out.println(Arrays.toString(num));
  }
  @Test
  public void testMove() {
    int [] num = {0,0,2, 0, 2, 0 ,0, 4, 4};
    Solution.moveValue(num);
    System.out.println(Arrays.toString(num));
  }
  @Test
  public void testStar() {
    String  s ="***";
    Collection<String> list = new ArrayList<String>();
    list.add(s);
    Collection<String> res = Solution.getValues(list);
    System.out.println(Arrays.toString(res.toArray()));
  }
  
  @Test
  public void testNumberofOne() {
    assertEquals(3, Solution.numberOfOne(7));
    assertEquals(2, Solution.numberOfOne(3));
    assertEquals(1, Solution.numberOfOne(8));
  }
  
  @Test
  public void testGetBiggerOne() {
    String s1 = "178";
    String s2 = "245";
    assertEquals("178", Solution.getBiggerOne(s1, s2));
  }
  @Test
  public void testGetPrime(){
    System.out.println(Solution.getNPrime(7));
  }
  @Test
  public void largestNumber() {
    int[] num = {3, 30, 34, 5, 9};
    assertEquals(Solution.largestNumber(num),"9534330");
  }
}
