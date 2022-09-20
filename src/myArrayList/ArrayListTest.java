package myArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {

/******************************Helper tools*********************************/
	
	//We assume that every combination of String, int and double values
	//can construct a valid Student object.
	record Student(String name, int fn, double grade) {}

	private final String[] globalNames = {"Radoslav", "Kaloyan", "Sofia"};
	
	ArrayList<String> getNamesList () {
		ArrayList <String> names = new ArrayList<>();
		for (int i=0; i<globalNames.length; ++i) {
			names.add(globalNames[i]);
		}
		return names;
	}
	
	boolean checkIfNamesAreOK(ArrayList<String> names) {
		if (names == null || names.size() != globalNames.length) {
			return false;
		}
		for (int i=0; i<names.size(); ++i) {
			if (!names.get(i).equals(globalNames[i])) {
				return false;
			}
		}
		return true;
	}
	
	ArrayList<Student> getStudentsList () {
		ArrayList <Student> students = new ArrayList<>();
		students.add(new Student("Radoslav", 82154, 5.99));
		students.add(new Student("Osman", 123456, 3.76));
		students.add(new Student("Kristina", 63178, 4.79));
		return students;
	}
	
	
/****************************HashCode tests*******************************/	
	
	@Test
	void testHashCodeWithNonPrimitiveType() {
		ArrayList <Student> reference1 = getStudentsList();
		ArrayList <Student> reference2 = reference1;
		assertEquals(reference1.hashCode(), reference2.hashCode());
		
		ArrayList <Student> reference3 = new ArrayList<>();
		reference3.add(new Student("Boyko", 1234, 3.50));
		assertNotEquals(reference1.hashCode(), reference3.hashCode());
	}
	
	
/******************************Equals tests********************************/	
	
	@Test
	void testEqualsWithPrimitiveType() {
		ArrayList <Integer> list1 = new ArrayList<>();
		ArrayList <Integer> list2 = new ArrayList<>();
		for (int i=0; i<100; ++i) {
			list1.add(i);
			list2.add(i);
		}
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		list1.remove();
		assertFalse(list1.equals(list2));
		assertFalse(list2.equals(list1));
	}

	
	@Test
	void testEqualsWithNonPrimitiveType() {
		ArrayList <Student> list1 = new ArrayList<>();
		ArrayList <Student> list2 = new ArrayList<>();
		for (int i=0; i<100; ++i) {
			list1.add(new Student("Rado" + i, i, i * 1.2));
			list2.add(new Student("Rado" + i, i, i * 1.2));
		}
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		list1.remove();
		assertFalse(list1.equals(list2));
		assertFalse(list2.equals(list1));
	}
	
	
/******************************toString tests*********************************/	
	
	@Test
	void testToStringWithPrimitiveType() {
		ArrayList <Integer> numbers = new ArrayList<>();
		numbers.add(4);
		numbers.add(5);
		numbers.add(8);
		assertTrue(numbers.toString().equals("[4, 5, 8]"));
		numbers.clear();
		assertTrue(numbers.toString().equals("[]"));
		numbers.add(180);
		assertTrue(numbers.toString().equals("[180]"));
	}
	
	
	@Test
	void testToStringWithNonPrimitiveType() {
		ArrayList <Student> students = new ArrayList<>();
		students.add(new Student("Radoslav", 82154, 5.99));
		students.add(new Student("Osman", 123456, 3.76));
		students.add(new Student("Kristina", 63178, 4.79));
		
		assertTrue(students.toString().equals (
				"[Student[name=Radoslav, fn=82154, grade=5.99], "
				+ "Student[name=Osman, fn=123456, grade=3.76], "
				+ "Student[name=Kristina, fn=63178, grade=4.79]]"
		));
		
		students.clear();
		assertTrue(students.toString().equals("[]"));
	}
	
	
/***************************Constructor tests******************************/
	
	@Test
	void testDefaultConstructor() {
		ArrayList <Integer> list = new ArrayList<>();
		
		assertEquals(0, list.size(), "Initial size must be 0");
		assertEquals(ArrayList.INITIAL_CAPACITY, list.capacity(), "Initial capacity must be " + ArrayList.INITIAL_CAPACITY);
		assertTrue(list.empty(), "Initial state must be empty");
	}
	
	
	@Test
	void testConstructorWithSpecifiedSizePrimitiveType() {
		final int INITIAL_CAPACITY = 10;
		ArrayList <Integer> list = new ArrayList<>(INITIAL_CAPACITY);
		
		assertEquals(INITIAL_CAPACITY, list.capacity(), "Initial capacity must be " + INITIAL_CAPACITY);
		assertEquals(0, list.size(), "Initial size must be 0.");
		assertTrue(list.empty(), "Initial state must be empty.");
		
		assertThrows(IllegalArgumentException.class, () -> {new ArrayList<Integer>(-5);});
	}
	
	
	@Test
	void testConstructorWithSpecifiedSizeNonPrimitiveType() {
		final int INITIAL_CAPACITY = 10;
		ArrayList <Student> list = new ArrayList<>(INITIAL_CAPACITY);
		
		assertEquals(INITIAL_CAPACITY, list.capacity(), "Initial capacity must be " + INITIAL_CAPACITY);
		assertEquals(0, list.size(), "Initial size must be 0");
		assertTrue(list.empty(), "Initial state must be empty");
		
		assertThrows(IllegalArgumentException.class, () -> {new ArrayList<Student>(-5);});
	}
	

/******************************Clear tests*********************************/	

	@Test
	void testClear() {
		ArrayList <String> names = getNamesList();
		assertFalse(names.empty());
		names.clear();
		assertTrue(names.empty(), "Must be empty after clearing.");
	}
	
	
/******************************Swap tests*********************************/
	
	@Test
	void testSwapWithEqualSmallListSizes() {
		ArrayList <Integer> list1 = new ArrayList<>();
		int[] values1 = {4, 1, -1};
		for (int i=0; i<values1.length; ++i) {
			list1.add(values1[i]);
		}
		
		ArrayList <Integer> list2 = new ArrayList<>();
		int[] values2 = {5, 6, 7};
		for (int i=0; i<values2.length; ++i) {
			list2.add(values2[i]);
		}
		
		list1.swap(list2);
		for (int i=0; i<values1.length; ++i) {
			assertEquals(values1[i], list2.get(i), "Check list2 on position " + i);
			assertEquals(values2[i], list1.get(i), "Check list1 on position " + i);
		}
	}
	
	
	@Test
	void testSwapWithEqualBigListSizes() {
		ArrayList <Integer> list1 = new ArrayList<>();
		ArrayList <Integer> list2 = new ArrayList<>();
		
		final int ELEMENTS_COUNT = 10000;
		for (int i=1; i<=ELEMENTS_COUNT; ++i) {
			list1.add(i);
			list2.add(-i);
		}
		list2.swap(list1);
		for (int i=1; i<=ELEMENTS_COUNT; ++i) {
			assertEquals(-i, list1.get(i-1), "Check list1 on position " + i);
			assertEquals(i, list2.get(i-1), "Check list2 on position " + i);
		}
	}
	
	
	@Test
	void testSwapWithDifferentListSizes() {
		ArrayList <Integer> list1 = new ArrayList<>();	
		ArrayList <Integer> list2 = new ArrayList<>();
		
		final int ELEMENTS_COUNT1 = 10000;
		final int ELEMENTS_COUNT2 = 100000;
		
		for (int i=1; i<=ELEMENTS_COUNT1; ++i) {
			list1.add(i);
		}
		
		for (int i=1; i<=ELEMENTS_COUNT2; ++i) {
			list2.add(-i);
		}
		
		list2.swap(list1);
		for (int i=1; i<=ELEMENTS_COUNT2; ++i) {
			assertEquals(-i, list1.get(i-1), "Check list1 on position " + i);
		}
		
		for (int i=1; i<=ELEMENTS_COUNT1; ++i) {
			assertEquals(i, list2.get(i-1), "Check list2 on position " + i);
		}
	}
	
	
	@Test
	void testSwapWithNonPrimitiveType() {
		ArrayList <Student> students1 = new ArrayList<>();
		ArrayList <Student> students2 = new ArrayList<>();
		
		Student simona = new Student("Simona", 78165, 5.10);
		Student petar = new Student("Petar", 82178, 3.10);
		Student maria = new Student("Maria", 89162, 4.78);
		
		students1.add(simona);
		students1.add(maria);
		students2.add(petar);
		
		students1.swap(students2);
		assertEquals(1, students1.size());
		assertEquals(2, students2.size());
		
		assertEquals(petar, students1.get(0));
		assertEquals(simona, students2.get(0));
		assertEquals(maria, students2.get(1));
	}
	
	
/**************************Reserve tests*******************************/
	
	@Test
	void testReserveWithMoreThanCurrentCapacity() {
		ArrayList <Student> students = getStudentsList();
		final int request = students.size() + 5000;
		students.reserve(request);
		assertTrue(students.capacity() >= request, "Capacity must be atleast the requested.");
	}
	
	
	@Test
	void testReserveWithLessThanCurrentCapacity() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		names.reserve(globalNames.length - 1);
		assertEquals(prevCapacity, names.capacity(), "Reserve must have had no effect.");
		assertTrue(checkIfNamesAreOK(names), "Reserve must have had no effect.");
	}
	
	
	@Test
	void testReserveWithNegativeRequest() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		names.reserve(-100);
		assertEquals(prevCapacity, names.capacity(), "Reserve must have had no effect.");
		assertTrue(checkIfNamesAreOK(names), "Reserve must have had no effect.");
	}
	
	
	@Test
	void testReserveWithTooLargeRequest() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		try {
			names.reserve(Integer.MAX_VALUE);
		} catch (OutOfMemoryError e) {
			assertEquals(prevCapacity, names.capacity(), "Reserve must have had no effect.");
			assertTrue(checkIfNamesAreOK(names), "Reserve must have had no effect.");
		}
	}
	
	
