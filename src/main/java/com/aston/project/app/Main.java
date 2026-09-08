package com.aston.project.app;

import com.aston.project.app.builder.StudentComparators;
import com.aston.project.app.builder.model.Student;
import com.aston.project.app.strategy.impl.JsonFillStrategy;
import com.aston.project.app.strategy.impl.ManualFillStrategy;
import com.aston.project.app.strategy.impl.RandomFillStrategy;
import com.aston.project.app.strategy.model.DataFiller;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.aston.project.app.utils.sort.CustomSort;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String JSON_PATH = "src/main/resources/students.json";

    private static CustomArrayList<Student> students = new CustomArrayList<>();
    private static List<Student> sortedStudents = new CustomArrayList<>();

    static void main() {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        DataFiller reader = new DataFiller();

        while (isRunning) {
            System.out.println("Введите q чтобы закрыть программу");
            System.out.println("Введите номер желаемой опции");
            System.out.println("1. Отсортирвать данные из JSON файла");
            System.out.println("2. Отсортирвать рандомные данные");
            System.out.println("3. Отсортирвать введённые данные\n");
            String code = input.next();
            switch (code.toLowerCase()) {
                case "q":
                    System.out.println("Выход из программы");
                    isRunning = false;
                    break;
                case "1":
                    System.out.println("Сортирую данные из JSON файла");
                    reader.setFillStrategy(new JsonFillStrategy(JSON_PATH));
                    students = reader.fillData(0);
                    sortedStudents = CustomSort.merge(students, StudentComparators.COMPARE_BY_STUDENT_ID);
                    for (Student student : sortedStudents) {
                        System.out.println(student);
                    }
                    System.out.println();
                    continue;
                case "2":
                    System.out.println("Генерирую рандомные данные и сортирую их");
                    reader.setFillStrategy(new RandomFillStrategy());
                    students = reader.fillData(10);
                    sortedStudents = CustomSort.merge(students, StudentComparators.COMPARE_BY_GROUP_NUMBER);
                    for (Student student : sortedStudents) {
                        System.out.println(student);
                    }
                    System.out.println();
                    continue;
                case "3":
                    System.out.println("Ручной ввод");
                    reader.setFillStrategy(new ManualFillStrategy(input));
                    students = reader.fillData(10);
                    sortedStudents = CustomSort.merge(students, StudentComparators.COMPARE_BY_AVERAGE_GRADE);
                    for (Student student : sortedStudents) {
                        System.out.println(student);
                    }
                    System.out.println();
                    continue;
                default:
                    System.out.println("Такой опции нет");
            }
        }
        input.close();
        System.out.println("Программа завершила работу");
    }

}
