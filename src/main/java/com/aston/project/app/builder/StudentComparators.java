package com.aston.project.app.builder;

import java.util.Comparator;

public class StudentComparators {
    public static final Comparator<Student> COMPARE_BY_GROUP_NUMBER = Comparator.comparingInt(Student::getGroupNumber);

    public static final Comparator<Student> COMPARE_BY_STUDENT_ID = Comparator.comparingInt(Student::getStudentId);

    public static final Comparator<Student> COMPARE_BY_AVERAGE_GRADE = Comparator.comparingDouble(Student::getAverageGrade);

    private StudentComparators() {
        throw new IllegalStateException("Utility class");
    }
}
