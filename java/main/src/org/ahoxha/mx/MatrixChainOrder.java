package org.ahoxha.mx;

public class MatrixChainOrder {
	public static Object[] optimalCost(int[] p) {
		Object[] result = new Object[2];
		double infinity = 1.0 / 0;
		int n = p.length - 1;
		int[][] m = new int[n][n];
		int[][] s = new int[n][n];

		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				m[i - 1][j - 1] = (int) infinity;
				for (int k = i; k <= j - 1; k++) {
					int q = m[i - 1][k - 1] + m[k][j - 1] + p[i - 1] * p[k] * p[j];
					if (q < m[i - 1][j - 1]) {
						m[i - 1][j - 1] = q;
						s[i - 1][j - 1] = k;
					}
				}
			}
		}

		result[0] = m;
		result[1] = s;
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