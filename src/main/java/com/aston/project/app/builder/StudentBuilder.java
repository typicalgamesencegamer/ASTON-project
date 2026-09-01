package com.aston.project.app.builder;

public class StudentBuilder {
    protected int groupNumber;
    protected int studentId;
    protected double averageGrade;
    private double minAverageGrade = 0.0;
    private double maxAverageGrade = 100.0;

    public StudentBuilder setGroupNumber(int groupNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть положительным числом.");
        }
        this.groupNumber = groupNumber;
        return this;
    }

    public StudentBuilder setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть положительным числом.");
        }
        this.studentId = studentId;
        return this;
    }

    public StudentBuilder setAverageGrade(double averageGrade) {
        if (averageGrade < minAverageGrade || averageGrade > maxAverageGrade) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне от " + minAverageGrade + "до " + maxAverageGrade);
        }
        this.averageGrade = averageGrade;
        return this;
    }

    public Student build() {
        return new Student(this);
    }
}
