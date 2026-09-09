package com.aston.project.app.strategy.impl;

import com.aston.project.app.strategy.api.FillStrategy;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.aston.project.app.builder.model.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonFillStrategy implements FillStrategy {
    private final String PATH;

    public JsonFillStrategy(String path) {
        this.PATH = path;
    }

    public CustomArrayList<Student> fill(int length) {
        try (FileInputStream fis = new FileInputStream(PATH)) {
            try {
                String jsonContent = new String(fis.readAllBytes(), StandardCharsets.UTF_8);
                Gson gson = new GsonBuilder().create();
                Type studentListType = new TypeToken<CustomArrayList<Student>>() {
                }.getType();
                return gson.fromJson(jsonContent, studentListType);
            } catch (IOException e) {
                System.out.println("File error");
                return null;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
