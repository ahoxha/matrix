package org.ahoxha.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixViewTest {
    @Test
    void testParse() {
        Assertions.assertArrayEquals(new int[]{23, 12, 34}, MatrixView.parse("23,12,34"));
    }
}
