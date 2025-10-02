/*
 * Copyright (c) 2019 Thibault Helsmoortel.
 *
 * This file is part of numbered-strings.
 *
 * numbered-strings is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * numbered-strings is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with numbered-strings.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.thibstars.strings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Processor to process fields annotated with {@link Quantifiable}.
 *
 * @author Thibault Helsmoortel
 */
public class QuantifiableProcessor {

    private QuantifiableProcessor() {
        // Hide otherwise publicly available default constructor
    }
    
    public static Map<String, Optional<String>> process(Object object) {
        Objects.requireNonNull(object, "Object cannot be null");
        return Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> {
                    try {
                        return Map.entry(field.getName(), process(object, field));
                    } catch (IllegalAccessException e) {
                        return Map.entry(field.getName(), Optional.<String>empty());

                    }
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
