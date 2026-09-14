package com.aston.project.app.strategy.impl;

import com.aston.project.app.exception.ErrorCode;
import com.aston.project.app.exception.FillStrategyException;
import com.aston.project.app.strategy.api.FillStrategy;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.aston.project.app.builder.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonFillStrategy implements FillStrategy {
    private final String PATH;

    public JsonFillStrategy(String path) {
        this.PATH = path;
    }

    public CustomArrayList<Student> fill(int length) {
        InputStream res = getClass().getResourceAsStream(PATH);

        if (res == null) {
            throw new FillStrategyException(ErrorCode.FILE_MISSING);
        }

        try {
            String jsonContent = new String(res.readAllBytes(), StandardCharsets.UTF_8);

            if (jsonContent.isEmpty()) {
                throw new FillStrategyException(ErrorCode.FILE_EMPTY);
            }

            Gson gson = new GsonBuilder().create();
            Type studentListType = new TypeToken<List<Student>>() {
            }.getType();

            List<Student> students = null;

            try {
                students = gson.fromJson(jsonContent, studentListType);
            } catch (Exception e) {
                throw new FillStrategyException(ErrorCode.MALFORMED_JSON);
            }

            if (students == null) {
                throw new FillStrategyException(ErrorCode.LIST_NULL);
            }

            CustomArrayList<Student> result = new CustomArrayList<>();

            if (length == 0) {
                students.stream()
                        .filter(student -> student != null)
                        .forEach(result::add);
            } else {
                students.stream()
                        .filter(student -> student != null)
                        .limit(length)
                        .forEach(result::add);
            }

            if (result.isEmpty()) {
                throw new FillStrategyException(ErrorCode.EMPTY_LIST);
            }

            return result;

        } catch (IOException e) {
            throw new FillStrategyException(ErrorCode.FILE_ERROR);
        }
    }
}
