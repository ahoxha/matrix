package org.hoxha.matrix;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.hoxha.matrix.domain.Result;
import org.junit.jupiter.api.Test;

class MatrixChainOrderTest {

    @Test
    void when_one_element_array_is_provided_then_return_null_results() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] { 12 });

        assertNull(output.getIndicesMatrix());
        assertNull(output.getMultiplicationsMatrix());
    }

    @Test
    void when_two_element_array_is_provided_then_return_null_results() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] { 12, 23 });

        assertNull(output.getIndicesMatrix());
        assertNull(output.getMultiplicationsMatrix());
    }

    @Test
    void when_three_element_array_is_provided_then_return_null_results() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] { 12, 23, 34 });

        assertNull(output.getIndicesMatrix());
        assertNull(output.getMultiplicationsMatrix());
    }

    @Test
    void when_no_matrix_then_expect_null_matrices() {
        Result output = MatrixChainOrder.findOptimalCost(new int[] {});

        assertNull(output.getMultiplicationsMatrix());
        assertNull(output.getIndicesMatrix());
    }
}
