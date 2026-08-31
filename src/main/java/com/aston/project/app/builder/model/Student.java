package com.aston.project.app.builder.model;

public class Student {
    private String groupNum;
    private Float avgGrade;
    private Integer recBookNum;
    public Student(String groupNum, Float avgGrade, Integer recBookNum){
        this.groupNum = groupNum;
        this.avgGrade = avgGrade;
        this.recBookNum = recBookNum;
    }

    @Override
    public String toString() {
        return "Student{groupNum='" + groupNum + "', avgGrade=" + avgGrade + "', recBookNum=" + recBookNum + "}\n";
    }
}
