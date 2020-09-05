package org.hoxha.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class InputParser {

    private InputParser() {
    }

    public static int[] parse(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException();
        }

        List<Integer> vec = new ArrayList<>();
        StringTokenizer t = new StringTokenizer(s.replace(" ", ""), ",-");
        while (t.hasMoreTokens()) {
            vec.add(Integer.parseInt(t.nextToken()));
        }

        return vec.stream().mapToInt(n -> n).toArray();
    }
}
