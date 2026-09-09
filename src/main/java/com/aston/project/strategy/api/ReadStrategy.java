package com.aston.project.strategy.api;
import com.aston.project.app.utils.customcollections.CustomArrayList;

import java.util.List;

import com.aston.project.builder.Student;

public interface ReadStrategy {
    public List<Student> read();
}
