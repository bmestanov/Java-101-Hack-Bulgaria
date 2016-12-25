package week04;
/**
 * Interface of stack abstract data structure
 * @author mestanov
 * @param <T> should implement Comparable interface
 */

public interface EnhancedStack<T extends Comparable<T>> {
	/**
	 * Puts a new element in the stack
	 * @param element
	 */
	public void push(T element);
	
	/**
	 * Removes the element to be removed of the stack
	 * @return the element that got removed
	 */
	public T pop();
	
	/**
	 * Gets the element that's on top of the stack
	 * @return the element itself
	 */
	public T peek();
	
	/**
	 * Gets the size of the stack
	 * @return the number of elements in the stack
	 */
	public int getSize();
	
	/**
	 * Finds the minimum element in the stack
	 * @return the minimum element
	 */
	public T getMin();
	
	/**
	 * Sorts the stack
	 * 
	 */
	public void sort();
}
