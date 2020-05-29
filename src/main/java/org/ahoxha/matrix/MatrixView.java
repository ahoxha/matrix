package org.ahoxha.matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;

public final class MatrixView {

	private MatrixView() {}

	public static int[] parse(String s) {
		List<String> vec = new ArrayList<>();
		StringTokenizer t = new StringTokenizer(s, ",-");
		int i = 0;
		while (t.hasMoreTokens()) {
			i++;
			vec.add(t.nextToken());
		}

		int[] rez = new int[i];
		for (int j = 0; j < rez.length; j++) {
			rez[j] = Integer.parseInt(vec.get(j));
		}
		return rez;
	}

	public static JTable createTable(int[][] m) {
		JTable tab = new JTable(m.length, m[0].length);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tab.setForeground(Color.blue);
		tab.setShowGrid(false);
		tab.setEnabled(false);
		tab.setRowHeight(20);
		tab.setTableHeader(null);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (m[j][i] != 0 || j == i) {
					tab.setValueAt(m[j][i], j, i);
				}
			}
		}
		return tab;
	}
}
