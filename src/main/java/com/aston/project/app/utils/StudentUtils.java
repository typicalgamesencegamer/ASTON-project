package com.aston.project.app.utils;

import com.aston.project.app.builder.impl.StudentBuilder;
import com.aston.project.app.builder.model.Student;

import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

public class StudentUtils {
    private static Scanner scanner;

    public static Student askUserForStudent(Scanner scnr) {
        scanner = scnr;
        return readStudent();
    }

    private static Student readStudent() {
        int groupNumber = readInt(
                "Введите номер группы (> 0): ",
                value -> value > 0,
                "Номер группы должен быть положительным числом."
        );

        int studentId = readInt(
                "Введите номер зачетной книжки (> 0): ",
                value -> value > 0,
                "Номер зачетной книжки должен быть положительным числом."
        );

        double averageGrade = readDouble(
                "Введите средний балл (0.0-5.0): ",
                value -> value >= 0.0 && value <= 5.0,
                "Средний балл должен быть в диапазоне от 0 до 5."
        );

        return new StudentBuilder()
                .setGroupNumber(groupNumber)
                .setStudentId(studentId)
                .setAverageGrade(averageGrade)
                .build();
    }

    private static int readInt(
            String prompt,
            IntPredicate validator,
            String validationMessage
    ) {
        while (true) {
            System.out.print(prompt);

            String value = scanner.next();

            try {
                int parsed = Integer.parseInt(value);

                if (validator.test(parsed)) {
                    return parsed;
                }

                System.out.println(validationMessage);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: введите целое число."
                );
            }
        }
    }

    private static double readDouble(
            String prompt,
            DoublePredicate validator,
            String validationMessage
    ) {
        while (true) {
            System.out.print(prompt);

            String value = scanner.next().replace(',', '.');

            try {
                double parsed = Double.parseDouble(value);

                if (Double.isFinite(parsed)
                        && validator.test(parsed)) {
                    return parsed;
                }

                System.out.println(validationMessage);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: введите число."
                );
            }
        }
    }

}
