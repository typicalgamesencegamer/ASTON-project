package com.aston.project.app.additionalTask2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Scanner;

public class FileResultWriter {

    private static final Path FILE_PATH = Path.of("result.txt");

    private FileResultWriter() {
    }

    public static void offerToSave(Collection<?> collection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Записать результат в файл? (y/n) - (default: n)");

        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("y")) {
            writeToFile(collection);
        } else {
            System.out.println("Результат не записан в файл.");
        }
    }
    private static void writeToFile(Collection<?> collection) {
        try {
            Files.write(FILE_PATH, collection.stream().map(element -> element +
                            System.lineSeparator()).toList(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.println("Результат добавлен в файл: " + FILE_PATH);
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
