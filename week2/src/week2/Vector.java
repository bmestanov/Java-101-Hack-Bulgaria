/*package week2;

public class Vector<T> implements Iterable<T>{
	T[] arr = (T[])new Object[10];
	int size, capacity;
	
	public Vector(){
		
	}
	
	public Vector(int capacity) {
		this.capacity = capacity;
		arr = (T[])new Object[capacity];
	}
	
	void resize(){
		capacity *= 2;
		T[] newArr = (T[])new Object[capacity];
		for(int i=0; i<size;i++){
			newArr[i] = arr[i];
		}
		
		arr = newArr;
	}
	
	public void add(T data) {
		if(size == capacity){
			resize();
		}
		
		arr[size] = data;
		size++;
	}
	
	@Override
	public String toString() {
		String result = "[";
		for(int i=0; i<size-1;i++){
			result += arr[i] + ", ";
		}
		
		result += arr[size-1];
		result += "]";
		return result;
	}	
}
*/