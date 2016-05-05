// Ordered List implementation
// Shawn Seymour
// CSci 4651

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTests {
	
	@Test
	public void testCreation() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
	}
	
	@Test
	public void testAdd() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(5);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testOrderedAdd() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(1);
		list.add(4);
		list.add(2);
		list.add(4);
		list.add(5);
		list.add(4);
		list.add(3);
		assertEquals(7, list.size());
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 2, list.get(1));
		assertEquals((Integer) 3, list.get(2));
		assertEquals((Integer) 4, list.get(3));
		assertEquals((Integer) 4, list.get(4));
		assertEquals((Integer) 4, list.get(5));
		assertEquals((Integer) 5, list.get(6));
	}
	
	@Test
	public void testGet() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(5);
		assertEquals(1, list.size());
		assertEquals((Integer) 5, list.get(0));
	}
	
	@Test
	public void testIsEmpty() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		assertEquals(true, list.isEmpty());
		
		list.add(5);
		assertEquals(false, list.isEmpty());
	}
	
	@Test
	public void testMap() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(5);
		assertEquals(1, list.size());
		assertEquals((Integer) 5, list.get(0));
		
		list.add(3);
		assertEquals(2, list.size());
		assertEquals((Integer) 3, list.get(0));
		assertEquals((Integer) 5, list.get(1));
		
		list.map((Integer n) -> (n * 2));
		assertEquals((Integer) 6, list.get(0));
		assertEquals((Integer) 10, list.get(1));
	}
	
	@Test
	public void testAddOneMap() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(1);
		list.add(4);
		list.add(2);
		list.add(3);
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 2, list.get(1));
		assertEquals((Integer) 3, list.get(2));
		assertEquals((Integer) 4, list.get(3));
		
		list.map((Integer n) -> (n + 1));
		assertEquals((Integer) 2, list.get(0));
		assertEquals((Integer) 3, list.get(1));
		assertEquals((Integer) 4, list.get(2));
		assertEquals((Integer) 5, list.get(3));
	}

	@Test
	public void testAddTwoMap() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(4);
		list.add(3);
		list.add(1);
		list.add(2);
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 2, list.get(1));
		assertEquals((Integer) 3, list.get(2));
		assertEquals((Integer) 4, list.get(3));
		
		list.map((Integer n) -> (n * n));
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 4, list.get(1));
		assertEquals((Integer) 9, list.get(2));
		assertEquals((Integer) 16, list.get(3));
	}
	
	@Test
	public void testStringMap() {
		OrderedList<String> list = new OrderedList<>();
		list.add("Hello");
		list.add("HELLO");
		list.add("HeLlO");
		list.add("Testing A Long String with CAPITALS");
		assertEquals(4, list.size());
		
		list.map((String s) -> (s.toLowerCase()));
		assertEquals("hello", list.get(0));
		assertEquals("hello", list.get(1));
		assertEquals("hello", list.get(2));
		assertEquals("testing a long string with capitals", list.get(3));
	}
	
	@Test
	public void testFilterOne() {
		OrderedList<Integer> list = new OrderedList<>();
		assertEquals(0, list.size());
		
		list.add(8);
		assertEquals(1, list.size());
		assertEquals((Integer) 8, list.get(0));
		
		list.filter((Integer n) -> (n % 2 == 0));
		assertEquals(1, list.size());
		assertEquals((Integer) 8, list.get(0));
		
		list.filter((Integer n) -> (n % 2 != 0));
		assertEquals(0, list.size());
		assertEquals(null, list.get(0));
		
		list.add(5);
		assertEquals(1, list.size());
		assertEquals((Integer) 5, list.get(0));
		
		list.add(8);
		assertEquals(2, list.size());
		assertEquals((Integer) 8, list.get(1));

		
		list.filter((Integer n) -> (n % 2 != 0));
		assertEquals(1, list.size());
		assertEquals((Integer) 5, list.get(0));
		assertEquals(null, list.get(1));
		
		
	}
	
	@Test
	public void testFilterLong() {
		OrderedList<Integer> list = new OrderedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(6, list.size());
		
		list.filter((Integer n) -> (n % 2 == 0));
		assertEquals(3, list.size());
		assertEquals((Integer) 2, list.get(0));
		assertEquals((Integer) 4, list.get(1));
		assertEquals((Integer) 6, list.get(2));
	}
	
	@Test
	public void testFilterLonger() {
		OrderedList<Integer> list = new OrderedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		list.add(7);
		list.add(7);
		list.add(8);
		assertEquals(10, list.size());
		
		list.filter((Integer n) -> (n % 2 != 0));
		assertEquals(5, list.size());
		assertEquals((Integer) 1, list.get(0));
		assertEquals((Integer) 3, list.get(1));
		assertEquals((Integer) 5, list.get(2));
		assertEquals((Integer) 7, list.get(3));
		assertEquals((Integer) 7, list.get(4));
		assertEquals(null, list.get(5));
	}
	
	@Test
	public void testFilterLonger2() {
		OrderedList<Integer> list = new OrderedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		list.add(7);
		list.add(7);
		list.add(8);
		assertEquals(10, list.size());
		
		list.filter((Integer n) -> (n % 2 == 0));
		assertEquals(5, list.size());
		assertEquals((Integer) 2, list.get(0));
		assertEquals((Integer) 4, list.get(1));
		assertEquals((Integer) 6, list.get(2));
		assertEquals((Integer) 6, list.get(3));
		assertEquals((Integer) 8, list.get(4));
		assertEquals(null, list.get(5));
	}
	
	@Test
	public void testEmptyResult() {
		OrderedList<Integer> list = new OrderedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		list.add(7);
		list.add(7);
		list.add(8);
		assertEquals(10, list.size());
		
		list.filter((Integer n) -> (n == 0));
		assertEquals(true, list.isEmpty());
		assertEquals(0, list.size());
		assertEquals(null, list.get(0));
	}
	
	@Test
	public void testEverything() {
		OrderedList<String> list = new OrderedList<>();
		list.add("a");
		list.add("b");
		list.add("a");
		list.add("d");
		list.add("c");
		list.add("z");
		
		assertEquals(6, list.size());
		assertEquals("a", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("c", list.get(3));
		assertEquals("d", list.get(4));
		assertEquals("z", list.get(5));
		assertEquals(null, list.get(6));

	}
	

}
