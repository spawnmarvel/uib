package com.task1.sortingAlg;

import java.util.Arrays;

/**
 * A class which holds the bucket sort algorithm
 * @author jkl070 2/11/11
 *
 */


public class BucketSortAlg {

	/**
	 * the constructor
	 * empty constructor used to reach the algorithm
	 */
	public BucketSortAlg() {
	}


	/**
	 * Worst case performance 	O(n2)
     * Average case performance 	O(n + k)
     * Worst case space complexity O(n * k)
	 * (runs on O(n) time, O(n + k))with the two text files
	 * bucketSort algorithm, input an array with integers
	 * @param arr, array to be sorted
	 * @return, return sorted array
	 */
	public  int[] bucketSort(int[] ar) {
		int i, j;
		//the new bucket array to store the count of numbers
		int[] countOfNumbers = new int[ar.length];
		Arrays.fill(countOfNumbers, 0);
		//determine the amount of each number in the array I want to sort 
		for(i = 0; i < ar.length; i++ ) {
			countOfNumbers[ar[i]]++;
		}
		//j holds the current position in the array to sort. 
		//i holds the current position in the countOfNumbers array, 
		for(i=0,j=0; i < countOfNumbers.length; i++) {
			for(; countOfNumbers[i]>0; (countOfNumbers[i])--) {
				ar[j++] = i;
			}
		}
		return ar;
	}
	
	/**
	 * runs on O(1) time,(sub runs in same as bucket sort ,since it uses the algorithm bucket sort)
	 * Method to time the bucket sort
	 * input the array to sort and time
	 * @param ar
	 */
	public void testBucket(int []ar) {
		//the start line
		long start = System.currentTimeMillis();
		bucketSort(ar);
		long stop = System.currentTimeMillis();
		//the finish line
		long usedTime = stop - start;
		System.out.println("BucketSort completed in: "+ usedTime + " ms");

	}
	
}