/****************************Resize tests*********************************/
	
	@Test
	void testResizeWithGreaterNewCapacity() {
		ArrayList <String> names = getNamesList();
		final int newCapacity = names.capacity() * 2 + 1;
		names.resize(newCapacity);
		assertEquals(newCapacity, names.capacity());
		assertTrue(checkIfNamesAreOK(names));
	}
	
	
	@Test
	void testResizeWithSmallerNewCapacity() {
		ArrayList <Integer> numbers = new ArrayList<>();
		for (int i=0; i<1000; ++i) {
			numbers.add(i);
		}
		final int newCapacity = 350;
		numbers.resize(newCapacity);
		assertEquals(newCapacity, numbers.capacity());
		assertEquals(newCapacity, numbers.size(), "Size must also be decreased.");
		for (int i=0; i<newCapacity; ++i) {
			assertEquals(i, numbers.get(i));
		}
	}
	
	
	@Test
	void testResizeToZeroCapacity() {
		ArrayList <Student> students = getStudentsList();
		students.resize(0);
		assertEquals(0, students.size(), "Size must be 0");
		assertEquals(0, students.capacity(), "Capacity must be 0");
	}
	
	
	@Test
	void testResizeWithNegativeNewCapacity() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		assertThrows(IllegalArgumentException.class, () -> {names.resize(-5);});
		assertEquals(prevCapacity, names.capacity(), "Capacity must be the same");
		assertTrue(checkIfNamesAreOK(names), "Nothing must have changed");
	}
	
	
	@Test
	void testResizeWithTooLargeNewCapacity() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		try {
			names.resize(Integer.MAX_VALUE);
		} catch (OutOfMemoryError e) {
			assertEquals(prevCapacity, names.capacity(), "Capacity must be the same.");
			assertTrue(checkIfNamesAreOK(names), "Nothing must have changed.");
		}
	}
	
	
