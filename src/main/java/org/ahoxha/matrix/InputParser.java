package org.ahoxha.matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;

public final class InputParser {

	private InputParser() {}

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
}
