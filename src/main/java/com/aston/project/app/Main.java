package com.aston.project.app;

import com.aston.project.app.core.Program;
import com.aston.project.app.utils.multithreading.ElementOccurrenceCounter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> elements = List.of("Java", "SQL", "Java", "Spring", "Java", "Docker");
        String searchedElement = "Java";
        int occurrences = ElementOccurrenceCounter.countOccurrences(elements, searchedElement, 3);
        System.out.printf("Количество вхождений элемента '%s': %d%n", searchedElement, occurrences);

        Program program = new Program();
        program.start();
    }
}
