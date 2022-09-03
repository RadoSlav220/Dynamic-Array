//import java.lang.IllegalArgumentException;
//import java.lang.Exception;

public class ArrayList <T> {
	public static final int INITIAL_CAPACITY = 5;
	private T[] arr;
	private int size;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public ArrayList(){
		this.arr = (T[]) new Object [INITIAL_CAPACITY];
		this.size = 0;
		this.capacity = INITIAL_CAPACITY;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) throws IllegalArgumentException {
		if (capacity < 0) {
			throw new IllegalArgumentException("Initial capacity must be non-negative number.");
		}
		this.arr = (T[]) new Object [capacity];
		this.capacity = capacity;
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean empty() {
		return this.size == 0;
	}

	public void clear() {
		for (int i=0; i<size; ++i) {
			arr[i] = null;
		}
		size = 0;
	}
	
	public void swap(ArrayList<T> other) {
		T[] tempArr = this.arr;
		this.arr = other.arr;
		other.arr = tempArr;

		int tempCapacity = this.capacity;
		this.capacity = other.capacity;
		other.capacity = tempCapacity;
		
		int tempSize = this.size;
		this.size = other.size;
		other.size = tempSize;
	}
	
	public int capacity() {
		return this.capacity;
	}
	
	@SuppressWarnings("unchecked")
	public void reserve(int capacity) {
		if (this.capacity < capacity) {
			T[] newArr = (T[]) new Object[capacity];
			for (int i=0; i<size; ++i) {
				newArr[i] = arr[i];
			}
			arr = newArr;
			this.capacity = capacity;
		}
	}
	
	public void resize(int capacity) {
		
	}
	
	public void shrink_to_fit() {
		
	}
	
	public boolean add(T newElement) {
		return true;
	}
	
	public void remove() {
		
	}
	
	public T get(int position) {
		return null;
	}
	
	public void set(int position, T newValue) {
		
	}
	
	public void concatenate(ArrayList<T> other) {
		
	}
}
