package org.ahoxha.matrix;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.awt.*;

import javax.swing.*;

public class MatrixFrame extends JFrame {

	private final JTextField inputField = new JTextField("", 25);
	private final JPanel matricesPanel = new JPanel(new GridLayout(2, 1));
	private final JTextArea resultArea = new JTextArea("", 4, 30);

	public MatrixFrame() {
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BorderLayout());

		JPanel pad = new JPanel();
		pad.setBackground(new Color(0xfdf5e6));
		pad.add(inputField);
		JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(actionEvent -> determineMatrixChainOrder());
		pad.add(calculateButton);
		JButton helpButton = new JButton("Help");
		pad.add(helpButton);
		contentPane.add(pad, BorderLayout.NORTH);

		matricesPanel.setBackground(Color.white);
		contentPane.add(matricesPanel, BorderLayout.CENTER);

		resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
		resultArea.setEditable(false);
		resultArea.setForeground(Color.blue);
		JScrollPane scroll = new JScrollPane(resultArea);
		contentPane.add(scroll, BorderLayout.SOUTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 600) / 2, (screenSize.height - 600) / 2, 600, 600);

		helpButton.addActionListener(a -> showHelpDialog());

		this.setTitle("Matrix Chain Order");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void determineMatrixChainOrder() {
		int[] p;
		try {
			wipeOutResultAreaAndResetPenColor();
			p = MatrixView.parse(inputField.getText());
			Object[] ob = MatrixChainOrder.optimalCost(p);
			matricesPanel.removeAll();

			int[][] m = (int[][]) ob[0];
			int[][] s = (int[][]) ob[1];
			JScrollPane sp1 = new JScrollPane(MatrixView.createTable(m));
			JScrollPane sp2 = new JScrollPane(MatrixView.createTable(s));
			sp1.getViewport().setBackground(Color.white);
			sp2.getViewport().setBackground(Color.white);
			JPanel pan1 = new JPanel(new BorderLayout());
			JPanel pan2 = new JPanel(new BorderLayout());
			pan1.removeAll();
			pan2.removeAll();
			pan1.add(new JLabel("  Matrix of multiplications : "), BorderLayout.NORTH);
			pan1.add(sp1, BorderLayout.CENTER);
			pan2.add(new JLabel("  Matrix of indices : "), BorderLayout.NORTH);
			pan2.add(sp2, BorderLayout.CENTER);

			matricesPanel.add(pan1);
			matricesPanel.add(pan2);
			matricesPanel.updateUI();
			String text = " To ensure the minimum number of multiplications,\n" + " matrices have to be multiplied in this order : \n ";
			resultArea.append(text + MatrixChainOrder.parenthesize(s, 0, p.length - 2));
		} catch (Exception ex) {
			showErrorMessage();
		}
	}

	private void wipeOutResultAreaAndResetPenColor() {
		resultArea.setText("");
		resultArea.setForeground(Color.blue);
	}

	private void showErrorMessage() {
		resultArea.setForeground(Color.red);
		resultArea.setText("Invalid input argument.");
	}

	private void showHelpDialog() {
		String helpText = "We are given three matrices: A1 having 10 rows and 3 columns,\n"
				+ "A2 having 3 rows and 5 columns and A3 having 5 rows and 6\ncolumns.\n"
				+ "To find out in what order we must multiply them so that we minimize\n"
				+ "the cost, we type the array of dimensions of matrices in the text \n"
				+ "filed, and press the 'Calculate' button.\n"
				+ "In our case the array is: 10,3,5,6 or 10-3-5-6";

		JTextArea helpTextArea = new JTextArea(helpText, 7, 10);
		helpTextArea.setEditable(false);
		helpTextArea.setForeground(Color.blue);
		helpTextArea.setFont(new Font("Arial", Color.HSBtoRGB(169, 255, 132), 20));

		Object[] ob = { "OK" };
		Object[] h = new Object[2];
		h[0] = helpTextArea;
		h[1] = new JLabel("Author: Armend Hoxha. Created on: 15.01.2006.");
		JOptionPane.showOptionDialog(
				this, h, "Help",
				DEFAULT_OPTION, INFORMATION_MESSAGE,
				null, ob, ob[0]);
	}
}
