package org.ahoxha.matrix;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MatrixFrame extends JFrame implements ActionListener {

	private JTextField matrixArray = new JTextField("", 25);
	private JButton calcBtn = new JButton("Calculate");
	private MatrixView mv = new MatrixView();
	private JScrollPane sp1;
	private JScrollPane sp2;
	private Container cp = this.getContentPane();
	private JPanel matrices = new JPanel(new GridLayout(2, 1));
	private JTextArea result = new JTextArea("", 4, 30);
	private String text = " To ensure the minimum number of multiplications,\n"
			+ " matrices have to be multiplied in this order : \n ";
	private String message = "Invalid input argument.";
	private String help = "We are given three matrices: A1 having 10 rows and 3 columns,\n"
					+ "A2 having 3 rows and 5 columns and A3 having 5 rows and 6\ncolumns.\n"
					+ "To find out in what order we must multiply them so that we minimize\n"
					+ "the cost, we type the array of dimensions of matrices in the text \n"
					+ "filed, and press the 'Calculate' button.\n"
					+ "In our case the array is: 10,3,5,6 or 10-3-5-6";
	private JLabel copyRight = new JLabel("Author: Armend Hoxha. Created on: 15.1.2006.");

	private JButton helpBtn = new JButton("Help");
	private JTextArea helpTextArea = new JTextArea(help, 7, 10);

	public MatrixFrame() {
		cp.setBackground(Color.white);
		cp.setLayout(new BorderLayout());

		helpTextArea.setEditable(false);
		helpTextArea.setForeground(Color.blue);
		helpTextArea.setFont(new Font("Arial", Color.HSBtoRGB(169, 255, 132), 20));

		JPanel pad = new JPanel();
		pad.setBackground(new Color(0xfdf5e6));
		pad.add(matrixArray);
		pad.add(calcBtn);
		pad.add(helpBtn);
		cp.add(pad, BorderLayout.NORTH);

		matrices.setBackground(Color.white);
		cp.add(matrices, BorderLayout.CENTER);

		result.setFont(new Font("Arial", Font.PLAIN, 14));
		result.setEditable(false);
		result.setForeground(Color.blue);
		JScrollPane scroll = new JScrollPane(result);
		cp.add(scroll, BorderLayout.SOUTH);
		calcBtn.addActionListener(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 600) / 2, (screenSize.height - 600) / 2, 600, 600);
		helpBtn.addActionListener(this);

		this.setTitle("Matrix Chain Order");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(helpBtn)) {
			Object[] ob = { "OK" };
			Object[] h = new Object[2];
			h[0] = helpTextArea;
			h[1] = copyRight;
			JOptionPane.showOptionDialog(
					this, h, "Help",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, ob, ob[0]);
		} else {
			int[] p;
			try {
				result.setText("");
				result.setForeground(Color.blue);
				p = mv.parse(matrixArray.getText());
				Object[] ob = MatrixChainOrder.optimalCost(p);
				matrices.removeAll();

				int[][] m = (int[][]) ob[0];
				int[][] s = (int[][]) ob[1];
				sp1 = new JScrollPane(mv.getMultTable(m));
				sp2 = new JScrollPane(mv.getMultTable(s));
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

				matrices.add(pan1);
				matrices.add(pan2);
				matrices.updateUI();
				result.append(text + MatrixChainOrder.parenthesize(s, 0, p.length - 2));
			} catch (Exception ex) {
				result.setForeground(Color.red);
				result.setText(message);
			}
		}
	}
}
