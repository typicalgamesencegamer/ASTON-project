package com.aston.project.app.builder;

import java.util.Objects;
import java.util.Random;

public class StudentBuilder {
    protected int groupNumber;
    protected Integer studentId = null;
    protected double averageGrade;
    private double minAverageGrade = 0.0;
    private double maxAverageGrade = 100.0;

    private boolean groupNumberSet = false;
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

    public Integer getStudentId() {
         return this.studentId;
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

        int idToAssign;

        if (this.studentId != null) {
            if (this.studentId <= 0) {
                throw new IllegalStateException("Номер зачетной книжки должен быть положительным.");
            }
            this.studentId = null;
        }
        else {
            Random random = new Random();
            this.studentId = random.nextInt(1000) + 1;
            System.out.println("Сгенерирован случайный Student ID: " + this.studentId);
        }
        return new Student(this);
    }
}
