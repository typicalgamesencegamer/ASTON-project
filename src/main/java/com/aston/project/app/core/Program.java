package com.aston.project.app.core;

import com.aston.project.app.builder.StudentComparators;
import com.aston.project.app.builder.model.Student;
import com.aston.project.app.strategy.api.FillStrategy;
import com.aston.project.app.strategy.impl.JsonFillStrategy;
import com.aston.project.app.strategy.impl.ManualFillStrategy;
import com.aston.project.app.strategy.impl.RandomFillStrategy;
import com.aston.project.app.strategy.model.DataFiller;
import com.aston.project.app.utils.customcollections.CustomArrayList;
import com.aston.project.app.utils.sort.CustomAdditionalSort;
import com.aston.project.app.utils.sort.CustomSort;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
    private boolean isRunning = true;
    private Scanner input = new Scanner(System.in);
    private DataFiller dataFiller = new DataFiller();
    private CustomArrayList<Student> students;
    private List<Student> sortedStudents;

    private final String JSON_PATH = "src/main/resources/students.json";

    public Program() {

    }

    private void printStudents() {
        if (!studentsExist()) {
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void printSortedStudents() {
        if (!sortedStudentsExist()) {
            return;
        }
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }

    private boolean studentsExist() {
        if (students != null) {
            return true;
        }
        System.out.println("нет студентов");
        return false;
    }

    private boolean sortedStudentsExist() {
        if (sortedStudents != null) {
            return true;
        }
        System.out.println("нет отсортированных студентов");
        return false;
    }

    private void printInfo() {
        System.out.println("Введите q чтобы закрыть программу");
        System.out.println("Введите номер желаемой опции");
        System.out.println("1. Заполнить данными из JSON файла");
        System.out.println("2. Заполнить рандомными данными");
        System.out.println("3. Заполнить введёнными данными");
        System.out.println("4. Отсортировать массив данных");
        System.out.println("5. Отсортировать массив данных дополнительной сортировкой");
        System.out.println("6. Вывести массив данных");
        System.out.println("7. Вывести отсортированный массив данных\n");
    }

    private void fillData(FillStrategy strategy) throws RuntimeException {
        int length = askForLength();
        dataFiller.setFillStrategy(strategy);
        students = dataFiller.fillData(length);
    }

    private int askForLength() {
        System.out.println("Введите количество элементов");
        int count = input.nextInt();
        if (count <= 0) {
            throw new RuntimeException("Количество элементов должно быть больше или равно 1");
        }
        return count;
    }

    private void sortData() {
        Comparator<Student> comparator = askForComparator();
        sortedStudents = CustomSort.merge(students, comparator);
    }

    private void additionalSortData() {
        Comparator<Student> comparator = askForComparator();
        sortedStudents = CustomAdditionalSort.sort(students, comparator);
    }
    private Comparator<Student> askForComparator() {
        System.out.println("выберите сортировку по полю");
        System.out.println("1. По номеру группы");
        System.out.println("2. По номеру студента");
        System.out.println("3. По среднему баллу");
        String code = input.next();
        return switch (code) {
            case "1" -> StudentComparators.COMPARE_BY_GROUP_NUMBER;
            case "2" -> StudentComparators.COMPARE_BY_STUDENT_ID;
            case "3" -> StudentComparators.COMPARE_BY_AVERAGE_GRADE;
            default -> {
                System.out.println("Такой опции нет, будет выбрана сортировка по умолчанию");
                yield StudentComparators.COMPARE_BY_GROUP_NUMBER;
            }
        };
    }

    public void start() {
        while (isRunning) {
            printInfo();
            String code = input.next();
            switch (code.toLowerCase()) {
                case "q":
                    System.out.println("Выход из программы");
                    isRunning = false;
                    input.close();
                    break;
                case "1":
                    System.out.println("Чтение данных из JSON файл");
                    try {
                        fillData(new JsonFillStrategy(JSON_PATH));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    continue;
                case "2":
                    System.out.println("Генерация рандомных данных");
                    try {
                        fillData(new RandomFillStrategy());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    continue;
                case "3":
                    System.out.println("Ручной ввод");
                    try {
                        fillData(new ManualFillStrategy(input));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    continue;
                case "4":
                    sortData();
                    System.out.println("Сортировка выполнена");
                    System.out.println();
                    continue;
                case "5":
                    additionalSortData();
                    System.out.println("Сортировка выполнена");
                    System.out.println();
                    continue;
                case "6":
                    printStudents();
                    System.out.println();
                    continue;
                case "7":
                    printSortedStudents();
                    System.out.println();
                    continue;
                default:
                    System.out.println("Такой опции нет");
            }
        }
    }
}
