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
	
	@Test
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
	
	@Test
	void testClear() {
		ArrayList <String> names = new ArrayList<>();
		names.add("Radoslav");
		names.add("Kaloyan");
		names.add("Sofia");
		names.clear();
		assertTrue(names.empty(), "Must be empty after clear");
		assertEquals(0, names.size(), "Size must be 0 after clear");
	}
	
	@Test
	void testSwapWithEqualListSizes() {
		ArrayList <Integer> list1 = new ArrayList<>();
		list1.add(4);
		list1.add(1);
		list1.add(-1);
		int[] values1 = {4, 1, -1};
		
		ArrayList <Integer> list2 = new ArrayList<>();
		list2.add(5);
		list2.add(6);
		list2.add(7);
		int[] values2 = {5, 6, 7};
		
		list1.swap(list2);
		for (int i=0; i<values1.length; ++i) {
			assertEquals(values1[i], list2.get(i), "Check list2 on position " + i);
			assertEquals(values2[i], list1.get(i), "Check list1 on position " + i);
		}
		
		list1.clear();
		list2.clear();
		
		//Filling the lists with 10000 elements
		for (int i=1; i<=10000; ++i) {
			list1.add(i);
			list2.add(-i);
		}
		list2.swap(list1);
		for (int i=1; i<=10000; ++i) {
			assertEquals(-i, list1.get(i), "Check list1 on position " + i);
			assertEquals(i, list2.get(i), "Check list2 on position " + i);
		}
	}
	
	@Test
	void testSwapWithDifferentListSizes() {
		ArrayList <Integer> list1 = new ArrayList<>();
		list1.add(4);
		list1.add(1);
		list1.add(-1);
		int[] values1 = {4, 1, -1};
		
		ArrayList <Integer> list2 = new ArrayList<>();
		list2.add(5);
		list2.add(6);
		list2.add(7);
		list2.add(8);
		list2.add(9);
		int[] values2 = {5, 6, 7, 8, 9};
		
		list1.swap(list2);
		assertEquals(values2.length, list1.size(), "List1's size must be " + values2.length);
		assertEquals(values1.length, list2.size(), "List2's size must be " + values1.length);
		
		for (int i=0; i<values1.length; ++i) {
			assertEquals(values1[i], list2.get(i), "Check list2 on position " + i);
		}
		
		for (int i=0; i<values2.length; ++i) {
			assertEquals(values2[i], list1.get(i), "Check list2 on position " + i);
		}
			
		list1.clear();
		list2.clear();
		
		//Filling list1 with 10000 elements
		for (int i=1; i<=10000; ++i) {
			list1.add(i);
		}
		
		//Filling list2 with 100000 elements
		for (int i=1; i<=100000; ++i) {
			list1.add(-i);
		}
		
		list2.swap(list1);
		for (int i=1; i<=100000; ++i) {
			assertEquals(-i, list1.get(i), "Check list1 on position " + i);
		}
		
		for (int i=1; i<=10000; ++i) {
			assertEquals(i, list2.get(i), "Check list2 on position " + i);
		}
	}
	
	@Test
	void testCapacity() {
		ArrayList <String> names = new ArrayList<>();
		assertEquals(ArrayList.INITIAL_CAPACITY, names.capacity(), "Initial capacity must be " + ArrayList.INITIAL_CAPACITY);
		
		for (int i=0; i<ArrayList.INITIAL_CAPACITY+1; ++i) {
			names.add("someName");
		}
		assertEquals(ArrayList.INITIAL_CAPACITY*2, names.capacity(), "Capacity must be 2 times the initial capacity");
		
		names.reserve(1000);
		assertTrue(names.capacity() >= 1000, "Capacity must be atleast 1000");
	
		names.resize(50000);
		assertTrue(names.capacity() >= 50000, "Capacity must be atleast 50000");
	
		names.shrink_to_fit();
		assertEquals(names.size(), names.capacity(), "Capacity must fit the size");
	}
}
