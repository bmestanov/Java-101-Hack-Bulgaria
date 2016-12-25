package week04;

/**
 * Interface for the queue abstract data structure
 * @author mestanov
 * @param <T> should implement Comparable interface
 */
public interface QueueInterface<T extends Comparable<T>> {
	/**
	 * Puts new element in the queue
	 * @param element to be put
	 */
	public void enqueue(T element);
	
	/**
	 * Removes last element in the queue
	 * @return the element that got removed
	 */
	public T dequeue();
	
	/**
	 * Returns the first element to be dequeued
	 * @return the element itself
	 */
	public T peek();
	
	/**
	 * Gets the size of the queue
	 * @return the size of the queue
	 */
	public int getSize();
}
