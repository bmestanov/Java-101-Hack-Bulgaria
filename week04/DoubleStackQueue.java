package week04;

public class DoubleStackQueue<T extends Comparable<T>> implements QueueInterface<T> {

	private EnhancedStack<T> s1, s2;

	DoubleStackQueue() {
		s1 = new MLinkedList<T>();
		s2 = new MLinkedList<T>();
	}

	@Override
	public void enqueue(T element) {
		s2.push(element);
	}

	@Override
	public T dequeue() {
		swapIfEmpty();
		T element = null;
		if (s1.getSize() != 0) {
			element = s1.pop();
		}
		return element;
	}

	@Override
	public T peek() {
		swapIfEmpty();
		T element = null;
		if(s1.getSize() != 0){
			element = s1.peek();
		}
		return element;
	}

	@Override
	public int getSize() {
		return s1.getSize() + s2.getSize();
	}

	private void swapIfEmpty() {
		if (s1.getSize() == 0) {
			while(s2.getSize() != 0){
				s1.push(s2.pop());
			}
		}
	}
	
	@Override
	public String toString() {
		return s1.toString() + s2.toString();
	}

}
