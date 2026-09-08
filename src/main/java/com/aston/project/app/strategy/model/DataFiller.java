package com.aston.project.app.strategy.model;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.strategy.api.FillStrategy;
import com.aston.project.app.utils.customcollections.CustomArrayList;

public class DataFiller {
    private FillStrategy fillStrategy;

    public CustomArrayList<Student> fillData(int length) {
        return fillStrategy.fill(length);
    }

    public void setFillStrategy(FillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }
}
