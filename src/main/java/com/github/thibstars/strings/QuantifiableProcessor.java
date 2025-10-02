package com.github.thibstars.strings;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

/**
 * Processor to process fields annotated with {@link Quantifiable}.
 *
 * @author Thibault Helsmoortel
 */
public class QuantifiableProcessor {

    private QuantifiableProcessor() {
        // Hide otherwise publicly available default constructor
    }

    public static Optional<String> process(Object object, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        return process(object, object.getClass().getDeclaredField(fieldName));
    }

    public static Optional<String> process(Object object, Field field) throws IllegalAccessException {
        Objects.requireNonNull(object, "Object cannot be null");
        Objects.requireNonNull(field, "Field cannot be null");

        if (field.isAnnotationPresent(Quantifiable.class)) {
            Quantifiable annotation = field.getAnnotation(Quantifiable.class);
            String singularForm = annotation.singularForm();
            String pluralForm = annotation.pluralForm();

            boolean couldAccess = field.canAccess(object);
            if (!couldAccess) {
                field.setAccessible(true);
            }

            Integer amount = (Integer) field.get(object);

            if (amount == null) {
                return Optional.empty();
            }

            Optional<String> numberedString = Optional.of(
                    NumberedString.getNumberedString(amount, singularForm, pluralForm)
            );

            field.setAccessible(couldAccess);

            return numberedString;
        }

        return Optional.empty();
    }

}
