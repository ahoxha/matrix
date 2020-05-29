package org.ahoxha.matrix;

import static org.ahoxha.matrix.GuiUtils.createTable;
import static org.ahoxha.matrix.GuiUtils.showHelpDialog;

import java.awt.*;

import javax.swing.*;

public class MatrixFrame extends JFrame {

	private final JTextField inputField = new JTextField("", 25);
	private final JPanel matricesPanel = new JPanel(new GridLayout(2, 1));
	private JTextArea resultArea;

	public MatrixFrame() {
		Container contentPane = getAndCustomizeContentPane();

		JPanel controlPanel = createControlPanel();
		customizeMatricesPanel();
		createResultArea();

		contentPane.add(controlPanel, BorderLayout.NORTH);
		contentPane.add(matricesPanel, BorderLayout.CENTER);
		contentPane.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

		customizeMatrixFrame();
	}

	private void customizeMatricesPanel() {
		matricesPanel.setBackground(Color.white);
	}

	private void customizeMatrixFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 600) / 2, (screenSize.height - 600) / 2, 600, 600);
		this.setTitle("Matrix Chain Order");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private Container getAndCustomizeContentPane() {
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BorderLayout());
		return contentPane;
	}

	private void createResultArea() {
		resultArea = new JTextArea("", 4, 30);
		resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
		resultArea.setEditable(false);
		resultArea.setForeground(Color.blue);
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(0xfdf5e6));
		controlPanel.add(inputField);
		addCalculateButton(controlPanel);
		addHelpButton(controlPanel);
		return controlPanel;
	}

	private void addHelpButton(JPanel pad) {
		JButton helpButton = new JButton("Help");
		pad.add(helpButton);
		helpButton.addActionListener(a -> showHelpDialog(this));
	}

	private void addCalculateButton(JPanel pad) {
		JButton calculateButton = new JButton("Calculate");
		pad.add(calculateButton);
		calculateButton.addActionListener(actionEvent -> {
			matricesPanel.removeAll();
			determineMatrixChainOrder();
			matricesPanel.updateUI();
		});
	}

	private void determineMatrixChainOrder() {
		int[] p;
		try {
			wipeOutResultAreaAndResetPenColor();
			p = InputParser.parse(inputField.getText());
			Object[] ob = MatrixChainOrder.findOptimalCost(p);

			int[][] m = (int[][]) ob[0];
			int[][] s = (int[][]) ob[1];
			JScrollPane sp1 = new JScrollPane(createTable(m));
			JScrollPane sp2 = new JScrollPane(createTable(s));
			sp1.getViewport().setBackground(Color.white);
			sp2.getViewport().setBackground(Color.white);
			JPanel pan1 = new JPanel(new BorderLayout());
			JPanel pan2 = new JPanel(new BorderLayout());
			pan1.add(new JLabel("  Matrix of multiplications : "), BorderLayout.NORTH);
			pan1.add(sp1, BorderLayout.CENTER);
			pan2.add(new JLabel("  Matrix of indices : "), BorderLayout.NORTH);
			pan2.add(sp2, BorderLayout.CENTER);

			matricesPanel.add(pan1);
			matricesPanel.add(pan2);
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
}
