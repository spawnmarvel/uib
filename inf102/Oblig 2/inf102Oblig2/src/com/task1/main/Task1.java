package com.task1.main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.task1.loadNumbers.LoadNumbers;
import com.task1.sortingAlg.BucketSortAlg;
import com.task1.sortingAlg.QuickSortAlg;
/**
 * A main class for loading a text file and running the two algorithms in this program
 * @author jkl070 01/11/11
 *
 */

public class Task1 {
	//the array to be sorted
	private int[] arrToSort;
	//the array list to be sorted
	private ArrayList<Integer> arrayToSort;
	//instance variable bucketSort class
	private BucketSortAlg bSort;
	//instance variable quickSort class
	private QuickSortAlg qSort;


	/**
	 * constructor which takes a file input as a string to load the text files
	 * @param filePath
	 * @throws FileNotFoundException
	 */
	public Task1(String filePath) throws FileNotFoundException {
			 getInput(filePath);
		}
	/**
	 * runs in O(n), due to contains(), (sub runs in the time the algorithm uses to compute)
	 * method which takes a file input as a string and checks which file it is:
	 * if it one of the two files from the assignment it makes a call to right list 
	 * and the right sorting algorithm 
	 * @param target, target to run, the text file
	 * @throws FileNotFoundException
	 */
	public void getInput(String target) throws FileNotFoundException {
		if(target.contains("numbers2.txt")) {
			LoadNumbers one = new LoadNumbers(11);
			one.loadFile(target);
			System.out.println("we are sorting: "+ (one.getSize()-1)+ " integers in this test");
			//the call to the algorithm and the timing
			bSortRunner(initArrBucket(one));
			qSortRunner(initArrayListQuick(one));
			//bucket sort output to compare
			bSort = new BucketSortAlg();
			int [] outputBucket = bSort.bucketSort(one.loadFile(target));
			LoadNumbers two = new LoadNumbers(11);
			//quick sort to compare
			qSort = new QuickSortAlg();
			int [] outputQuick = toIntArray(qSort.quickSort(toArrayList(two.loadFile(target))));
			//the comparing of the output
			System.out.println("Is the output from bucketSort & quickSort equal: "+Arrays.equals(outputBucket, outputQuick));
		}
		else if(target.contains("numbers1.txt")) {
			LoadNumbers one = new LoadNumbers(1000001);
			one.loadFile(target);
			System.out.println("we are sorting: "+ (one.getSize()-1)+ " integers in this test");
			//the call to the algorithm and the timing
			bSortRunner(initArrBucket(one));
			qSortRunner(initArrayListQuick(one));
			//bucket sort output to compare
			bSort = new BucketSortAlg();
			int [] outputBucket = bSort.bucketSort(one.loadFile(target));
			LoadNumbers two = new LoadNumbers(1000001);
			//quick sort output to compare
			qSort = new QuickSortAlg();
			int [] outputQuick = toIntArray(qSort.quickSort(toArrayList(two.loadFile(target))));
			//the comparison of the output
			System.out.println("Is the output from bucketSort & quickSort equal: "+ Arrays.equals(outputBucket, outputQuick));

		}
		else {
			System.out.println("Enter one of the two files from assignment 2 in inf102...");
		}
	}
	/**
	 * runs in O(n) time
	 * method to add the numbers from the the load file object
	 * into an array list
	 * @param l, the load file object
	 * @return, return array list with the integer elements
	 */
	public ArrayList<Integer> initArrayListQuick(LoadNumbers l) {
		arrayToSort = new ArrayList<Integer>();
		for(int i = 1; i < l.getSize();i++) {
			arrayToSort.add(l.getInt(i));
		}
		System.out.println("Load complete for QuickSort....");
		return arrayToSort;
	}
	/**
	 * method to invoke quick sort
	 * runs in O(1) time, just invoking the quick sort
	 * @param listToTest, the list to sort, and time
	 */
	public void qSortRunner(ArrayList<Integer> listToTest) {
		qSort = new QuickSortAlg();
		qSort.testQuickSort(listToTest);

	}
	/**
	 * runs in O(n) time
	 * method to add the numbers from the the load file object
	 * into an array 
	 * @param l, the load file object
	 * @return, return array with the integer elements
	 */
	public int [] initArrBucket(LoadNumbers l) {
		int size;
		size = l.getSize()-1;
		arrToSort = new int[size];
		for(int i = 1; i < arrToSort.length; i++) {
			arrToSort[i] = l.getInt(i);
		}
		System.out.println("Load complete for BucketSort....");
		return arrToSort;
	}
	/**
	 * method to invoke bucket sort
	 * runs in O(1) time, just invoking the bucket sort
	 * @param arToTest, the array to sort, and time
	 */
	public void bSortRunner(int []arToTest) {
		bSort = new BucketSortAlg();
		bSort.testBucket(arToTest);
	}

	/**
	 * runs in O(n) time, due to the size of the array list
	 * method to convert an array list to an array
	 * @param l, the array list to convert
	 * @return the array representation
	 */
	public  int[] toIntArray(ArrayList<Integer> l) {
		int [] intArray = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			intArray[i] = l.get(i);
		}
		return intArray;
	}
	/**
	 * runs in O(n) due to the length of the array
	 * method to convert an array to an array list
	 * @param ar, the array to convert
	 * @return the array list representation
	 */
	public ArrayList<Integer> toArrayList(int []ar) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for(int i = 0; i < ar.length; i++) {
			arrayList.add(ar[i]);
		}
		return arrayList;
	}

	public static void main(String [] args) throws FileNotFoundException {
		//String f = "numbers2.txt";
		//Task1 t1 = new Task1(f);
		Task1 t2 = new Task1(args[0]);
	}
}



