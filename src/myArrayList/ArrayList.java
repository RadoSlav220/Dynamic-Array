package myArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Dynamic Array (ArrayList in Java)
 * 
 * Digital Lights Masterclass 2022
 * @author Radoslav Ivanov
 * 
 * @param <T> Type of the stored data.
 */

public class ArrayList <T> {
	public static final int INITIAL_CAPACITY = 5;
	private T[] array;
	private int size;
	private int capacity;
	
	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(array);
		result = prime * result + Objects.hash(capacity, size);
		return result;
	}


	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList other = (ArrayList) obj;
		return Arrays.deepEquals(array, other.array) && capacity == other.capacity && size == other.size;
	}


	/**
	 * Returns a string representation of the object.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		if (this.size > 0) {
			for (int i=0; i<this.size-1; ++i) {
				result.append(array[i] + ", ");
			}
			result.append(array[this.size-1]);
		}
		result.append("]");
		return result.toString();
	}
	
	
	/**
	 * Construct a new empty ArrayList with initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(){
		this.array = (T[]) new Object [INITIAL_CAPACITY];
		this.size = 0;
		this.capacity = INITIAL_CAPACITY;
	}
	
	
	/**
	 * Receives the size of the array and constructs an array with exactly that number of elements.
	 * @param capacity
	 * @throws IllegalArgumentException if the capacity is negative.
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
	
	
	/**
	 * @return Number of elements stored in the container.
	 */
	public int size() {
		return this.size;
	}
	
	
	/**
	 * @return Whether the container is empty.
	 */
	public boolean empty() {
		return this.size == 0;
	}

	
	/**
	 * Empty the content of the container.
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
	
	
	/**
	 * @return The size of the underlying buffer.
	 */
	public int capacity() {
		return this.capacity;
	}
	
	
	/**
	 * Changes the capacity of the array, keeping the stored data.
	 * @param newCapacity - The desired new capacity.
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
	 * Set the size of the capacity to a specified value. 
	 * @param newCapacity - The desired new size of the capacity.
	 * @throws IllegalArgumentException - If the desired new size
	 * of the capacity is negative.
	 */
	public void resize(int newCapacity) throws IllegalArgumentException {
		if (newCapacity < 0) {
			throw new IllegalArgumentException("New size must be non-negative number");
		}
		changeCapacity(newCapacity);
	}
	
	
	/**
	 * Reduce memory usage by freeing unused memory (if possible).
	 */
	public void shrink_to_fit() {
		changeCapacity(this.size);
	}
	
	
	/**
	 * Append the specified element to the end of the list. 
	 * @param newElement - element to be appended 
	 * @return true
	 */
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
	
	
	/**
	 * Remove the element at the end of the array.
	 * @throws ArrayIndexOutOfBoundsException - If the container
	 * is empty, elements cannot be removed, throw an exception.
	 */
	public void remove() throws ArrayIndexOutOfBoundsException {
		if (this.size == 0) {
			throw new ArrayIndexOutOfBoundsException("Cannot remove an element from an empty array");
		}
		this.array[this.size-1] = null;
		this.size--;
	}
	
	
	/**
	 * @param index
	 * @return The element at the specified position in the array.
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 * (index < 0 || index >= size())
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return this.array[index];
	}
	
	
	/**
	 * Replaces the element at the specified position in the list 
	 * with the specified element.
	 * @param index
	 * @param newValue
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 * (index < 0 || index >= size())
	 */
	public void set(int index, T newValue) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		array[index] = newValue;
	}
	
	
	/**
	 * Concatenates the contents of another array to the current 
	 * one. The other array is not changed by the operation.
	 * @param other - An ArrayList of the same type to be
	 * concatenated.
	 * @throws NullPointerException if the given reference is null. 
	 */
	public void concatenate(ArrayList<T> other) {
		if (other == null) {
			throw new NullPointerException("Concatenation with null object!");
		}
		reserve(this.size + other.size);
		for (int i=0; i<other.size; ++i) {
			array[this.size + i] = other.get(i);
		}
		this.size += other.size;
	}
}
