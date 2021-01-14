package controller;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * a class used to represent the score for the search function
 * @author Espen
 *
 */
public class PresentScoreOfSearch {
	
	
	/**
	 * Prints out the top most frequent scores starting from the top
	 * @param map, the map to print
	 */
	public static String printTop(Map<String, Double> map) {
		int count = 0;
		String s = "";
		Set<?> set = map.entrySet();
		// Get an iterator
		Iterator<?> i = set.iterator();
		// Display elements
		while(i.hasNext()) {
			while(count < 10) {
				Map.Entry me = (Map.Entry)i.next();
				s +="Document name: " +me.getKey() + ","+" Score = " + me.getValue()+"\n";
				count++;
			}
			break;
		}
		return s;

	}
	/**
	 * Compares the values in the a map
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		//make a new comparator
		Comparator<K> valueComparator =  new Comparator<K>() {
			//the compare method
			public int compare(K k1, K k2) {
				int compare = map.get(k2).compareTo(map.get(k1));
				if (compare == 0) return 1;
				else return compare;
			}
		};
		//put the sorted results in a new map
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		//return the sorted map
		return sortedByValues;
	}

}
