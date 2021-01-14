package controller;
/**
 * A class which has a static method for computing the euclidean length of a vector
 * @
 *
 */
public class Euclidean {
	
	/**
	 * a method for computing normalized vector length
	 * @param arr, the vector to normalize
	 * @return, the normalized length
	 */
	public static double normalizeEuclidean(double [] arr)  {
		double normLength = 0.0;
		double vector = 0.0;
		for (int i = 0; i < arr.length; i++) {
			vector += arr[i] * arr[i];
		}
		System.out.println(vector);
		//normLength = normLength / Math.sqrt(vector);
		normLength =  Math.sqrt(vector);
		return normLength;
	}

}