/**************************Shrink_to_fit tests******************************/
	
	@Test
	void testShrinkToFitWithPrimitiveType() {
		ArrayList <Integer> numbers = new ArrayList<>();
		int elementsCount = ArrayList.INITIAL_CAPACITY * 2 + 3;
		for (int i=0; i<elementsCount; ++i) {
			numbers.add(i);
		}
		numbers.shrink_to_fit();
		assertEquals(numbers.size(), numbers.capacity(), "Capacity must be equal to the size");
		for (int i=0; i<elementsCount; ++i) {
			assertEquals(i, numbers.get(i), "Data must be preserved");
		}
	}
	
	
	@Test
	void testShrinkToFitWithNonPrimitiveType() {
		ArrayList <String> names = getNamesList();
		names.shrink_to_fit();
		assertEquals(names.capacity(), names.size(), "Capacity must be equal to the size");
		assertTrue(checkIfNamesAreOK(names), "Data must be preserved");
	}
	
	
/*****************************Add tests********************************/
	
	@Test
	void testAddWithEmptyArray() {
		ArrayList <Student> students = new ArrayList<>();
		Student st = new Student("Radoslav", 82154, 5.99);
		students.add(st);
		assertEquals(1, students.size(), "After adding one element size must be 1.");
		assertEquals(st, students.get(students.size() - 1));
	}
	
	
	@Test
	void testAddWithNonEmptyArray() {
		ArrayList <String> names = getNamesList();
		int prevSize = names.size();
		names.add("Ivon");
		assertEquals(prevSize + 1, names.size());
		assertTrue(names.get(names.size() - 1).equals("Ivon"), "The new element must be appended.");
	}
	
	
	@Test
	void testAddWithChangingCapacity() {
		ArrayList <Student> students = getStudentsList();
		students.shrink_to_fit(); // Making the capacity equal to the size
		int prevCapacity = students.capacity();
		Student newStudent = new Student("Hristo Petrov", 42010, 5.82); 
		students.add(newStudent);
		assertEquals(students.capacity(), (int)Math.ceil(prevCapacity * ArrayList.GROWING_CONSTANT));
		assertEquals(prevCapacity + 1, students.size());
		assertEquals(newStudent, students.get(prevCapacity), "New Student must be appended in $prevCapacity index");
	}
	
	
	@Test
	void testAddingTooManyElements() {
		ArrayList <Integer> numbers = new ArrayList<>();
		int i = 0;
		while (i >= 0) {
			int currentSize = numbers.size();
			int currentCapacity = numbers.capacity();
			try {
				numbers.add(i);
			} catch (OutOfMemoryError e) {
				assertEquals(currentSize, numbers.size());
				assertEquals(currentCapacity, numbers.capacity());
				for (int j=0; j<currentSize; ++j) {
					assertEquals(j, numbers.get(j));
				}
				break;
			}
			++i;
		}
	}
	

