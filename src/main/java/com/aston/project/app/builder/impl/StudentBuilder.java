package com.aston.project.app.builder.impl;

import com.aston.project.app.builder.model.Student;

public class StudentBuilder {
    protected int groupNumber;
    protected int studentId;
    protected double averageGrade;
    private final double MIN_AVERAGE_GRADE = 0.0;
    private final double MAX_AVERAGE_GRADE = 100.0;

    private boolean groupNumberSet = false;
    private boolean studentIdSet = false;
    private boolean averageGradeSet = false;

    public StudentBuilder setGroupNumber(int groupNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть положительным числом.");
        }
        this.groupNumber = groupNumber;
        this.groupNumberSet = true;
        return this;
    }

    public StudentBuilder setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть положительным числом.");
        }
        this.studentId = studentId;
        this.studentIdSet = true;
        return this;
    }

    public StudentBuilder setAverageGrade(double averageGrade) {
        if (averageGrade < MIN_AVERAGE_GRADE || averageGrade > MAX_AVERAGE_GRADE) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне от " + MIN_AVERAGE_GRADE + "до " + MAX_AVERAGE_GRADE);
        }
        this.averageGrade = averageGrade;
        this.averageGradeSet = true;
        return this;
    }

    public int getGroupNumber() {
        if (!groupNumberSet) throw new IllegalStateException("Group number not set.");
        return groupNumber;
    }

    public int getStudentId() {
        if (!studentIdSet) throw new IllegalStateException("Student ID not set.");
        return studentId;
    }

    public double getAverageGrade() {
        if (!averageGradeSet) throw new IllegalStateException("Average grade not set.");
        return averageGrade;
    }


    public Student build() {
        if (!groupNumberSet) {
            throw new IllegalStateException("Номер группы не был установлен.");
        }
        if (!studentIdSet) {
            throw new IllegalStateException("Номер зачетной книжки не был установлен.");
        }
        if (!averageGradeSet) {
            throw new IllegalStateException("Средний балл не был установлен.");
        }

        return new Student(this);
    }
}
