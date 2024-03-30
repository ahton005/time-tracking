package ru.zuablov.time.tracking.enums;

/**
 * Перечисление статусов выполнения метода
 */
public enum Status {
    OK, ERROR;

    public static Status fromValue(String value) {
        Status res = OK;
        if (value == null) return res;
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                res = status;
                break;
            }
        }
        return res;
    }
}
