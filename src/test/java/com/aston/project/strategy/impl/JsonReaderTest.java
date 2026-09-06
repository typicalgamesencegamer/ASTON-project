package com.aston.project.strategy.impl;

import com.aston.project.builder.Student;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void read_shouldReturnListOfStudents_whenFileExists() {
        // given
        JsonReader reader = new JsonReader("/students.txt");

        // when
        List<Student> students = reader.read();

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
        JsonReader reader = new JsonReader("/wrongPath.txt");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // when
        List<Student> students = reader.read();

        // then
        assertNull(students);
        assertTrue(outContent.toString().contains("File is missing"));

        System.setOut(originalOut);
    }
}