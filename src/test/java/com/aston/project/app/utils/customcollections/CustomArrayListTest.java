package com.aston.project.app.utils.customcollections;

import junit.framework.TestCase;

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
}
