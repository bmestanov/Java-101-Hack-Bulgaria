package week4;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree<T extends Comparable<T>> {
	private class Node<T> {
		T data;
		Node left_child, right_child;

		Node() {
			data = null;
			left_child = right_child = null;
		}

		public Node(T element) {
			data = element;
			left_child = right_child = null;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}

	private Node<T> root;
	private int size;
	
	public BinarySearchTree(){
		root = null;
		size = 0;
	}
	
	//Week6: construct tree off a sorted List
	public BinarySearchTree(List<T> sorted){
		root = construct(root,sorted);
	}

	private Node<T> construct(BinarySearchTree<T>.Node<T> root, List<T> sorted) {
		if(sorted.isEmpty()){
			return null;
		}
		int mid = sorted.size()/2;
		root = new Node<T>(sorted.get(mid));
		root.left_child = construct(root.left_child, sorted.subList(0, mid));
		root.right_child = construct(root.right_child, sorted.subList(mid+1, sorted.size()));
		return root;
	}

	public void add(T element) {
		if (element != null) {
			root = add(root, element);
			size++;
		}
	}

	private Node<T> add(Node<T> node, T element) {
		if (node == null) {
			return new Node<T>(element);
		}

		if (node.data.compareTo(element) > 0) {
			node.left_child = add(node.left_child, element);
		} else {
			node.right_child = add(node.right_child, element);
		}

		return node;
	}
	
	public int size(){
		return size(root);
	}
	

	private int size(BinarySearchTree<T>.Node<T> root) {
		if(root == null){
			return 0;
		}
		
		return 1 + size(root.left_child) + size(root.right_child);
	}
	
	public int maxDepth(){
		return maxDepth(root);
	}

	private int maxDepth(BinarySearchTree<T>.Node<T> root) {
		if(root == null)
			return 0;
		return Math.max(maxDepth(root.left_child)+1, maxDepth(root.right_child)+1);
	}

	@Override
	public String toString() {
		return getLeftRootRight(root, new ArrayList<T>()).toString();
	}

	/**
	 * Does a depth-first-traversal on the tree
	 * 
	 * @param node
	 *            to start
	 * @param container
	 *            to put elements
	 * @return the container with all the elements
	 */
	private List<T> getLeftRootRight(Node<T> node, List<T> container) {
		if (node == null) {
			return null;
		}

		
		getLeftRootRight(node.left_child, container);
		container.add((T) node.data);
		getLeftRootRight(node.right_child, container);

		return container;
	}
	
	public List<List<T>> getLevels(){
		//TODO This
		return null;
	}
	
	public List<T> getBFS(){
		List<T> list = new ArrayList<>();
		return getBFS(root, list);
	}
	
	private List<T> getBFS(Node<T> node, List<T> container){
		//Initialize this level with the root node
		Queue<Node> thisLevel = new LinkedList<>();
		if(root != null) {
			thisLevel.add(root);
		}
		
		//The process will stop when there are no children to be visited
		while(!thisLevel.isEmpty()){
			Queue<Node> nextLevel = new LinkedList();
			
			//For all the nodes in this level replace with children
			while(!thisLevel.isEmpty()){
				Node<T> front = thisLevel.poll();
				container.add(front.data);
				
				if(front.left_child != null){
					nextLevel.add(front.left_child);
				}
				if (front.right_child != null){
					nextLevel.add(front.right_child);
				}
			}
			
			thisLevel = nextLevel;
		}
		
		return container;
	}

	public boolean contains(T query) {
		return getLeftRootRight(root, new ArrayList<T>(size)).contains(query);
	}

	/**
	 * 
	 * Finds a node with given query
	 * 
	 * @param node
	 *            to start the search
	 * @param query
	 *            to look for
	 * @return the node found or null if it doesn't exist
	 */
	private Node<T> find(Node<T> node, T query) {
		if (node == null || node.data.equals(query)) {
			return node;
		}

		if (node.data.compareTo(query) > 0) {
			return find(node.left_child, query);
		}

		return find(node.right_child, query);
	}

	/**
	 * Removes the element off the tree if it exists
	 * 
	 * @param element
	 *            to be removed
	 */
	public void remove(T element) {
		if (element != null) {
			root = remove(root, element);
		}
	}

	/**
	 * Removes the given node off the tree
	 * 
	 * @param node
	 * @return the updated node
	 */
	private Node<T> remove(Node<T> node, T key) {
		if (node == null)
			return null;
		else if (node.data.compareTo(key) < 0)
			node.right_child = remove(node.right_child, key);
		else if (node.data.compareTo(key) > 0)
			node.left_child = remove(node.left_child, key);
		else {
			// Case: 0 children
			if (node.left_child == null && node.right_child == null) {
				return null;
			}

			// Case: 1 child
			if (node.left_child == null) {
				return node.right_child;
			} else if (node.right_child == null) {
				return node.left_child;
			} else {
			// Case: 2 children
				Node<T> leftmost = getLeftmost(node.right_child);
				node.data = leftmost.data;
				node.right_child = remove(node.right_child, node.data);
			}
		}
		return node;
	}

	/**
	 * Gets the leftmost node in a subtree
	 * 
	 * @param node
	 * @return
	 */
	private Node<T> getLeftmost(Node node) {
		if (node.left_child != null) {
			return getLeftmost(node.left_child);
		}

		return node;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
	
	

}
