package org.hoxha.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MatrixChainOrderTest {

    @Nested
    @DisplayName("Tests with wrong input")
    class WrongInputTests {
        private static final String ILLEGAL_ARGUMENT_ERROR_MESSAGE = "You should provide an array of at least 4 elements that specify the dimensions of multipliable matrices.";

        @Test
        void when_no_matrix_then_throw_exception() {
            testWithInvalidInput();
        }

        @Test
        void when_one_element_array_is_provided_then_throw_exception() {
            testWithInvalidInput(12);
        }

        @Test
        void when_two_element_array_is_provided_then_throw_exception() {
            testWithInvalidInput(12, 23);
        }

        @Test
        void when_three_element_array_is_provided_then_throw_exception() {
            testWithInvalidInput(12, 23, 45);
        }

        private void testWithInvalidInput(int... dimensions) {
            try {
                MatrixChainOrder.findOptimalCost(dimensions);
                fail("It should have thrown an IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertEquals(ILLEGAL_ARGUMENT_ERROR_MESSAGE, e.getMessage());
            }
        }
    }
}
