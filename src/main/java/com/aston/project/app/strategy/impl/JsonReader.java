package com.aston.project.app.strategy.impl;

import com.aston.project.app.strategy.api.ReadStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.aston.project.app.builder.model.Student;

public class JsonReader implements ReadStrategy {
    public List<Student> read() {
        InputStream res = getClass().getResourceAsStream("/students.txt");
        if (res != null) {
            try {
                String jsonContent = new String(res.readAllBytes(), StandardCharsets.UTF_8);
                Gson gson = new GsonBuilder().create();
                Type studentListType = new TypeToken<List<Student>>() {
                }.getType();
                List<Student> students = gson.fromJson(jsonContent, studentListType);
                return students;
            } catch (IOException e) {
                System.out.println("File error");
                return null;
            }
        } else {
            System.out.println("File is missing");
            return null;
        }
    }
}
