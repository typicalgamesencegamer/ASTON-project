package com.aston.project.app.strategy.api;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.utils.customcollections.CustomArrayList;

public interface FillStrategy {

    CustomArrayList<Student> fill(int length);
}