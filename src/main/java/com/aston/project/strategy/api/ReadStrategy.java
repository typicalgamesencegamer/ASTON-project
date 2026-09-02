package com.aston.project.strategy.api;

import java.util.List;

import com.aston.project.builder.Student;

public interface ReadStrategy {
    public List<Student> read();
}
