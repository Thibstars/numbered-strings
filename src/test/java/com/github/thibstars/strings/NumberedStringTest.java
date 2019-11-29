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

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Thibault Helsmoortel
 */
class NumberedStringTest {

    @DisplayName("Should get singular numbered String")
    @Test
    void shouldGetSingularNumberedString() {
        final String singularForm = "potato";
        final String pluralForm = "potatoes";

        String result = NumberedString.getNumberedString(1, singularForm, pluralForm);

        Assertions.assertEquals(singularForm, result, "Result must match singular form.");
    }

    @DisplayName("Should get plural numbered String")
    @Test
    void shouldGetPluralNumberedString() {
        final String singularForm = "a panda";
        final String pluralForm = "pandas";

        String result = NumberedString.getNumberedString(2, singularForm, pluralForm);

        Assertions.assertEquals(pluralForm, result, "Result must match singular form.");
    }

    @DisplayName("Should correctly format String")
    @Test
    void shouldCorrectlyFormatString() {
        final String singularForm = "antenna";
        final String pluralForm = "antennae";
        String result = NumberedString.format("%d %s", 1, singularForm, pluralForm);

        Assertions.assertEquals("1 " + singularForm, result, "Result must match singular form with number.");

        result = NumberedString.format("%d %s", 2, singularForm, pluralForm);
        Assertions.assertEquals("2 " + pluralForm, result, "Result must match plural form with number.");
    }

    @DisplayName("Should correctly format numbered String pair")
    @Test
    void shouldCorrectlyFormatNumberedStringPair() {
        final String singularForm = "person";
        final String pluralForm = "people";
        final NumberedStringPair pair = new NumberedStringPair(singularForm, pluralForm);
        String result = NumberedString.format("%d %s", 1, pair);

        Assertions.assertEquals("1 " + singularForm, result, "Result must match singular form with number.");

        result = NumberedString.format("%d %s", 2, pair);
        Assertions.assertEquals("2 " + pluralForm, result, "Result must match plural form with number.");
    }

    @Test
    void shouldCorrectlyFormatNumberedStringPairs() {
        List<NumberedStringPair> pairs = List.of(
            new NumberedStringPair("friend", "friends"),
            new NumberedStringPair("foe", "foes")
        );

        String result = NumberedString.format("%d %s %s", 1, pairs);

        Assertions.assertEquals("1 " + pairs.get(0).getSingularForm() + " " + pairs.get(1).getSingularForm(), result, "Result must match singular form.");

        result = NumberedString.format("%d %s %s", 2, pairs);
        Assertions.assertEquals("2 " + pairs.get(0).getPluralForm() + " " + pairs.get(1).getPluralForm(), result, "Result must match plural form.");
    }
}
