package week04;

/**
 * Created by Bilal on 6.11.2016 г..
 */
public class MLinkedList<T extends Comparable<T>> implements MList, 
EnhancedStack<T>, QueueInterface<T> {

    private class Node implements Comparable<Node> {
        Node next;
        T data;

        public Node() {
            data = null;
            next = null;
        }

        public Node(T data) {
            this.data = data;
            next = null;
        }

        @Override
        public int compareTo(Node o) {
            return data.compareTo(o.data);
        }
    }
    
    private Node front, back;
    private int size;

    public MLinkedList() {
        front = new Node();
        back = front;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addToFront(Object element) {
        Node new_front = new Node((T) element);
        new_front.next = front;
        front = new_front;
        size++;

        if (size == 1) {
            back = front;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addToBack(Object element) {
        Node new_back = new Node((T) element);
        back.next = new_back;
        back = new_back;
        size++;

        if (size == 1) {
            front = back;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, Object element) {
        Node iter = front;
        for (int i = 0; i < index; i++) {
            if (iter == null) {
                throw new IndexOutOfBoundsException();
            }

            iter = iter.next;
        }

        Node new_node = new Node((T) element);
        new_node.next = iter.next;
        iter.next = new_node;
        size++;

        if (size == 1) {
            front = back;
        }
    }

    @Override
    public T getFirst() {
        return front.data;
    }

    @Override
    public T getLast() {
        return back.data;
    }

    @Override
    public T get(int index) {
        Node at_index = getNode(index);
        if (at_index != null)
            return at_index.data;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(int index) {
    	T to_remove;
        if(index == 0 && size == 1){
        	to_remove = front.data;
        	front = back = null;
        } else if(index == 0){
        	to_remove = front.data;
        	front = front.next;
        } else if(index == size-1){
        	to_remove = back.data;
        	back = getNode(index-2);
        	back.next = null;
        } else {
        	Node to_rem = getNode(index);
        	to_remove = to_rem.data;
        	to_rem.data = to_rem.next.data;
        	to_rem.next = to_rem.next.next;
        }
        size--;
    }

    @Override
    public void append(MList app) {
        int i = 1;
        while (i <= app.size()) {
            addToBack(app.get(i - 1));
            i++;
        }
    }

    @Override
    public T toLast(int k) {
        Node iter = front, offsetted = front;
        for (int i = 0; i < k; i++) {
            offsetted = offsetted.next;
        }

        while (offsetted.next != null) {
            offsetted = offsetted.next;
            iter = iter.next;
        }

        return iter.data;
    }

    /*@Override
    @SuppressWarnings("unchecked")
    public void partitionBy(Object query) {
        Node beforeQHead = null, afterQHead = null,
                beforeQTail = null, afterQTail = null;
        Node iter = front;
        while (iter != null) {
            if (iter.data.compareTo((T) query) < 0) {
                if (beforeQHead == null) {
                    beforeQHead = iter;
                    beforeQTail = beforeQHead;
                } else {
                    beforeQTail.next = iter;
                    beforeQTail = beforeQTail.next;
                }
            } else {
                if (afterQHead == null) {
                    afterQHead = iter;
                    afterQTail = afterQHead;
                } else {
                    afterQTail.next = iter;
                    afterQTail = afterQTail.next;
                }
            }
            iter = iter.next;
        }

        if (beforeQHead == null) {
            front = afterQHead;
            return;
        }

        beforeQTail.next = afterQHead;
        front = beforeQHead;
    }

    @Override
    public boolean areConnected(MList l1, MList l2) {
        return areLinkedConnected((MLinkedList) l1, (MLinkedList) l2);
    }

    @SuppressWarnings("unchecked")
    private boolean areLinkedConnected(MLinkedList l1, MLinkedList l2) {
        Node iter1 = l1.front;
        Node iter2 = l2.front;
        while (iter1 != null) {
            if (iter1 == iter2 || iter1 == (iter2 = iter2.next)) {
                return true;
            }

            iter1 = iter1.next;
            if (iter2 == null) {
                iter2 = l2.front;
            } else {
                iter2 = iter2.next;
            }
        }
        return false;
    }

    @Override
    public T getLoopStart() {
        return null;
    }

    @Override
    public boolean containLoops() {
        Node slow_iter = front;
        Node fast_iter = front.next;
        while (slow_iter != null && fast_iter != null) {
            if (slow_iter == fast_iter || slow_iter == (fast_iter = fast_iter.next)) {
                return true;
            }
            slow_iter = slow_iter.next;
            fast_iter = fast_iter.next;
        }
        return false;
    }


    @Override
    public boolean isPalindrome() {
        if (front == null)
            return true;

        Node iter = front;
        Node prev = new Node(front.data);

        while (iter.next != null) {
            Node temp = new Node(iter.next.data);
            temp.next = prev;
            prev = temp;
            iter = iter.next;
        }

        Node p1 = front;
        Node p2 = prev;

        while (p1 != null) {
            if (!p1.data.equals(p2.data))
                return false;

            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }*/

    private Node getNode(int index) {
        Node iter = front;
        for (int i = 0; i < index; i++) {
            if (iter == null) {
                return null;
            }
            iter = iter.next;
        }

        return iter;
    }

    private Node contains(T element) {
        Node iter = front;
        while (iter != null) {
            if (iter.data.equals(element)) {
                return iter;
            }
            iter = iter.next;
        }
        return null;
    }

	@Override
	public void enqueue(T element) {
		addToBack(element);
	}

	@Override
	public T dequeue() {
		return getFirst(true);
	}

	@Override
	public void push(T element) {
		addToFront(element);
	}

	@Override
	public T pop() {
		return getFirst(true);
	}

	@Override
	public T peek() {
		return getFirst();
	}

	private T getFirst(boolean remove) {
		T first = null;
		if(size!=0){
			first = front.data;
			if(remove) remove(0);
			
		}
		return first;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		String out = "";
		Node iter = front;
		while(iter.next != null) {
			out += iter.data.toString() + ", ";
			iter = iter.next;
		}
		out += iter.data;
	
		return out;
	}

	@Override
	public T getMin() {
		//TODO Figure this out
		return null;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
	
	
}
