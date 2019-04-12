package org.ahoxha.mx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixViewTest {
    @Test
    void testParse() {
        MatrixView matrixView = new MatrixView();
        Assertions.assertArrayEquals(new int[]{23, 12, 34}, matrixView.parse("23,12,34"));
    }
}
