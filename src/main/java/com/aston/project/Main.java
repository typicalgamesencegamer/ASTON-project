package com.aston.project;

import java.util.Scanner;

public class Main {
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
