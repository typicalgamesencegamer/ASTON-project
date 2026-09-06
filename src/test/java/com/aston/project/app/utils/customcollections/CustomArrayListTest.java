package com.aston.project.app.utils.customcollections;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CustomArrayListTest extends TestCase {
    public void testAddAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("first");
        list.add("second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    public void testInsertAndRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(3);

        list.add(1, 2);

        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    public void testSetAndClear() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("old");

        assertEquals("old", list.set(0, "new"));
        assertEquals("new", list.get(0));

        list.clear();
        assertTrue(list.isEmpty());
    }

    public void testGrowsWhenCapacityIsExceeded() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        for (int number = 0; number < 20; number++) {
            list.add(number);
        }

        assertEquals(20, list.size());
        assertEquals(Integer.valueOf(19), list.get(19));
    }

    public void testInvalidIndexThrowsException() {
        CustomArrayList<String> list = new CustomArrayList<>();

        try {
            list.get(0);
            fail("Ожидалось исключение IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
            // Ожидаемое исключение.
        }
    }

    public void testCollectionMethods() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.addAll(Arrays.asList("one", "two", "two", "three"));

        assertTrue(list.contains("two"));
        assertTrue(list.containsAll(Arrays.asList("one", "three")));
        assertEquals(1, list.indexOf("two"));
        assertEquals(2, list.lastIndexOf("two"));
        assertTrue(list.remove("two"));
        assertEquals(1, list.lastIndexOf("two"));
    }

    public void testArrayIteratorAndSubList() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));

        Integer[] values = list.toArray(new Integer[0]);
        assertTrue(Arrays.equals(new Integer[]{1, 2, 3}, values));

        Iterator<Integer> iterator = list.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        iterator.remove();
        assertEquals(Integer.valueOf(2), list.get(0));

        List<Integer> part = list.subList(0, 2);
        part.set(0, 99);
        assertEquals(Integer.valueOf(2), list.get(0));
        assertEquals(Integer.valueOf(99), part.get(0));
    }

    public void testSubListRejectsReversedRange() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));

        try {
            list.subList(2, 1);
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Ожидаемое исключение.
        }
    }
}
