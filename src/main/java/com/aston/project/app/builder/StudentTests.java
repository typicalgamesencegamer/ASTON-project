package com.aston.project.app.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentTests {
    public static void runTests() {
        System.out.println("--- Запуск тестов для Student ---");
        testBuilderAndValidation();
        testComparators();
        testEqualsAndHashCode();
        System.out.println("--- Тесты завершены ---");
    }

    private static void testBuilderAndValidation() {
        System.out.println("\n--- Тест Builder'а и валидации ---");
        boolean allPassed = true;

        try {
            Student student1 = new StudentBuilder()
                    .setGroupNumber(101)
                    .setStudentId(12345)
                    .setAverageGrade(85.5)
                    .build();
            if (!student1.toString().contains("groupNumber=101") ||
                    !student1.toString().contains("studentId=12345") ||
                    !student1.toString().contains("averageGrade=85.5")) {
                System.out.println("FAIL: Успешное создание не соответствует ожиданиям.");
                allPassed = false;
            }

            else {
                System.out.println("PASS: Успешное создание студента.");
            }

        } catch (Exception e) {
            System.out.println("FAIL: Неожиданная ошибка при успешном создании: " + e.getMessage());
            allPassed = false;
        }

        try {
            new StudentBuilder().setGroupNumber(-1).setStudentId(1).setAverageGrade(80).build();
            System.out.println("FAIL: Ошибка валидации номера группы не сработала.");
            allPassed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Корректно обработана ошибка валидации номера группы: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: Сработало не то исключение при ошибке группы: " + e.getMessage());
            allPassed = false;
        }

        try {
            new StudentBuilder().setGroupNumber(1).setStudentId(0).setAverageGrade(80).build();
            System.out.println("FAIL: Ошибка валидации номера зачетки не сработала.");
            allPassed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Корректно обработана ошибка валидации номера зачетки: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: Сработало не то исключение при ошибке зачетки: " + e.getMessage());
            allPassed = false;
        }

        try {
            new StudentBuilder().setGroupNumber(1).setStudentId(1).setAverageGrade(-10.0).build();
            System.out.println("FAIL: Ошибка валидации среднего балла (низкий) не сработала.");
            allPassed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Корректно обработана ошибка валидации среднего балла (низкий): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: Сработало не то исключение при ошибке среднего балла (низкий): " + e.getMessage());
            allPassed = false;
        }

        try {
            new StudentBuilder().setGroupNumber(1).setStudentId(1).setAverageGrade(110.0).build();
            System.out.println("FAIL: Ошибка валидации среднего балла (высокий) не сработала.");
            allPassed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Корректно обработана ошибка валидации среднего балла (высокий): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: Сработало не то исключение при ошибке среднего балла (высокий): " + e.getMessage());
            allPassed = false;
        }

        if(allPassed) {
            System.out.println("Результат: Все тесты Builder'а и валидации пройдены.");
        } else {
            System.out.println("Результат: Некоторые тесты Builder'а и валидации НЕ пройдены.");
        }
    }

    private static void testComparators() {
        System.out.println("\n--- Тест компараторов ---");
        List<Student> students = new ArrayList<>();
        students.add(new StudentBuilder().setGroupNumber(102).setStudentId(54321).setAverageGrade(75.0).build());
        students.add(new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(85.5).build());
        students.add(new StudentBuilder().setGroupNumber(101).setStudentId(67890).setAverageGrade(92.1).build());
        students.add(new StudentBuilder().setGroupNumber(103).setStudentId(98765).setAverageGrade(75.0).build());

        boolean allPassed = true;

        Collections.sort(students, StudentComparators.COMPARE_BY_GROUP_NUMBER);
        if (students.get(0).getGroupNumber() != 101 || students.get(1).getGroupNumber() != 101 || students.get(2).getGroupNumber() != 102 || students.get(3).getGroupNumber() != 103) {
            System.out.println("FAIL: Сортировка по номеру группы неверна.");
            allPassed = false;
        } else {
            System.out.println("PASS: Сортировка по номеру группы верна.");
        }

        System.out.println("Студенты после сортировки по группе: " + students);

        Collections.sort(students, StudentComparators.COMPARE_BY_STUDENT_ID);
        if (students.get(0).getStudentId() != 12345 || students.get(1).getStudentId() != 54321 || students.get(2).getStudentId() != 67890 || students.get(3).getStudentId() != 98765) {
            System.out.println("FAIL: Сортировка по ID неверна.");
            allPassed = false;
        } else {
            System.out.println("PASS: Сортировка по ID верна.");
        }

        System.out.println("Студенты после сортировки по ID: " + students);

        Collections.sort(students, StudentComparators.COMPARE_BY_AVERAGE_GRADE);

        if (students.get(0).getAverageGrade() != 75.0 || students.get(1).getAverageGrade() != 75.0 || students.get(2).getAverageGrade() != 85.5 || students.get(3).getAverageGrade() != 92.1) {
            System.out.println("FAIL: Сортировка по среднему баллу неверна (первые 2).");
            allPassed = false;
        } else {
            System.out.println("PASS: Сортировка по среднему баллу верна (первые 2).");
        }

        System.out.println("Студенты после сортировки по среднему баллу: " + students);

        if(allPassed) {
            System.out.println("Результат: Все тесты компараторов пройдены.");
        } else {
            System.out.println("Результат: Некоторые тесты компараторов НЕ пройдены.");
        }
    }

    private static void testEqualsAndHashCode() {
        System.out.println("\n--- Тест equals() и hashCode() ---");
        boolean allPassed = true;

        Student s1 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(85.5).build();
        Student s2 = new StudentBuilder().setGroupNumber(101).setStudentId(12345).setAverageGrade(85.5).build();
        Student s3 = new StudentBuilder().setGroupNumber(102).setStudentId(12345).setAverageGrade(85.5).build();
        Student s4 = s1;

        if (!s1.equals(s4)) {
            System.out.println("FAIL: equals() не сработал для той же ссылки.");
            allPassed = false;
        } else {
            System.out.println("PASS: equals() работает для той же ссылки.");
        }

        if (!s1.equals(s2)) {
            System.out.println("FAIL: equals() не сработал для равных объектов.");
            allPassed = false;
        } else {
            System.out.println("PASS: equals() работает для равных объектов.");
        }

        if (s1.equals(s3)) {
            System.out.println("FAIL: equals() сработал для неравных объектов (разная группа).");
            allPassed = false;
        } else {
            System.out.println("PASS: equals() не работает для неравных объектов (разная группа).");
        }

        if (s1.equals(null)) {
            System.out.println("FAIL: equals() сработал для null.");
            allPassed = false;
        } else {
            System.out.println("PASS: equals() не работает для null.");
        }

        if (s1.hashCode() != s4.hashCode()) {
            System.out.println("FAIL: hashCode() не совпадает для той же ссылки.");
            allPassed = false;
        } else {
            System.out.println("PASS: hashCode() совпадает для той же ссылки.");
        }

        if (s1.hashCode() != s2.hashCode()) {
            System.out.println("FAIL: hashCode() не совпадает для равных объектов.");
            allPassed = false;
        } else {
            System.out.println("PASS: hashCode() совпадает для равных объектов.");
        }

        if (s1.hashCode() == s3.hashCode()) {
            System.out.println("FAIL: hashCode() совпадает для неравных объектов (разная группа).");
            allPassed = false;
        } else {
            System.out.println("PASS: hashCode() не совпадает для неравных объектов (разная группа).");
        }

        if(allPassed) {
            System.out.println("Результат: Все тесты equals() и hashCode() пройдены.");
        } else {
            System.out.println("Результат: Некоторые тесты equals() и hashCode() НЕ пройдены.");
        }
    }

    //public static void main(String[] args) {
    //    StudentTests.runTests();
    //}
}
