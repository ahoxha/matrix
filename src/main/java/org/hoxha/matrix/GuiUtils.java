package org.hoxha.matrix;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.awt.*;

import javax.swing.*;

public final class GuiUtils {

    private static final String HELP_TEXT =
            "We are given three matrices: A1 having 10 rows and 3 columns,\n" + "A2 having 3 rows and 5 columns and A3 having 5 rows and 6\ncolumns.\n"
                    + "To find out in what order we must multiply them so that we minimize\n" + "the cost, we type the array of dimensions of matrices in the text \n"
                    + "filed, and press the 'Calculate' button.\n" + "In our case the array is: 10,3,5,6 or 10-3-5-6";

    private GuiUtils() {
    }

    public static void displayHelpDialog(JFrame parent) {
        JTextArea helpTextArea = new JTextArea(HELP_TEXT, 7, 10);
        helpTextArea.setEditable(false);
        helpTextArea.setForeground(Color.blue);
        helpTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        Object[] button = { "OK" };
        Object[] contents = new Object[2];
        contents[0] = helpTextArea;
        contents[1] = new JLabel("Author: Armend Hoxha. Created on: 15.01.2006.");
        JOptionPane.showOptionDialog(parent, contents, "Help", DEFAULT_OPTION, INFORMATION_MESSAGE, null, button, button[0]);
    }

    public static JTable renderTable(int[][] matrix) {
        JTable tab = new JTable(matrix.length, matrix[0].length);
        tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tab.setForeground(Color.blue);
        tab.setShowGrid(false);
        tab.setEnabled(false);
        tab.setRowHeight(20);
        tab.setTableHeader(null);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] != 0 || j == i) {
                    tab.setValueAt(matrix[j][i], j, i);
                }
            }
        }
        return tab;
    }
}
