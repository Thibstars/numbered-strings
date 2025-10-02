package com.github.thibstars.strings;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Thibault Helsmoortel
 */
class QuantifiableProcessorTest {

    @Test
    void shouldGetSingularNumberedString() throws NoSuchFieldException, IllegalAccessException {
        final String singularForm = "potato";

        TestClass object = new TestClass();
        object.setAmountOfPotatoes(1);
        String result = QuantifiableProcessor.process(object, "amountOfPotatoes").orElseThrow();

        Assertions.assertEquals(singularForm, result, "Result must match singular form.");
    }

    @Test
    void shouldGetPluralNumberedString() throws NoSuchFieldException, IllegalAccessException {
        final String pluralForm = "potatoes";

        TestClass object = new TestClass();
        object.setAmountOfPotatoes(2);
        String result = QuantifiableProcessor.process(object, "amountOfPotatoes").orElseThrow();

        Assertions.assertEquals(pluralForm, result, "Result must match plural form.");
    }

    @Test
    void shouldNotProcessFieldWithoutAnnotation() throws NoSuchFieldException, IllegalAccessException {
        TestClass object = new TestClass();
        Assertions.assertTrue(QuantifiableProcessor.process(object, "notAnnotatedField").isEmpty());
    }

    @Test
    void shouldNotProcessFieldWithValueNull() throws NoSuchFieldException, IllegalAccessException {
        TestClass object = new TestClass();
        object.setAmountOfPotatoes(null);

        Assertions.assertTrue(QuantifiableProcessor.process(object, "amountOfPotatoes").isEmpty());
    }

    @Data
    static class TestClass {

        @Quantifiable(singularForm = "potato", pluralForm = "potatoes")
        private Integer amountOfPotatoes;

        private int notAnnotatedField;

    }

}
