package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.exception.ErrorCode;
import com.aston.project.app.exception.FillStrategyException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void read_shouldReturnListOfStudents_whenFileExists() {
        // given
        JsonFillStrategy reader = new JsonFillStrategy("/testStudents.json");

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
    void fill_shouldThrowExceptionWithFileMissingCode_whenFileNotFound() {
        JsonFillStrategy reader = new JsonFillStrategy("/wrongPath.json");

        FillStrategyException exception = assertThrows(
                FillStrategyException.class,
                () -> reader.fill(0)
        );

        assertEquals(ErrorCode.FILE_MISSING, exception.getErrorCode());
    }

    @Test
    void read_shouldPrintMessage_whenFileIsEmpty() {
        JsonFillStrategy reader = new JsonFillStrategy("/emptyFile.json");

        FillStrategyException exception = assertThrows(
                FillStrategyException.class,
                () -> reader.fill(0)
        );

        assertEquals(ErrorCode.FILE_EMPTY, exception.getErrorCode());
    }

    @Test
    void read_shouldPrintMessage_whenStudentListIsNull() {
        JsonFillStrategy reader = new JsonFillStrategy("/nullList.json");

        FillStrategyException exception = assertThrows(
                FillStrategyException.class,
                () -> reader.fill(0)
        );

        assertEquals(ErrorCode.LIST_NULL, exception.getErrorCode());
    }

    @Test
    void read_shouldPrintMessage_whenFileIsMalformed() {
        JsonFillStrategy reader = new JsonFillStrategy("/malformed.json");

        FillStrategyException exception = assertThrows(
                FillStrategyException.class,
                () -> reader.fill(0)
        );

        assertEquals(ErrorCode.MALFORMED_JSON, exception.getErrorCode());
    }

    @Test
    void read_shouldPrintMessage_whenResultIsEmpty() {
        JsonFillStrategy reader = new JsonFillStrategy("/emptyList.json");

        FillStrategyException exception = assertThrows(
                FillStrategyException.class,
                () -> reader.fill(0)
        );

        assertEquals(ErrorCode.EMPTY_LIST, exception.getErrorCode());
    }
}