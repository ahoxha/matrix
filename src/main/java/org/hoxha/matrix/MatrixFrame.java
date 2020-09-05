package org.hoxha.matrix;

import static org.hoxha.matrix.GuiUtils.renderTable;
import static org.hoxha.matrix.GuiUtils.showHelpDialog;

import java.awt.*;

import javax.swing.*;

import org.hoxha.matrix.domain.Result;

public class MatrixFrame extends JFrame {

    private final JTextField inputField = new JTextField("", 25);
    private final JPanel matricesPanel = new JPanel(new GridLayout(2, 1));
    private JTextArea resultArea;

    public MatrixFrame() {
        Container contentPane = getAndCustomizeContentPane();

        JPanel controlPanel = createControlPanel();
        createResultArea();

		setUpContentPane(contentPane, controlPanel);

		customizeMatrixFrame();
    }

	private Container getAndCustomizeContentPane() {
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BorderLayout());
		return contentPane;
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(0xfdf5e6));
		controlPanel.add(inputField);
		addCalculateButton(controlPanel);
		addHelpButton(controlPanel);
		return controlPanel;
	}

	private void createResultArea() {
		resultArea = new JTextArea("", 4, 30);
		resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
		resultArea.setEditable(false);
		resultArea.setForeground(Color.blue);
	}

	private void setUpContentPane(Container contentPane, JPanel controlPanel) {
		contentPane.add(controlPanel, BorderLayout.NORTH);
		contentPane.add(matricesPanel, BorderLayout.CENTER);
		contentPane.add(new JScrollPane(resultArea), BorderLayout.SOUTH);
	}

	private void customizeMatrixFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 600) / 2, (screenSize.height - 600) / 2, 600, 600);
        this.setTitle("Matrix Chain Order");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

	private void addCalculateButton(JPanel pad) {
		JButton calculateButton = new JButton("Calculate");
		pad.add(calculateButton);
		calculateButton.addActionListener(actionEvent -> calculate());
	}

    private void addHelpButton(JPanel pad) {
        JButton helpButton = new JButton("Help");
        pad.add(helpButton);
        helpButton.addActionListener(a -> showHelpDialog(this));
    }

    private void calculate() {
		try {
			clearResults();

			int[] inputArray = InputParser.parse(inputField.getText());
			Result result = MatrixChainOrder.findOptimalCost(inputArray);

			renderMatrixPanel(result.getMultiplicationsMatrix(), "  Matrix of multiplications : ");
			renderMatrixPanel(result.getIndicesMatrix(), "  Matrix of indices : ");
			renderResults(result, inputArray.length - 2);
		} catch (Exception ex) {
			showErrorMessage();
		}
		matricesPanel.updateUI();
    }

	private void clearResults() {
		matricesPanel.removeAll();
		resultArea.setText("");
		resultArea.setForeground(Color.blue);
	}

	private void renderMatrixPanel(int[][] matrix, String title) {
		JScrollPane scrollPane = new JScrollPane(renderTable(matrix));
		scrollPane.getViewport().setBackground(Color.white);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(title), BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		matricesPanel.add(panel);
	}

	private void renderResults(Result result, int j) {
		String text = " To ensure the minimum number of multiplications,\n" + " matrices have to be multiplied in this order : \n ";
		resultArea.append(text + MatrixChainOrder.parenthesize(result.getIndicesMatrix(), 0, j));
	}

    private void showErrorMessage() {
        resultArea.setForeground(Color.red);
        resultArea.setText("Invalid input argument.");
    }
}
