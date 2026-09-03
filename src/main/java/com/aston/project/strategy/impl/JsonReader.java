package com.aston.project.strategy.impl;

import com.aston.project.strategy.api.ReadStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.aston.project.builder.Student;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonReader implements ReadStrategy {
    private String path;
    public JsonReader(String path){
        this.path = path;
    }
    public List<Student> read() {
        InputStream res = getClass().getResourceAsStream(path);
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
