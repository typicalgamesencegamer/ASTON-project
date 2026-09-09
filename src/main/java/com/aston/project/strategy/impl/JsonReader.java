package com.aston.project.strategy.impl;

import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.aston.project.strategy.api.ReadStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.aston.project.builder.Student;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonReader implements ReadStrategy {
    private String path;

    public JsonReader(String path) {
        this.path = path;
    }

    public List<Student> read() {
        InputStream res = getClass().getResourceAsStream(path);

        if (res == null) {
            System.out.println("File is missing");
            return null;
        }

        try {
            String jsonContent = new String(res.readAllBytes(), StandardCharsets.UTF_8);

            if (jsonContent.isEmpty()) {
                System.out.println("File is empty");
                return null;
            }

            Gson gson = new GsonBuilder().create();
            Type studentListType = new TypeToken<List<Student>>() {}.getType();

            List<Student> students = null;

            try {
                students = gson.fromJson(jsonContent, studentListType);
            } catch (Exception e) {
                System.out.println("File is malformed");
                return null;
            }

            if (students == null) {
                System.out.println("Student list is null");
                return null;
            }

            CustomArrayList<Student> result = new CustomArrayList<>();

            students.stream()
                    .filter(student -> student != null)
                    .forEach(result::add);

            return result;

        } catch (IOException e) {
            System.out.println("File error");
            return null;
        }
    }
}
