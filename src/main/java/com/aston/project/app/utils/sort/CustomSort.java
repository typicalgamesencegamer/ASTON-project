package com.aston.project.app.utils.sort;

import com.aston.project.app.builder.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomSort {

    public CustomSort() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> List<T> merge(List<T> list, Comparator<? super T> comparator) {
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        int mid = list.size() / 2;
        List<T> left = merge(list.subList(0, mid), comparator);
        List<T> right = merge(list.subList(mid, list.size()), comparator);

        return mergeSort(left, right, comparator);
    }

    private static <T> List<T> mergeSort(List<T> a, List<T> b, Comparator<? super T> comparator) {

        List<T> result = new ArrayList<>(a.size() + b.size());
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (comparator.compare(a.get(i), b.get(j)) <= 0) {
                result.add(a.get(i));
                i++;
            } else {
                result.add(b.get(j));
                j++;
            }
        }
        while (i < a.size()) {
            result.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            result.add(b.get(j));
            j++;
        }

        return result;
    }
}
