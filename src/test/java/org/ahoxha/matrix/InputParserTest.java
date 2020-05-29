package org.ahoxha.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputParserTest {
    @Test
    void testParse() {
        Assertions.assertArrayEquals(new int[]{23, 12, 34}, InputParser.parse("23,12,34"));
    }
}
