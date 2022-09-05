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
		
		assertThrows(IllegalArgumentException.class, () -> {new ArrayList<Integer>(-5);});
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
			assertEquals(-i, list1.get(i-1), "Check list1 on position " + i);
			assertEquals(i, list2.get(i-1), "Check list2 on position " + i);
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
			list2.add(-i);
		}
		
		list2.swap(list1);
		for (int i=1; i<=100000; ++i) {
			assertEquals(-i, list1.get(i-1), "Check list1 on position " + i);
		}
		
		for (int i=1; i<=10000; ++i) {
			assertEquals(i, list2.get(i-1), "Check list2 on position " + i);
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
	
	@Test
	void testReserve() {
		ArrayList <String> names = new ArrayList<>();
		final int request = 5000;
		names.reserve(request);
		assertTrue(names.capacity() >= request, "Capacity must be atleast the requested");
		names.reserve(request * 5);
		assertTrue(names.capacity() >= request * 5, "Capacity must be atleast the requested");
	}
	
	@Test
	void testResize() {
		ArrayList <Integer> list = new ArrayList<>();
		for (int i=0; i<ArrayList.INITIAL_CAPACITY+1; ++i) {
			list.add(i);
		}
		
		int previousSize = list.size();
		final int resizeTo1 = ArrayList.INITIAL_CAPACITY*2 + 14; 
		list.resize(resizeTo1);
		assertEquals(resizeTo1, list.size(), "Size must be " + resizeTo1);
		
		//If resize requested number is greater than the original 
		//size, the rest of the list must be filled with zeroes
		for (int i=previousSize; i<list.size(); ++i) {
			assertEquals(null, list.get(i), "Every element after position " + previousSize + " must be 0");
		}
		
		final int resizeTo2 = ArrayList.INITIAL_CAPACITY / 2 + 1;
		list.resize(resizeTo2);
		assertEquals(resizeTo2, list.size(), "Size must be " + resizeTo2);
		
		//If resize requested number is smaller than the original
		//size, the method must cut the elements after a certain position onwards
		for (int i=0; i<list.size(); ++i) {
			assertEquals(i, list.get(i), "Check the position " + i);
		}
	}
	
	@Test
	void testShrinkToFit() {
		ArrayList <Double> list = new ArrayList<>();
		list.add(4.5);
		list.add(1.2);
		list.add(-1.9);
		list.shrink_to_fit();
		assertEquals(list.size(), list.capacity(), "Capacity must be equal to the size after shrink");
	
		list.resize(700);
		list.remove();
		list.reserve(10000);
		list.shrink_to_fit();
		assertEquals(list.size(), list.capacity(), "Capacity must be equal to the size after shrink");
	}
	
	@Test
	void testAdd() {
		ArrayList <Integer> list = new ArrayList<>();
		list.add(5);
		assertEquals(1, list.size(), "After adding one element size must be 1");
		list.add(7);
		assertEquals(2, list.size(), "After adding second element size must be 2");
		assertEquals(5, list.get(0), "First element must be 5");
		assertEquals(7, list.get(1), "First element must be 7");
		
		ArrayList <Double> list2 = new ArrayList<>();
		for (int i=0; i<ArrayList.INITIAL_CAPACITY+1; ++i) {
			list2.add(0.8);
		}
		assertEquals(ArrayList.INITIAL_CAPACITY+1, list2.size(), "Size must be " + ArrayList.INITIAL_CAPACITY+1);
		assertEquals(ArrayList.INITIAL_CAPACITY * 2, list2.capacity(), "Capacity must be doubled");
	}
	
	@Test
	void testRemove() {
		ArrayList <String> names = new ArrayList<>();
		names.add("Radoslav");
		names.add("Christian");
		names.add("Jonathan");
		assertEquals(3, names.size(), "Size must be 3");
		
		names.remove();
		assertEquals(2, names.size(), "After removal size must be 2");
		names.remove();
		assertEquals(1, names.size(), "After second removal size must be 1");
	}
	
	@Test
	void testGet() {
		ArrayList <Integer> list = new ArrayList<>();
		list.add(1);
		list.add(-1);
		list.add(5);
		assertEquals(1, list.get(0), "First element must be 1");
		assertEquals(-1, list.get(1), "Second element must be 1");
		assertEquals(5, list.get(2), "Third element must be 1");
	}
	
	@Test
	void testSet() {
		ArrayList <Integer> list = new ArrayList<>();
		list.add(1);
		list.add(-1);
		list.add(5);
		list.set(0, 2);
		list.set(1, 3);
		list.set(2, 4);
		assertEquals(2, list.get(0), "First element must be 2");
		assertEquals(3, list.get(1), "Second element must be 3");
		assertEquals(4, list.get(2), "Third element must be 4");
	}

	@Test
	void testConcatenate() {
		ArrayList <Integer> list1 = new ArrayList<>();
		for (int i=0; i<800; ++i) {
			list1.add(i);
		}
		ArrayList <Integer> list2 = new ArrayList<>();
		for (int i=800; i<1000; ++i) {
			list2.add(i);
		}
		
		list1.concatenate(list2);
		assertEquals(1000, list1.size(), "After concatenation list1's size must be 1000");
		for (int i=0; i<1000; ++i) {
			assertEquals(i, list1.get(i));
		}
		
		assertEquals(200, list2.size(), "List2's size must not be changed");
		for (int i=800; i<1000; ++i) {
			assertEquals(i, list2.get(i-800), "Nothing should be changed");
		}
	}
}