/*****************************Remove tests********************************/	
	
	@Test
	void testRemoveFromNonEmptyArray() {
		ArrayList <String> names = getNamesList();
		names.remove();
		assertEquals(globalNames.length - 1, names.size(), "After removal size must 1 less.");
		for (int i=0; i<globalNames.length - 1; ++i) {
			assertTrue(names.get(i).equals(globalNames[i]));
		}
	}
	
	
	@Test
	void testRemoveFromEmptyArray() {
		ArrayList <String> names = new ArrayList<>();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {names.remove();});
		assertTrue(names.empty());
		assertEquals(ArrayList.INITIAL_CAPACITY, names.capacity(), "Nothing should have changed.");
	}
	

/******************************Get tests*********************************/		
	
	@Test
	void testLegalGet() {
		ArrayList <Integer> list = new ArrayList<>();
		list.add(1);
		list.add(-1);
		list.add(5);
		assertEquals(1, list.get(0), "First element must be 1");
		assertEquals(-1, list.get(1), "Second element must be -1");
		assertEquals(5, list.get(2), "Third element must be 5");
	}
	
	
	@Test
	void testIllegalGet() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		assertThrows(IndexOutOfBoundsException.class, () -> {names.get(names.size() + 1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {names.get(-2);});
		
		assertEquals(prevCapacity, names.capacity(), "Capacity must be the same.");
		assertTrue(checkIfNamesAreOK(names), "Nothing should have changed.");
	}
	

/******************************Set tests*********************************/
	
	@Test
	void testLegalSet() {
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
	void testIllegalSet() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		assertThrows(IndexOutOfBoundsException.class, () -> {names.set(names.size() + 1, "Florian");});
		assertThrows(IndexOutOfBoundsException.class, () -> {names.set(-2, "Florian");});
		
		assertEquals(prevCapacity, names.capacity(), "Capacity must be the same.");
		assertTrue(checkIfNamesAreOK(names), "Nothing should have changed.");
	}


/***************************Concatenate tests******************************/	
	
	@Test
	void testConcatenateWithNullReference() {
		ArrayList <String> names = getNamesList();
		int prevCapacity = names.capacity();
		assertThrows(NullPointerException.class, () -> {names.concatenate(null);});

		assertEquals(prevCapacity, names.capacity(), "Capacity must be the same.");
		assertTrue(checkIfNamesAreOK(names), "Nothing should have changed.");
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
		
		assertEquals(200, list2.size(), "List2's size should not be changed");
		for (int i=800; i<1000; ++i) {
			assertEquals(i, list2.get(i-800), "Nothing should be changed");
		}
	}
	
	
/******************************Other tests*********************************/
	
	@Test
	void testArrayWithZeroCapacity() {
		ArrayList <Double> numbers = new ArrayList<>(0);
		assertEquals(0, numbers.size(), "Size must be 0");
		assertEquals(0, numbers.capacity(), "Capacity must be 0");
		
		numbers.add(9.4);
		assertEquals(1, numbers.size(), "There must not be problem adding an element");
		assertEquals(1, numbers.capacity());
	}
}
