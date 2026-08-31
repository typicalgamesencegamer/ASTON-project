package com.aston.project;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.strategy.api.ReadStrategy;
import com.aston.project.app.strategy.impl.JsonReader;

import java.util.List;

public class Main {
    static void main() {
        List<Student> students;
        ReadStrategy ss = new JsonReader();
        students = ss.read();
        students.stream().forEach(student -> System.out.print(student));
    }
}
