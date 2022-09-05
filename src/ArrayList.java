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
	
	public void swap(ArrayList<T> other) {
		
	}
	
	public int capacity() {
		return this.capacity;
	}
	
	@SuppressWarnings("unchecked")
	private void changeCapacity(int newCapacity) throws IllegalArgumentException {
		if (newCapacity < this.size) {
			throw new IllegalArgumentException("Invalid new capacity value.");
		}
		T[] newArr = (T[]) new Object[newCapacity];
		for (int i=0; i<this.size; ++i) {
			newArr[i] = arr[i];
		}
		this.arr = newArr;
		this.capacity = newCapacity;
	}
	
	public void reserve(int capacity) {
		if (this.capacity < capacity) {
			changeCapacity(capacity);
		}
	}
	
	public void resize(int newSize) throws IllegalArgumentException {
		if (newSize < 0) {
			throw new IllegalArgumentException("New size must be non-negative number");
		}
		
		if (newSize > this.capacity) {
			changeCapacity(newSize);
		}
		else if (newSize < this.size) {
			for (int i=this.size-1; i>=newSize; --i) {
				this.arr[i] = null;
			}
		}
		this.size = newSize;
	}
	
	public void shrink_to_fit() {
		changeCapacity(this.size);
	}
	
	public boolean add(T newElement) {
		if (this.size + 1 > this.capacity) {
			if (this.size > 0) {
				changeCapacity(this.capacity * 2);	
			}
			else {
				changeCapacity(1);
			}
		}
		arr[this.size] = newElement;
		this.size++;
		return true;
	}
	
	public void remove() throws ArrayIndexOutOfBoundsException {
		if (this.size == 0) {
			throw new ArrayIndexOutOfBoundsException("Cannot remove an element from an empty array");
		}
		this.size--;
	}
	
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return this.arr[index];
	}
	
	public void set(int index, T newValue) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		arr[index] = newValue;
	}
	
	public void concatenate(ArrayList<T> other) {
		reserve(this.size + other.size);
		for (int i=0; i<other.size; ++i) {
			arr[this.size + i] = other.get(i);
		}
		this.size += other.size;
	}
}
