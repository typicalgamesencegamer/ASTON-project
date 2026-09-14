package com.aston.project.app.student;

import com.aston.project.app.builder.StudentComparators;
import com.aston.project.app.builder.impl.StudentBuilder;
import com.aston.project.app.builder.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Student")
class StudentTest {

    @Nested
    @DisplayName("Тесты Builder'а и валидации")
    class BuilderAndValidationTests {

        @Test
        @DisplayName("Успешное создание студента через Builder")
        void shouldCreateStudentSuccessfully() {
            Student student = new StudentBuilder()
                    .setGroupNumber(101)
                    .setStudentId(12345)
                    .setAverageGrade(4.7)
                    .build();

            assertEquals(101, student.getGroupNumber());
            assertEquals(12345, student.getStudentId());
            assertEquals(4.7, student.getAverageGrade());
        }

        @Test
        @DisplayName("Ошибка валидации: отрицательный номер группы")
        void shouldThrowWhenGroupNumberIsNegative() {
            assertThrows(IllegalArgumentException.class, () ->
                    new StudentBuilder()
                            .setGroupNumber(-1)
                            .setStudentId(1)
                            .setAverageGrade(80)
                            .build());
        }

        @Test
        @DisplayName("Ошибка валидации: нулевой номер зачётки")
        void shouldThrowWhenStudentIdIsZero() {
            assertThrows(IllegalArgumentException.class, () ->
                    new StudentBuilder()
                            .setGroupNumber(1)
                            .setStudentId(0)
                            .setAverageGrade(80)
                            .build());
        }

        @Test
        @DisplayName("Ошибка валидации: средний балл ниже 0")
        void shouldThrowWhenAverageGradeIsTooLow() {
            assertThrows(IllegalArgumentException.class, () ->
                    new StudentBuilder()
                            .setGroupNumber(1)
                            .setStudentId(1)
                            .setAverageGrade(-10.0)
                            .build());
        }

        @Test
        @DisplayName("Ошибка валидации: средний балл выше 100")
        void shouldThrowWhenAverageGradeIsTooHigh() {
            assertThrows(IllegalArgumentException.class, () ->
                    new StudentBuilder()
                            .setGroupNumber(1)
                            .setStudentId(1)
                            .setAverageGrade(110.0)
                            .build());
        }
    }

    @Nested
    @DisplayName("Тесты компараторов")
    class ComparatorTests {

        private List<Student> createStudents() {
            List<Student> students = new ArrayList<>();
            students.add(new StudentBuilder().setGroupNumber(102).setStudentId(54321).setAverageGrade(5.0).build());
            students.add(new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(4.5).build());
            students.add(new StudentBuilder().setGroupNumber(101).setStudentId(67890).setAverageGrade(3.1).build());
            students.add(new StudentBuilder().setGroupNumber(103).setStudentId(98765).setAverageGrade(2.6).build());
            return students;
        }

        @Test
        @DisplayName("Сортировка по номеру группы")
        void shouldSortByGroupNumber() {
            List<Student> students = createStudents();

            students.sort(StudentComparators.COMPARE_BY_GROUP_NUMBER);

            assertEquals(101, students.get(0).getGroupNumber());
            assertEquals(101, students.get(1).getGroupNumber());
            assertEquals(102, students.get(2).getGroupNumber());
            assertEquals(103, students.get(3).getGroupNumber());
        }

        @Test
        @DisplayName("Сортировка по ID студента")
        void shouldSortByStudentId() {
            List<Student> students = createStudents();

            students.sort(StudentComparators.COMPARE_BY_STUDENT_ID);

            assertEquals(12345, students.get(0).getStudentId());
            assertEquals(54321, students.get(1).getStudentId());
            assertEquals(67890, students.get(2).getStudentId());
            assertEquals(98765, students.get(3).getStudentId());
        }

        @Test
        @DisplayName("Сортировка по среднему баллу")
        void shouldSortByAverageGrade() {
            List<Student> students = createStudents();

            students.sort(StudentComparators.COMPARE_BY_AVERAGE_GRADE);

            assertEquals(2.6, students.get(0).getAverageGrade());
            assertEquals(3.1, students.get(1).getAverageGrade());
            assertEquals(4.5, students.get(2).getAverageGrade());
            assertEquals(5.0, students.get(3).getAverageGrade());
        }
    }

    @Nested
    @DisplayName("Тесты equals() и hashCode()")
    class EqualsAndHashCodeTests {

        @Test
        @DisplayName("equals() возвращает true для той же ссылки")
        void shouldReturnTrueForSameReference() {
            Student s1 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(4.5).build();
            Student s4 = s1;

            assertEquals(s4, s1);
            assertEquals(s4.hashCode(), s1.hashCode());
        }

        @Test
        @DisplayName("equals() возвращает true для равных объектов")
        void shouldReturnTrueForEqualObjects() {
            Student s1 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(3.5).build();
            Student s2 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(3.5).build();

            assertEquals(s2, s1);
            assertEquals(s2.hashCode(), s1.hashCode());
        }

        @Test
        @DisplayName("equals() возвращает false для разных групп")
        void shouldReturnFalseForDifferentGroups() {
            Student s1 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(4.5).build();
            Student s3 = new StudentBuilder().setGroupNumber(102).setStudentId(12345).setAverageGrade(1.5).build();

            assertNotEquals(s3, s1);
        }

        @Test
        @DisplayName("equals() возвращает false для null")
        void shouldReturnFalseForNull() {
            Student s1 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(4.5).build();

            assertNotEquals(null, s1);
        }
    }
}

