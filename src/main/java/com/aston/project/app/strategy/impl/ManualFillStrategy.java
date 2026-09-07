package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.Student;
import com.aston.project.app.builder.StudentBuilder;
import com.aston.project.app.strategy.api.ReadStrategy;
import com.aston.project.app.utils.customcollections.CustomArrayList;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ManualFillStrategy implements ReadStrategy {

    private final Scanner scanner;

    public ManualFillStrategy(Scanner scanner) {
        this.scanner = Objects.requireNonNull(
                scanner,
                "Scanner не должен быть null."
        );
    }

    @Override
    public CustomArrayList<Student> fill(int length) {
        validateLength(length);

        return IntStream.range(0, length)
                .mapToObj(index -> readStudent(index + 1))
                .collect(
                        CustomArrayList::new,
                        CustomArrayList::add,
                        CustomArrayList::addAll
                );
    }

    private Student readStudent(int studentNumber) {
        System.out.println();
        System.out.println("Студент №" + studentNumber);

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

    private int readInt(
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

    private double readDouble(
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

    private void validateLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException(
                    "Длина коллекции должна быть больше 0."
            );
        }
    }
}