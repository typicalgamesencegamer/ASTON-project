package com.aston.project.app.utils.sort;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.utils.customcollections.CustomArrayList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomAdditionalSort {

    private CustomAdditionalSort(CustomArrayList<Student> students) {
    }

    public static List<Student> sort(CustomArrayList<Student> data, Comparator<? super Student> comparator) {
        CustomArrayList<Student> temporaryList = new CustomArrayList<>();
        CustomArrayList<Student> students = new CustomArrayList<>();
        students.copyof(data);

        for (Student student : students) {
            if (student.getStudentId() % 2 == 0) {
                temporaryList.add(student);
            }
        }
        List<Student> sortedTemp = CustomSort.merge(temporaryList, comparator);
        for (int i = 0, j = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() % 2 == 0) {
                students.set(i, sortedTemp.get(j));
                j++;
            }
        }
        return students;
    }
}
