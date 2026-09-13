package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomFillStrategyTest {

    @Test
    void fill_shouldCreateRequiredNumberOfStudents() {
        RandomFillStrategy strategy = new RandomFillStrategy();

        CustomArrayList<Student> students = strategy.fill(10);

        assertNotNull(students);
        assertEquals(10, students.size());
    }

    @Test
    void fill_shouldCreateStudentsWithValidValues() {
        RandomFillStrategy strategy = new RandomFillStrategy();

        CustomArrayList<Student> students = strategy.fill(100);

        for (Student student : students) {
            assertTrue(student.getGroupNumber() >= 1);
            assertTrue(student.getGroupNumber() <= 999);

            assertTrue(student.getStudentId() >= 1);
            assertTrue(student.getStudentId() <= 99999);

            assertTrue(student.getAverageGrade() >= 0.0);
            assertTrue(student.getAverageGrade() <= 5.0);
        }
    }

    @Test
    void fill_shouldReturnCustomArrayList() {
        RandomFillStrategy strategy = new RandomFillStrategy();

        CustomArrayList<Student> students = strategy.fill(5);

        assertInstanceOf(CustomArrayList.class, students);
    }

    @Test
    void fill_shouldThrowException_whenLengthIsZero() {
        RandomFillStrategy strategy = new RandomFillStrategy();

        assertThrows(
                IllegalArgumentException.class,
                () -> strategy.fill(0)
        );
    }

    @Test
    void fill_shouldThrowException_whenLengthIsNegative() {
        RandomFillStrategy strategy = new RandomFillStrategy();

        assertThrows(
                IllegalArgumentException.class,
                () -> strategy.fill(-1)
        );
    }
}