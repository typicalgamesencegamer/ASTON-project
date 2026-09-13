package com.aston.project.app.utils.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomSortTest {

    @Test
    void sortsIntegersAscending() {
        List<Integer> numbers = List.of(5, 3, 8, 1, 9, 2);
        List<Integer> sorted = CustomSort.merge(numbers, Comparator.naturalOrder());
        assertEquals(List.of(1, 2, 3, 5, 8, 9), sorted);
    }

    @Test
    void sortsIntegersDescendingWithCustomComparator() {
        List<Integer> numbers = List.of(5, 3, 8, 1, 9, 2);
        List<Integer> sorted = CustomSort.merge(numbers, Comparator.reverseOrder());
        assertEquals(List.of(9, 8, 5, 3, 2, 1), sorted);
    }

    @Test
    void sortsStringsByLength() {
        List<String> words = List.of("вишня", "аб", "груша", "б");
        List<String> sorted = CustomSort.merge(words, Comparator.comparingInt(String::length));
        assertEquals(List.of("б", "аб", "вишня", "груша"), sorted);
    }

    @Test
    void emptyListReturnsEmptyList() {
        List<Integer> list = new ArrayList<>();
        List<Integer> sorted = CustomSort.merge(list, Comparator.naturalOrder());
        assertTrue(sorted.isEmpty());
    }

    @Test
    void singleElementListIsUnchanged() {
        List<Integer> sorted = CustomSort.merge(List.of(42), Comparator.naturalOrder());
        assertEquals(List.of(42), sorted);
    }

    @Test
    void originalListIsNotModified() {
        List<Integer> numbers = List.of(3, 1, 2);
        CustomSort.merge(numbers, Comparator.naturalOrder());
        assertEquals(List.of(3, 1, 2), numbers);
    }

    @Test
    void sortIsStableForEqualKeys() {
        List<String> words = List.of("бб", "аа", "с");
        List<String> sorted = CustomSort.merge(words, Comparator.comparingInt(String::length));
        assertEquals(List.of("с", "бб", "аа"), sorted);
    }
}
