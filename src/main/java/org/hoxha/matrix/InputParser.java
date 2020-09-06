package org.hoxha.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class InputParser {

    private static final int MINIMUM_REQUIRED_ELEMENTS = 4;

    private InputParser() {
    }

    public static int[] parse(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException();
        }

        List<Integer> elements = new ArrayList<>();
        StringTokenizer t = new StringTokenizer(s.replace(" ", ""), ",-");
        while (t.hasMoreTokens()) {
            elements.add(Integer.parseInt(t.nextToken()));
        }
        assertMinimumRequiredElements(elements);
        return elements.stream().mapToInt(n -> n).toArray();
    }

    private static void assertMinimumRequiredElements(List<Integer> vec) {
        if (vec.size() < MINIMUM_REQUIRED_ELEMENTS) {
            throw new IllegalArgumentException("You need to provide at least " + MINIMUM_REQUIRED_ELEMENTS + " elements.");
        }
    }
}
