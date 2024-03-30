package ru.zuablov.time.tracking.annotations;

import ru.zuablov.time.tracking.enums.Group;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для отслеживания времени работы синхронных методов. Целевой метод будет выполнен синхронно.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TrackTime {

    /**
     * Параметр для разделения методов по группам
     */
    Group value() default Group.OTHER;
}
