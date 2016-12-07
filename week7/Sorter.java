package week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {
	/**
	 * Sorts the given list by splitting it to sorted and unsorted part and
	 * appending the minimum element of the unsorted part to the sorted one.
	 * 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> selectionSort(List<T> list) {
		if (list == null) {
			return list;
		}

		List<T> result = new ArrayList<>(list);

		int start = 0;
		int end = result.size();

		while (start < end) {
			T min = result.get(start);
			int min_index = start;

			for (int i = start + 1; i < end; i++) {
				T element = result.get(i);
				if (element.compareTo(min) < 0) {
					min = element;
					min_index = i;
				}
			}

			Collections.swap(result, start, min_index);
			start++;
		}

		return result;
	}

	/**
	 * Sorts the given list by putting every next element to the correct place
	 * in the already sorted left part of the list.
	 * 	 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> insertionSort(List<T> list) {
		if (list == null) {
			return list;
		}

		List<T> result = new ArrayList<>(list);
		int end = result.size();

		for (int i = 1; i < end; i++) {
			int j = i;
			while (j > 0 && result.get(j - 1).compareTo(result.get(j)) > 0) {
				Collections.swap(result, j, j - 1);
				j--;
			}
		}

		return result;
	}

	/**
	 * Sorts the given list by swapping the elements in wrong order starting
	 * from the beginning - the largest element "floats" to the end of the list
	 * 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> bubbleSort(List<T> list) {
		if (list == null) {
			return list;
		}

		List<T> result = new ArrayList<T>(list);
		int end = result.size();
		for (int i = 0; i < end; i++) {
			for (int j = 1; j < end - i; j++) {
				T prev = result.get(j-1);
				T current = result.get(j);

				if (prev.compareTo(current) > 0) {
					Collections.swap(result, j, j - 1);
				}
			}
		}

		return result;
	}

	/**
	 * Sorts a list of integers by creating a map of the occurrences of the
	 * elements. O(n+k) where k = range(min(list), max(list))
	 * 
	 * @param list to be sorted
	 * @return sorted copy of the list
	 */
	public static List<Integer> countingSort(List<Integer> list) {
		if (list == null || list.size() <= 1) {
			return list;
		}

		List<Integer> result = new ArrayList<>(list);
		int min = result.get(0);
		int max = min;

		for (Integer i : result) {
			max = (i > max) ? i : max;
			min = (i < min) ? i : min;
		}

		List<Integer> map = new ArrayList<>();
		for (int i = 0; i <= max - min; i++) {
			map.add(0);
		}

		for (Integer i : result) {
			map.set(i - min, map.get(i - min) + 1);
		}

		for (int result_iter = 0, map_iter = 0; map_iter < map.size();) {
			if (map.get(map_iter) == 0) {
				map_iter++;
			} else {
				result.set(result_iter, map_iter + min);
				result_iter++;
				map.set(map_iter, map.get(map_iter) - 1);
			}
		}

		return result;
	}

	/**
	 * Quick sort algorithm with the Lomuto partitioning scheme
	 * 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> quickLomutoSort(List<T> list) {
		if (list == null || list.size() <= 1) {
			return list;
		}

		int left = 0;
		int right = list.size() - 1;
		
		List<T> result = new ArrayList<>(list);
		return lomutoPartition(result, left,right);
	}
	
	private static <T extends Comparable<T>> List<T> lomutoPartition(List<T> list, int start, int end){
		int left = start, right = end;
		int lessIndex = left;

		if (left < right) {
			T pivotElem = list.get(right);
			for (int i = left; i < right; i++) {
				/*
				 * If the element is less or equal to the pivot place it after
				 * the last element that was less or equal
				 */
				if (list.get(i).compareTo(pivotElem) <= 0) {
					Collections.swap(list, lessIndex, i);
					lessIndex++;
				}
			}

			/*
			 * After all the elements place the pivot itself
			 */
			Collections.swap(list, lessIndex, right);

			lomutoPartition(list,0,lessIndex-1);
			lomutoPartition(list,lessIndex + 1, end);
		}

		return list;
	}

	/**
	 * Quick sort algorithm that uses the Hoare partitioning scheme.
	 * 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> quickHoareSort(List<T> list) {
		if (list == null || list.size() <= 1) {
			return list;
		}
		
		int left = 0;
		int right = list.size() - 1;
		
		List<T> result = new ArrayList<>(list);
		return hoarePartition(result, left,right);
	}
	
	private static<T extends Comparable<T>> List<T> hoarePartition(List<T> list, int start, int end){
		int left = start;
		int right = end;
		int mid = right / 2;
		T midElem = list.get(mid);

		// Put the pivot element in the correct place
		while (left <= right) {
			while (list.get(left).compareTo(midElem) < 0) {
				left++;
			}

			while (list.get(right).compareTo(midElem) > 0) {
				right--;
			}

			if (left <= right) {
				Collections.swap(list, left, right);
				left++;
				right--;
			}
		}

		// Sort left side if there are 2 or more elements
		if (start < left - 1) {
			hoarePartition(list,start,left-1);
		}

		// Sort right side if there are 2 or more elements
		// This side contains the original pivot element too
		if (left < end) {
			hoarePartition(list,left+1,end);
		}

		return list;
	}

	/**
	 * Merge sort algorithm
	 * 
	 * @param list to be sorted
	 * @param <T> must be comparable
	 * @return sorted copy of the list
	 */
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
		if (list.size() <= 1) {
			return list;
		}

		int end = list.size();
		int mid = (end - 1) / 2;
		List<T> left = mergeSort(list.subList(0, mid + 1));
		List<T> right = mergeSort(list.subList(mid + 1, end));
		return merge(left, right);
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> l1, List<T> l2) {
		int i, j;
		List<T> result = new ArrayList<>(l1.size() + l2.size());
		for (i = 0, j = 0; i < l1.size() && j < l2.size();) {
			T elem1 = l1.get(i);
			T elem2 = l2.get(j);
			if (elem1.compareTo(elem2) < 0) {
				result.add(elem1);
				i++;
			} else if (elem2.compareTo(elem1) < 0) {
				result.add(elem2);
				j++;
			} else {
				result.add(elem1);
				result.add(elem2);
				i++;
				j++;
			}
		}

		while (i < l1.size()) {
			T remainingElem = l1.get(i);
			result.add(remainingElem);
			i++;
		}

		while (j < l2.size()) {
			T remainingElem = l2.get(j);
			result.add(remainingElem);
			j++;
		}

		return result;
	}

}
