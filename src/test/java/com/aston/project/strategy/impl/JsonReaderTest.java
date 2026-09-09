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
        JsonReader reader = new JsonReader("/testStudents.txt");

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

    @Test
    void read_shouldPrintMessage_whenFileIsEmpty() {
        JsonReader reader = new JsonReader("/empty.txt");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        List<Student> students = reader.read();

        assertNull(students);
        assertTrue(out.toString().contains("File is empty"));

        System.setOut(System.out);
    }

    @Test
    void read_shouldPrintMessage_whenStudentListIsNull() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        JsonReader reader = new JsonReader("/nullList.txt");
        List<Student> students = reader.read();

        assertNull(students);
        assertTrue(out.toString().contains("Student list is null"));

        System.setOut(System.out);
    }

    @Test
    void read_shouldPrintMessage_whenFileIsMalformed() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        JsonReader reader = new JsonReader("/malformed.txt");
        List<Student> students = reader.read();

        assertNull(students);
        assertTrue(out.toString().contains("File is malformed"));

        System.setOut(System.out);
    }
}