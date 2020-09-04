package org.hoxha.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hoxha.matrix.domain.Result;
import org.junit.jupiter.api.Test;

class MatrixChainOrderTest {
    @Test
    void when_one_matrix_then_multiplications_and_indices_matrices_must_have_one_element_equal_to_zero() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] { 34, 32 });

        int[][] multiplications = output.getMultiplicationsMatrix();
        int[][] indices = output.getIndicesMatrix();

        assertNotNull(multiplications);
        assertNotNull(indices);
        assertEquals(1, multiplications.length);
        assertEquals(1, multiplications[0].length);
        assertEquals(1, indices.length);
        assertEquals(1, indices[0].length);
        assertEquals(0, multiplications[0][0]);
        assertEquals(0, indices[0][0]);
    }

    @Test
    void when_no_matrix_then_expect_null_matrices() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] {});

        assertNull(output.getMultiplicationsMatrix());
        assertNull(output.getIndicesMatrix());
    }
}
