package com.aston.project.app.utils.multithreading;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElementOccurrenceCounterTest extends TestCase {

    public void testCountsOccurrencesAcrossSeveralThreads() {
        List<String> elements = Arrays.asList("a", "b", "a", "c", "a", "b", "a");

        assertEquals(4, ElementOccurrenceCounter.countOccurrences(elements, "a", 3));
        assertEquals(2, ElementOccurrenceCounter.countOccurrences(elements, "b", 20));
        assertEquals(0, ElementOccurrenceCounter.countOccurrences(elements, "x", 2));
    }

    public void testSupportsNullElements() {
        List<String> elements = Arrays.asList(null, "a", null, "b");

        assertEquals(2, ElementOccurrenceCounter.countOccurrences(elements, null, 2));
    }

    public void testEmptyCollectionDoesNotCreateWorkers() {
        assertEquals(0, ElementOccurrenceCounter.countOccurrences(
                Collections.emptyList(), "element", 4));
    }

    public void testRejectsInvalidThreadCount() {
        try {
            ElementOccurrenceCounter.countOccurrences(List.of(1, 2, 3), 1, 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
        }
    }
}
