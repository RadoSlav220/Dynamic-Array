import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {

	@Test
	void testDefaultConstructor() {
		ArrayList <Integer> list = new ArrayList<>();
		
		assertEquals(0, list.size(), "Initial size must be 0");
		assertEquals(ArrayList.INITIAL_CAPACITY, list.capacity(), "Initial capacity must be " + ArrayList.INITIAL_CAPACITY);
		assertTrue(list.empty(), "Initial state must be empty");
	}
	
	@Test
	void testConstructorWithSpecifiedSize() {
		final int INITIAL_CAPACITY = 10;
		ArrayList <Integer> list = new ArrayList<>(INITIAL_CAPACITY);
		
		assertEquals(INITIAL_CAPACITY, list.capacity(), "Initial capacity must be " + INITIAL_CAPACITY);
		assertEquals(0, list.size(), "Initial size must be 0");
		assertTrue(list.empty(), "Initial state must be empty");
	}
	
	

}
