package week2;

public class IntVector {
	private int size, capacity;
	private int[] arr;
	
	public IntVector(){
		capacity = 10;
		size = 0;
		arr = new int[capacity];
	}
	
	public IntVector(int capacity){
		this.capacity = capacity;
		size = 0;
		arr = new int[capacity];
	}
	
	public int get(int index){
		return arr[index];
	}
	
	public int set(int index, int data){
		return arr[index] = data;
	}
	
	public void add(int data){
		if(size == capacity) {
			resize();
		}
		arr[size] = data;
		size++;
	}
	
	void resize() {
		capacity *= 2;
		int[] newArr = new int[capacity];
		for(int i=0; i<size;i++){
			newArr[i] = arr[i];
		}
		
		arr = newArr;
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
