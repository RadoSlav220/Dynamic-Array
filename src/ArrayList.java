/**
 * Dynamic Array (ArrayList in Java)
 * 
 * Digital Lights Masterclass 2022
 * @author Radoslav Ivanov
 * 
 * @param <T> Type of the stored data
 */
public class ArrayList <T> {
	public static final int INITIAL_CAPACITY = 5;
	private T[] array;
	private int size;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public ArrayList(){
		this.array = (T[]) new Object [INITIAL_CAPACITY];
		this.size = 0;
		this.capacity = INITIAL_CAPACITY;
	}
	
	/**
	 * Receives the size of the array and constructs an array with exactly that number of elements.
	 * @param capacity
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) throws IllegalArgumentException {
		if (capacity < 0) {
			throw new IllegalArgumentException("Initial capacity must be non-negative number.");
		}
		this.array = (T[]) new Object [capacity];
		this.capacity = capacity;
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean empty() {
		return this.size == 0;
	}

	
	/**
	 * Empty the contents of the container.
	 */
	public void clear() {
		for (int i=0; i<size; ++i) {
			array[i] = null;
		}
		size = 0;
	}
	
	
	/**
	 * Exchanges the contents of two containers.
	 * @param other - Another ArrayList with the same type of data stored in.
	 */
	public void swap(ArrayList<T> other) {
		T[] tempArr = this.array;
		this.array = other.array;
		other.array = tempArr;

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
	
	/**
	 * Changes the capacity of the array, keeping the stored data.
	 * @param newCapacity - The desired new capacity.
	 * @throws IllegalArgumentException - If the new capacity is 
	 * less than the size (so keeping the stored data is impossible)
	 * the function throws an exception.
	 */
	@SuppressWarnings("unchecked")
	private void changeCapacity(int newCapacity) {
		T[] newArr = (T[]) new Object[newCapacity];
		int newSize = Math.min (newCapacity, this.size);
		for (int i=0; i<newSize; ++i) {
			newArr[i] = array[i];
		}
		this.array = newArr;
		this.capacity = newCapacity;
		this.size = newSize;
	}
	
	
	/**
	 * Ensures that the underlying buffer has at least a certain capacity.
	 * @param capacity
	 */
	public void reserve(int capacity) {
		if (this.capacity < capacity) {
			changeCapacity(capacity);
		}
	}
	
	/**
	 * Set the size 
	 * @param newSize
	 * @throws IllegalArgumentException
	 */
	public void resize(int newSize) throws IllegalArgumentException {
		if (newSize < 0) {
			throw new IllegalArgumentException("New size must be non-negative number");
		}
		changeCapacity(newSize);
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
		array[this.size] = newElement;
		this.size++;
		return true;
	}
	
	public void remove() throws ArrayIndexOutOfBoundsException {
		if (this.size == 0) {
			throw new ArrayIndexOutOfBoundsException("Cannot remove an element from an empty array");
		}
		this.array[this.size-1] = null;
		this.size--;
	}
	
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return this.array[index];
	}
	
	public void set(int index, T newValue) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		array[index] = newValue;
	}
	
	public void concatenate(ArrayList<T> other) {
		reserve(this.size + other.size);
		for (int i=0; i<other.size; ++i) {
			array[this.size + i] = other.get(i);
		}
		this.size += other.size;
	}
}
