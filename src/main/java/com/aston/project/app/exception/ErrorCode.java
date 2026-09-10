package com.aston.project.app.exception;

public enum ErrorCode {
    FILE_MISSING(1001, "Файл не найден"),
    FILE_EMPTY(1002, "Файл пустой"),
    MALFORMED_JSON(1003, "Файл не соответствует формату JSON"),
    EMPTY_LIST(1004, "Список студентов пуст"),
    LIST_NULL(1005, "Список студентов null"),
    FILE_ERROR(1006, "Ошибка при открытии файла");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}