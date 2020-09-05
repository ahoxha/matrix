package org.hoxha.matrix;

import static org.hoxha.matrix.InputParser.parse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class InputParserTest {

    private static final String FAIL_MESSAGE = "Must throw exception";

    @Test
    void testParseWithComma() {
        assertArrayEquals(new int[] { 23, 12, 34 }, parse("23,12,34"));
    }

    @Test
    void testParseWithDash() {
        assertArrayEquals(new int[] { 1, 2, 3, 4 }, parse("1-2-3-4"));
    }

    @Test
    void testParseWithOneElement() {
        assertArrayEquals(new int[] { 10 }, parse("10"));
    }

    @Test
    void testParseWithExtraDashInTheEnd() {
        assertArrayEquals(new int[] { 12, 13, 14, 15 }, parse("12-13-14-15-"));
    }

    @Test
    void testParseWithExtraCommaInTheEnd() {
        assertArrayEquals(new int[] { 1, 5, 7, 8 }, parse("1,5,7,8,"));
    }

    @Test
    void testParseWithMixedDelimiters() {
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7 }, parse("1-2,3,4-5,6-7"));
    }

    @Test
    void testParseWithConsecutiveDelimiters() {
        assertArrayEquals(new int[] { 1, 2, 3 }, parse("1,-2,,3"));
    }

    @Test
    void testParseWithSpacesBetweenNumbersAndDelimiters() {
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, parse("1 -2  - 3,4  ,  5"));
    }

    @Test
    void testParseWithSpacesBetweenNumbers() {
        assertArrayEquals(new int[] { 1, 23, 4 }, parse("1  , 2   3, 4   "));
    }

    @Test
    void when_input_contains_letters_then_throw_exception() {
        try {
            parse("1,a");
            fail(FAIL_MESSAGE);
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    void when_input_is_empty_then_throw_exception() {
        try {
            parse("");
            fail(FAIL_MESSAGE);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void when_input_is_null_then_throw_exception() {
        try {
            parse(null);
            fail(FAIL_MESSAGE);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void when_input_is_blank_then_throw_exception() {
        try {
            parse("    ");
            fail(FAIL_MESSAGE);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
