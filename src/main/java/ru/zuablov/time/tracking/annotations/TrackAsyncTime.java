package ru.zuablov.time.tracking.annotations;

import ru.zuablov.time.tracking.enums.Group;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация для асинхронного отслеживания времени выполнения метода. Целевой метод будет выполнен асинхронно.
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface TrackAsyncTime {

    /**
     * Параметр для разделения методов по группам
     */
    Group value() default Group.OTHER;
}
