package com.aston.project.app.builder;

import java.util.Random;

public class StudentBuilder {
    protected int groupNumber;
    protected Integer studentId = null;
    protected double averageGrade;
    private double minAverageGrade = 0.0;
    private double maxAverageGrade = 100.0;

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
        if (averageGrade < minAverageGrade || averageGrade > maxAverageGrade) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне от " + minAverageGrade + "до " + maxAverageGrade);
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
        if (!averageGradeSet) {
            throw new IllegalStateException("Средний балл не был установлен.");
        }
        int studentIdToUse;
        if (this.studentId == null) {
            Random random = new Random();
            studentIdToUse = random.nextInt(1000) + 1;
            this.studentId = studentIdToUse;
            System.out.println("Сгенерирован случайный Student ID: " + this.studentId);
        } else if (this.studentId <= 0) {
            throw new IllegalStateException("Номер зачетной книжки (studentId) должен быть положительным.");
        } else {
            studentIdToUse = this.studentId;
        }
        return new Student(this);
    }
}
