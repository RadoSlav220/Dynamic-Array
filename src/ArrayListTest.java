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
	
	void testSize() {
		ArrayList <Integer> list1 = new ArrayList<>();
		assertEquals(0, list1.size(), "Initial size must be 0");
		
		list1.add(6);
		assertEquals(1, list1.size(), "Size must be 1");
		
		list1.add(10);
		list1.add(4);
		assertEquals(3, list1.size(), "Size must be 3");
		
		list1.remove();
		assertEquals(2, list1.size(), "Size must be 2 after removing");
		
		ArrayList <Integer> list2 = new ArrayList<>();
		list2.add(4);
		list2.add(8);
		list2.add(14);
		list1.concatenate(list2);
		assertEquals(5, list1.size(), "Size must be 5 after concatenation");
		
		list1.clear();
		assertEquals(0, list1.size(), "Size must be 0 after clearing");
	}

	@Test
	void testEmpty() {
		ArrayList <String> names = new ArrayList<>();
		assertTrue(names.empty(), "Initial state must be empty");
		
		names.add("Radoslav");
		names.add("Kaloyan");
		assertFalse(names.empty(), "There are elements, it must not be empty");
		
		names.remove();
		assertFalse(names.empty(), "There is still an element");
		
		names.add("Kristina");
		names.clear();
		assertTrue(names.empty(), "Must be empty after clear");
	}
}
