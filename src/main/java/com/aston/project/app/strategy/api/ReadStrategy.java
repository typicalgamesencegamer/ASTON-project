package com.aston.project.app.strategy.api;

import com.aston.project.app.builder.model.Student;
import java.util.List;

public interface ReadStrategy {
    public List<Student> read();
}
