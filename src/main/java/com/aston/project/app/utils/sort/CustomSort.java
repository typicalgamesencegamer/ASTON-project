package com.aston.project.app.utils.sort;

import com.aston.project.app.builder.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomSort {

    public CustomSort(){
        throw new IllegalStateException("Utility class");
    }

    public static List<Student> merge(List<Student> students, Comparator<Student> comparator){
        Student[] arr = students.toArray(new Student[0]);
        Student[] sorted = mergeSort(arr, comparator);
        return new ArrayList<>(Arrays.asList(sorted));
    }

    private static Student[] mergeSort(Student[] arr, Comparator<Student> comparator) {

        if (arr.length > 1) {
            int l = arr.length / 2;
            Student[] a = mergeSort(Arrays.copyOfRange(arr, 0, l), comparator);
            Student[] b = mergeSort(Arrays.copyOfRange(arr, l, arr.length), comparator);
            Student[] res = new Student[a.length + b.length];
            int
                    i = 0,
                    j = 0,
                    k = 0;

            while (i < a.length && j < b.length) {
                if (comparator.compare(a[i],b[j]) <=0) {
                    res[k] = a[i];
                    i++;
                } else {
                    res[k] = b[j];
                    j++;
                }
                k++;
            }
            while (i < a.length) {
                res[k] = a[i];
                i++;
                k++;
            }
            while (j < b.length) {
                res[k] = b[j];
                j++;
                k++;
            }

            return res;

        } else {
            return arr;
        }
    }
}
