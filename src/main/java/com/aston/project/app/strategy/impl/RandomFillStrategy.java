package com.aston.project.app.strategy.impl;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.builder.impl.StudentBuilder;
import com.aston.project.app.strategy.api.ReadStrategy;
import com.aston.project.app.utils.customcollections.CustomArrayList;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomFillStrategy implements ReadStrategy {

    private static final int MIN_GROUP_NUMBER = 1;
    private static final int MAX_GROUP_NUMBER = 999;

    private static final int MIN_STUDENT_ID = 1;
    private static final int MAX_STUDENT_ID = 99999;

    private static final double MIN_AVERAGE_GRADE = 0.0;
    private static final double MAX_AVERAGE_GRADE = 5.0;

    @Override
    public CustomArrayList<Student> fill(int length) {
        validateLength(length);

        return IntStream.range(0, length)
                .mapToObj(index -> createRandomStudent())
                .collect(
                        CustomArrayList::new,
                        CustomArrayList::add,
                        CustomArrayList::addAll
                );
    }

    private Student createRandomStudent() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int groupNumber = random.nextInt(
                MIN_GROUP_NUMBER,
                MAX_GROUP_NUMBER + 1
        );

        int studentId = random.nextInt(
                MIN_STUDENT_ID,
                MAX_STUDENT_ID + 1
        );

        double averageGrade = Math.round(
                random.nextDouble(
                        MIN_AVERAGE_GRADE,
                        MAX_AVERAGE_GRADE
                ) * 100.0
        ) / 100.0;

        return new StudentBuilder()
                .setGroupNumber(groupNumber)
                .setStudentId(studentId)
                .setAverageGrade(averageGrade)
                .build();
    }

    private void validateLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException(
                    "Длина коллекции должна быть больше 0."
            );
        }
    }
}
