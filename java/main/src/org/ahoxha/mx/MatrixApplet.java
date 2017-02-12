package org.ahoxha.mx;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class MatrixApplet extends JApplet implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField matrixArrayTextField = new JTextField("", 25);
	private JButton calcBtn = new JButton("Calculate");
	private MatrixView mv = new MatrixView();
	private JScrollPane sp1;
	private JScrollPane sp2;
	private Container cp = this.getContentPane();
	private JPanel matrices = new JPanel(new GridLayout(2, 1));
	private JTextArea result = new JTextArea("", 4, 30);
	private String text = " In order to have the minimum number of multiplications,\n"
			+ " matrices has to be parenthesized as follows : \n ";
	private String errMessage = "Invalid input argument.";

	public void init() {

		cp.setBackground(Color.white);

		cp.setLayout(new BorderLayout());

		JPanel pad = new JPanel();
		pad.setBackground(new Color(0xfdf5e6));
		pad.add(matrixArrayTextField);
		pad.add(calcBtn);
		cp.add(pad, BorderLayout.NORTH);

		matrices.setBackground(Color.white);

		cp.add(matrices, BorderLayout.CENTER);

		result.setFont(new Font("Arial", Color.HSBtoRGB(169, 255, 132), 20));
		result.setEditable(false);
		result.setForeground(Color.blue);
		JScrollPane scroll = new JScrollPane(result);
		cp.add(scroll, BorderLayout.SOUTH);
		calcBtn.addActionListener(this);
		this.setVisible(true);
		this.setSize(600, 600);
	}

	public void actionPerformed(ActionEvent evt) {
		int[] p;
		try {
			result.setText("");
			result.setForeground(Color.blue);
			p = mv.parse(matrixArrayTextField.getText());
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
			result.setText(errMessage);

		}
	}

}