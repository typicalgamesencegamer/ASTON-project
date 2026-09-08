package com.aston.project.app;

import com.aston.project.app.builder.model.Student;
import com.aston.project.app.strategy.impl.JsonReader;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Main {
    private static final String JSON_PATH = "src/main/resources/students.json";

    private static CustomArrayList<Student> students = new CustomArrayList<>();

    static void main() {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);

        System.out.println("Введите q чтобы закрыть программу");
        while (isRunning) {
            System.out.println("Введите номер желаемой опции");
            System.out.println("1. Отсортирвать данные из JSON файла");
            System.out.println("2. Отсортирвать рандомные данные");
            System.out.println("3. Отсортирвать введённые данные");
            String code = input.next();
            switch (code.toLowerCase()) {
                case "q":
                    System.out.println("Выход из программы");
                    isRunning = false;
                    break;
                case "1":
                    System.out.println("Сортирую данные из JSON файла");
                    JsonReader reader = new JsonReader(JSON_PATH);
                    students = reader.fill(0);
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    continue;
                case "2":
                    System.out.println("Генерирую рандомные данные и сортирую их");
                    continue;
                case "3":
                    System.out.println("Введите данные");
                    continue;
                default:
                    System.out.println("Такой опции нет");
            }
        }
        input.close();
        System.out.println("Программа завершила работу");
    }
}
