package com.task1.loadNumbers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * A class which is used to read a text file and store the integers in an array 
 * @author jkl070 29/10/11
 *
 */

public class LoadNumbers {
	//the array to store the numbers in from the file
	private int []numbers;


	/**
	 * The constructor which takes a string as the size of the array
	 * in as parameter
	 * @param sizeOfFile
	 */
	public LoadNumbers(int sizeOfFile) {
		numbers = new int [sizeOfFile];
	}
	public LoadNumbers() {

	}
	/**
	 * runs on O(1) time
	 * method which returns the first number/integer
	 * @return first integer
	 */
	public int getFirst() {
		return numbers[0];

	}
	/**
	 * runs on O(1) time
	 * method which returns a given integer form position in
	 * array
	 * @param i
	 * @return
	 */
	public int getInt(int i) {
		return numbers[i];
	}
	/**
	 * runs on O(1) time
	 * method which returns the size of the array
	 * @return size
	 */
	public int getSize() {
		return numbers.length;
	}

	/**
	 * runs on O(n) time
	 * method which load the file given as a string
	 * @param filename, the filename to read
	 * @return the array loaded with the elements
	 * @throws FileNotFoundException
	 */
	public int [] loadFile(String filePath) throws FileNotFoundException {
		//the file path 
		File f = new File(filePath);
		byte[] bytes = new byte[(int) f.length()];
		FileInputStream fiStream = new FileInputStream(f);
		try {
			fiStream.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fiStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Splits the line into tokens that are separated by one or more blanks 
		String[] input = new String(bytes).trim().split("\\s+");
		for (int i = 0; i < input.length; i++) 
			numbers[i] = Integer.parseInt(input[i]);
		//return the right array
		return numbers;
	}

}
