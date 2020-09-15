package org.hoxha.matrix;

import org.hoxha.matrix.domain.Result;

public final class MatrixChainOrder {

    private static final int MINIMUM_REQUIRED_ELEMENTS = 4;

    private MatrixChainOrder() {
    }

    public static Result findOptimalCost(int[] dimensions) {
        ensureValidInput(dimensions);

        Result result = new Result();
        double infinity = 1.0 / 0;
        int n = dimensions.length - 1;
        int[][] multiplications = new int[n][n];
        int[][] indices = new int[n][n];

        for (int x = 2; x <= n; x++) {
            for (int i = 1; i <= n - x + 1; i++) {
                int j = i + x - 1;
                multiplications[i - 1][j - 1] = (int) infinity;
                for (int k = i; k <= j - 1; k++) {
                    int q = multiplications[i - 1][k - 1] + multiplications[k][j - 1] + dimensions[i - 1] * dimensions[k] * dimensions[j];
                    if (q < multiplications[i - 1][j - 1]) {
                        multiplications[i - 1][j - 1] = q;
                        indices[i - 1][j - 1] = k;
                    }
                }
            }
        }

        result.setMultiplicationsMatrix(multiplications);
        result.setIndicesMatrix(indices);
        return result;
    }

    public static String parenthesize(int[][] s, int i, int j) {
        String res = "";
        if (i == j) {
            res += "A" + (i + 1);
        } else {
            int k = s[i][j];
            res += "(" + parenthesize(s, i, k - 1) + "*" + parenthesize(s, k, j) + ")";
        }
        return res;
    }

    private static void ensureValidInput(int[] dimensions) {
        if (dimensions == null || dimensions.length < MINIMUM_REQUIRED_ELEMENTS) {
            throw new IllegalArgumentException("You should provide an array of at least 4 elements that specify the dimensions of multipliable matrices.");
        }
    }
}
