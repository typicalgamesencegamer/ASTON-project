package com.aston.project.app.utils.sort;

import com.aston.project.app.builder.StudentComparators;
import com.aston.project.app.builder.model.Student;
import com.aston.project.app.utils.customcollections.CustomArrayList;

import java.util.Comparator;
import java.util.List;

public class CustomAdditionalSort {

    private CustomAdditionalSort(CustomArrayList<Student> students) {
    }

    public static List<Student> sort(CustomArrayList<Student> data, Comparator<Student> comparator) {
        CustomArrayList<Student> temporaryList = new CustomArrayList<>();
        CustomArrayList<Student> students = new CustomArrayList<>();
        students.copyof(data);

        if (comparator == StudentComparators.COMPARE_BY_GROUP_NUMBER) {
            for (Student student : students) {
                if (student.getGroupNumber() % 2 == 0) {
                    temporaryList.add(student);
                }
            }
            List<Student> sortedTemp = CustomSort.merge(temporaryList, comparator);
            for (int i = 0, j = 0; i < students.size(); i++) {
                if (students.get(i).getGroupNumber() % 2 == 0) {
                    students.set(i, sortedTemp.get(j));
                    j++;
                }
            }
        }
        if (comparator == StudentComparators.COMPARE_BY_STUDENT_ID) {
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
        }
        if (comparator == StudentComparators.COMPARE_BY_AVERAGE_GRADE) {
            for (Student student : students) {
                if ((student.getAverageGrade() * 100) % 2 == 0) {
                    temporaryList.add(student);
                }
            }
            List<Student> sortedTemp = CustomSort.merge(temporaryList, comparator);
            for (int i = 0, j = 0; i < students.size(); i++) {
                if ((students.get(i).getAverageGrade() * 100) % 2 == 0) {
                    students.set(i, sortedTemp.get(j));
                    j++;
                }
            }
        }
        return students;
    }
}
