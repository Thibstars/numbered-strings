package com.github.thibstars.strings;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark an integer field as quantifiable.
 *
 * @author Thibault Helsmoortel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Quantifiable {

    String singularForm();

    String pluralForm();

}
