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
