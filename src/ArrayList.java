
public class ArrayList <T> {
	public static final int INITIAL_CAPACITY = 5;
	private T[] arr;
	private int size;
	private int capacity;
	
	public ArrayList(){
		this.arr = (T[]) new Object [INITIAL_CAPACITY];
		this.size = 0;
		this.capacity = INITIAL_CAPACITY;
	}
	
	public ArrayList(int size) {
		
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean empty() {
		return this.size == 0;
	}

	public void clear() {
		
	}
	
	public void swap(ArrayList<T> other) {
		
	}
	
	public int capacity() {
		return this.capacity;
	}
	
	public void reserve(int capacity) {
		
	}
	
	public void resize(int capacity) {
		
	}
	
	public void shrink() {
		
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
