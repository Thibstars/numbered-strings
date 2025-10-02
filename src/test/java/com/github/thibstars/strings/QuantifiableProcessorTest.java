package com.github.thibstars.strings;

import java.util.Map;
import java.util.Optional;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Thibault Helsmoortel
 */
class QuantifiableProcessorTest {

    @Test
    void shouldProcessAll() {
        TestClass object = new TestClass();
        object.setAmountOfPotatoes(1);
        object.setAmountOfPandas(2);
        Map<String, Optional<String>> result = QuantifiableProcessor.process(object);

        Assertions.assertEquals("potato", result.get("amountOfPotatoes").orElseThrow(), "Result must match singular form.");
        Assertions.assertEquals("pandas", result.get("amountOfPandas").orElseThrow(), "Result must match plural form.");
    }

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

        @Quantifiable(singularForm = "panda", pluralForm = "pandas")
        private Integer amountOfPandas;

        private int notAnnotatedField;

    }

}
