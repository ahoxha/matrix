package mx;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MatrixView
{

	public int[] parse(String s) throws NumberFormatException
	{
		Vector<String> vec = new Vector<String>();
		StringTokenizer t = new StringTokenizer(s, ",-");
		int i = 0;
		while (t.hasMoreTokens())
		{
			i++;
			vec.add(t.nextToken());
		}

		int[] rez = new int[i];
		for (int j = 0; j < rez.length; j++)
		{
			rez[j] = Integer.parseInt(vec.elementAt(j).toString());
		}
		return rez;
	}

	public JTable getMultTable(int[][] m)
	{
		JTable tab = new JTable(m.length, m[0].length);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tab.setForeground(Color.blue);
		tab.setShowGrid(false);
		tab.setEnabled(false);
		tab.setRowHeight(20);
		tab.setTableHeader(null);
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m.length; j++)
			{
				if (m[j][i] != 0 || j == i)
				{
					tab.setValueAt(m[j][i], j, i);

				}
			}

		}

		return tab;
	}

}