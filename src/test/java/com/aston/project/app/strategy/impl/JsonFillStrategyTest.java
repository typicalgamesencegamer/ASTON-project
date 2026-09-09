package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.model.Student;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFillStrategyTest {

    @Test
    void read_shouldReturnListOfStudents_whenFileExists() {
        // given
        JsonFillStrategy reader = new JsonFillStrategy("src/test/java/resources/students.json");

        // when
        List<Student> students = reader.fill(0);

        // then
        assertNotNull(students);
        assertFalse(students.isEmpty());
        assertEquals(25, students.size());

        Student first = students.get(0);
        assertEquals(101, first.getGroupNumber());
        assertEquals(1001, first.getStudentId());
        assertEquals(4.5, first.getAverageGrade());
    }

    @Test
    void read_shouldPrintMessageAboutMissingFile() {
        // given
        JsonFillStrategy reader = new JsonFillStrategy("/wrongPath.txt");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // when
        List<Student> students = reader.fill(0);

        // then
        assertNull(students);
        assertTrue(outContent.toString().contains("File not found"));

        System.setOut(originalOut);
    }
}