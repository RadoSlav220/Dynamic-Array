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
	public static final double GROWING_CONSTANT = 2;
	private T[] array;
	private int size;
	private int capacity;
	
	/**
	 * Returns a hash code value for the object.
	 * Provides no-throw guarantee.
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
	 * Provides no-throw guarantee.
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
	 * Provides strong exception guarantee.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		if (this.size > 0) {
			for (int i=0; i<this.size-1; ++i) {
				result.append(array[i].toString() + ", ");
			}
			result.append(array[this.size-1].toString());
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
	 * @param initialCapacity the initial capacity of the list.
	 * @throws IllegalArgumentException if the given capacity is negative.
	 * @throws OutOfMemoryError if there is no enough memory.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Initial capacity must be non-negative number.");
		}
		this.array = (T[]) new Object [initialCapacity];
		this.capacity = initialCapacity;
		this.size = 0;
	}
	
	
	/**
	 * Provides no-throw guarantee.
	 * @return Number of elements stored in the container.
	 */
	public int size() {
		return this.size;
	}
	
	
	/**
	 * Provides no-throw guarantee.
	 * @return Whether the container is empty.
	 */
	public boolean empty() {
		return this.size == 0;
	}

	
	/**
	 * Empty the content of the container.
	 * Provides no-throw guarantee.
	 */
	public void clear() {
		for (int i=0; i<size; ++i) {
			array[i] = null;
		}
		size = 0;
	}
	
	
	/**
	 * Exchanges the contents of two containers. Provides no-throw guarantee.
	 * @param other Another ArrayList with the same type of data stored in.
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
	 * Provides no-throw guarantee.
	 * @return The size of the underlying buffer.
	 */
	public int capacity() {
		return this.capacity;
	}	

	
	/**
	 * Ensures that the underlying buffer has at least a certain capacity.
	 * 
	 * @param requestedCapacity The desired minimum capacity.
	 * @throws OutOfMemoryError if there is no enough memory.
	 * Provides strong exception guarantee.
	 */
	public void reserve(int requestedCapacity) {
		if (this.capacity < requestedCapacity) {
			int newCapacity = Math.max(nextCapacity(), requestedCapacity);
			changeCapacity(newCapacity);
		}
	}
	
	
	/**
	 * Set the size of the capacity to a specified value. 
	 * @param newCapacity The desired new size of the capacity.
	 * @throws IllegalArgumentException if the desired new size
	 * of the capacity is a negative number.
	 * @throws OutOfMemoryError if there is no enough memory.
	 * Provides strong exception guarantee.
	 */
	public void resize(int newCapacity) {
		if (newCapacity < 0) {
			throw new IllegalArgumentException("New size must be non-negative number.");
		}
		changeCapacity(newCapacity);
	}
	
	
	/**
	 * Reduce memory usage by freeing unused memory (if possible).
	 * @throws OutOfMemoryError if there is no enough memory (for changing capacity).
	 * Provides strong exception guarantee.
	 */
	public void shrink_to_fit() {
		changeCapacity(this.size);
	}
	
	
	/**
	 * Append the specified element to the end of the list. 
	 * @param newElement The element to be appended.
	 * @throws OutOfMemoryError if there is no enough memory. 
	 * Provides strong exception guarantee.
	 * @return true
	 */
	public boolean add(T newElement) {
		if (this.size + 1 > this.capacity) {
			if (this.capacity > 0) {
				changeCapacity(nextCapacity());	
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
	 * @throws ArrayIndexOutOfBoundsException if the container is empty.
	 * Provides strong exception guarantee.
	 */
	public void remove() {
		if (this.size == 0) {
			throw new ArrayIndexOutOfBoundsException("Cannot remove an element from an empty array");
		}
		this.array[this.size-1] = null;
		this.size--;
	}
	
	
	/**
	 * Returns the element at the specified position in this list.
	 * @param index Index of the element to return.
	 * @return The element at the specified position in the array.
	 * @throws IndexOutOfBoundsException if the index is out of range.
	 * Provides strong exception guarantee.
	 */
	public T get(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return this.array[index];
	}
	
	
	/**
	 * Replaces the element at the specified position in the list 
	 * with the specified element.
	 * @param index Index of the element to replace.
	 * @param element The element to be stored at the specified position.
	 * @throws IndexOutOfBoundsException if the index is out of range.
	 * Provides strong exception guarantee.
	 */
	public void set(int index, T element) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		array[index] = element;
	}
	
	
	/**
	 * Concatenates the contents of another array to the current one. 
	 * The other array is not changed by the operation.
	 * @param other An ArrayList of the same type to be
	 * concatenated.
	 * @throws NullPointerException if the given reference is null.
	 * @throws OutOfMemoryError if there is no enough memory. 
	 * Provides strong exception guarantee.
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
	
	
	/**
	 * Provides no-throw guarantee.
	 * @return The capacity after next extension.
	 */
	private int nextCapacity() {
		return (int)Math.ceil(this.capacity * GROWING_CONSTANT);
	}
	
	
	/**
	 * Changes the capacity of the array, keeping the stored data (as much as possible).
	 * @param newCapacity The desired new capacity.
	 * @throws OutOfMemoryError if there is no enough memory.
	 * Provides strong exception guarantee.
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
}
