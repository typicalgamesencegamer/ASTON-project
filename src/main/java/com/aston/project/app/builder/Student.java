package com.aston.project.app.builder;

import java.util.Objects;

public class Student {
    private final int groupNumber;
    private final int studentId;
    private final double averageGrade;

    public Student(StudentBuilder builder) {
        Objects.requireNonNull(builder, "StudentBuilder не должен быть null");

        this.groupNumber = builder.getGroupNumber();
        this.studentId = builder.getStudentId();
        this.averageGrade = builder.getAverageGrade();
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", studentId=" + studentId +
                ", averageGrade=" + averageGrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return groupNumber == student.groupNumber &&
                studentId == student.studentId &&
                Double.compare(student.averageGrade, averageGrade) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, studentId, averageGrade);
    }
}
