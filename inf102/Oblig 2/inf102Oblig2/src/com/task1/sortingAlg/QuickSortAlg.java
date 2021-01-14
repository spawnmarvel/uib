package com.task1.sortingAlg;

import java.util.ArrayList;

/**
 * A class which holds the quick sort algorithm
 * @author jkl070 01/11/11
 *
 */
public class QuickSortAlg {
	//the instance variable an array list, the list to be sorted
	private ArrayList<Integer> sorted;
    
	/**
	 * the constructor
	 * empty constructor used to reach the algorithm
	 */
	public QuickSortAlg() {

	}

	/**
	 * Worst case performance 	O(n2), when the list is sorted or nearly sorted, (or bad
	 * pivot choice)
	 * Best case performance 	O(n log n)
	 * Average case performance 	O(n log n)
	 * Worst case space complexity 	O(n)
	 * runs in 0(n log n) time with the two text files
	 * regular quick sort, which uses extra memory(not in place)
	 * @param num, input the list to be sorted
	 * @return, return the sorted list
	 */
	public ArrayList<Integer> quickSort(ArrayList<Integer> num) {
		if (num.size() <= 1) {
			return num;
		}
        //select the pivot
		int pivot = num.size() / 2;
		ArrayList<Integer> l = new ArrayList<Integer>();
		ArrayList<Integer> e = new ArrayList<Integer>();
		ArrayList<Integer> g = new ArrayList<Integer>();
		//the divide part	
		for (int i : num) {
			if (i > num.get(pivot)) {
				g.add(i);
			}
			else if (i == num.get(pivot)) {
				e.add(i);
			}
			else {
				l.add(i);
			}
		}
		l = quickSort(l);
		g = quickSort(g);
		//the concur part, joining the sublists together
		sorted = new ArrayList<Integer>();
		
		for (int i : l) {
			sorted.add(i);
		}
		for(int i : e) {
			sorted.add(i);
		}
		for (int i: g) {
			sorted.add(i);
		}
		//return the sorted array list
		return sorted;
	}
	/**
	 * runs in O(1) time, since it uses the algorithm quick sort
	 * Method to time the quick sort
	 * input the array to sort and time
	 * @param ar
	 */
	public void testQuickSort(ArrayList<Integer> ar) {
		//the start line
		long start = System.currentTimeMillis();
		quickSort(ar);
		long stop = System.currentTimeMillis();
		//the finish line
		long usedTime = stop - start;
		System.out.println("QuickSort completed in: "+ usedTime + " ms");

	}
	/**
	 * runs in O(1) time
	 * method to get the specific integer
	 * @param i, the integer to get
	 * @return the integer
	 */
	public int get(int i) {
		return sorted.get(i);
	}
	

}
