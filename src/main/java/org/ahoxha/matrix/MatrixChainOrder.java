package org.ahoxha.matrix;

public class MatrixChainOrder {
    public static Object[] optimalCost(int[] dimensions) {
        Object[] result = new Object[2];
        if (dimensions.length < 1) {
            return result;
        }

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

        result[0] = multiplications;
        result[1] = indices;
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
}
