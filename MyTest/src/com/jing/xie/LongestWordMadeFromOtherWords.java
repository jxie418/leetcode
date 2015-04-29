/**
 * 
 */
package com.jing.xie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @author jamesxieaudaexplorecom
 *
 */
public class LongestWordMadeFromOtherWords {
	
	public static void printLongestWord(String[] arr) {
	    Arrays.sort(arr, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
	    	
	    });
	    HashSet<String> set = new HashSet<String>();
	    for (String str : arr) {
	        set.add(str);
	    }
	    for (String word : arr) {
	        if (canDivide(word, 0, set)) {
	            System.out.println(word);
	            return;
	        }
	    }
	    System.out.println("can not find such word");
	}

	private static boolean canDivide(String word, int from, HashSet<String> set) {
	    if (from == word.length()) {
	        return true;
	    }
	    for (int i = from; i < word.length(); i++) {
	        String str = word.substring(from, i + 1);
	        if (from == 0 && i == word.length() - 1) {
	            continue;
	        } else if (!set.contains(str)) {
	            continue;
	        }
	        if (canDivide(word, i + 1, set)) {
	            return true;
	        }
	    }
	    return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
