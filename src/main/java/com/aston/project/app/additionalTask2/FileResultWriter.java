package com.aston.project.app.additionalTask2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

public class FileResultWriter {

    private static final Path FILE_PATH = Path.of("result.txt");

    private FileResultWriter() {
    }

    public static void writeToFile(Collection<?> collection) {
        try {
            Files.write(FILE_PATH, collection.stream()
                            .map(element -> element + System.lineSeparator())
                            .toList(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
