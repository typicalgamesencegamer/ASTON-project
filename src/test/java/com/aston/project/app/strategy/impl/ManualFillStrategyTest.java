package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ManualFillStrategyTest {

    @Test
    void fill_shouldCreateStudentsFromUserInput() {
        Scanner scanner = new Scanner(
                "101 1001 4.5 " +
                "102 1002 3.8"
        );

        ManualFillStrategy strategy =
                new ManualFillStrategy(scanner);

        CustomArrayList<Student> students =
                strategy.fill(2);

        assertEquals(2, students.size());

        Student first = students.get(0);

        assertEquals(101, first.getGroupNumber());
        assertEquals(1001, first.getStudentId());
        assertEquals(4.5, first.getAverageGrade());

        Student second = students.get(1);

        assertEquals(102, second.getGroupNumber());
        assertEquals(1002, second.getStudentId());
        assertEquals(3.8, second.getAverageGrade());
    }

    @Test
    void fill_shouldAcceptCommaAsDecimalSeparator() {
        Scanner scanner = new Scanner(
                "101 1001 4,75"
        );

        ManualFillStrategy strategy =
                new ManualFillStrategy(scanner);

        CustomArrayList<Student> students =
                strategy.fill(1);

        assertEquals(4.75, students.get(0).getAverageGrade());
    }

    @Test
    void fill_shouldRetryAfterInvalidInput() {
        Scanner scanner = new Scanner(
                "abc -10 101 " +
                "xyz -5 1001 " +
                "wrong 7.0 4.5"
        );

        ManualFillStrategy strategy =
                new ManualFillStrategy(scanner);

        CustomArrayList<Student> students =
                strategy.fill(1);

        Student student = students.get(0);

        assertEquals(101, student.getGroupNumber());
        assertEquals(1001, student.getStudentId());
        assertEquals(4.5, student.getAverageGrade());
    }

    @Test
    void fill_shouldThrowException_whenLengthIsZero() {
        ManualFillStrategy strategy =
                new ManualFillStrategy(new Scanner(""));

        assertThrows(
                IllegalArgumentException.class,
                () -> strategy.fill(0)
        );
    }

    @Test
    void fill_shouldThrowException_whenLengthIsNegative() {
        ManualFillStrategy strategy =
                new ManualFillStrategy(new Scanner(""));

        assertThrows(
                IllegalArgumentException.class,
                () -> strategy.fill(-1)
        );
    }

    @Test
    void constructor_shouldThrowException_whenScannerIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new ManualFillStrategy(null)
        );
    }
}