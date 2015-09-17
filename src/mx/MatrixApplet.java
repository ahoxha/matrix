package mx;

 import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
 
 public class MatrixApplet extends JApplet implements ActionListener
 { 
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JTextField vargu = new JTextField("",25);
   private JButton llog = new JButton("Calculate");
   private MatrixView mv = new MatrixView();
   private JScrollPane sp1;
   private JScrollPane sp2;
   private Container cp = this.getContentPane();
   private JPanel matricat = new JPanel(new GridLayout(2,1));
   private JTextArea kllp = new JTextArea("",4,30);
   private String text = " In order to have the minimum number of multiplications,\n"
			+ " matrices has to be parenthesized as follows : \n ";
   private String mesazhi = "Invalid input argument.";
   
   public void init()
   { 
    
     cp.setBackground(Color.white);
     
     cp.setLayout(new BorderLayout());
	
	 
     JPanel pad = new JPanel();
     pad.setBackground(new Color(0xfdf5e6));
     pad.add(vargu);
     pad.add(llog);
     cp.add(pad,BorderLayout.NORTH);
     
     matricat.setBackground(Color.white);
     
 
     cp.add(matricat, BorderLayout.CENTER);
    
     kllp.setFont(new Font("Arial",Color.HSBtoRGB(169,255,132),20));
     kllp.setEditable(false);
     kllp.setForeground(Color.blue);
     JScrollPane scroll = new JScrollPane(kllp);
     cp.add(scroll,BorderLayout.SOUTH);
     llog.addActionListener(this);
 	 this.setVisible(true);	
 	 this.setSize(600, 600);
   }	
   public void actionPerformed(ActionEvent evt)
   { int[] p;
   	 try 
	    { kllp.setText("");
	      kllp.setForeground(Color.blue);
	      p = mv.parse(vargu.getText());
	      Object[] ob = MatrixChainOrder.optimalCost(p);
	      matricat.removeAll();
	      
	      int[][] m = (int[][])ob[0];
	      int[][] s = (int[][])ob[1];
	      sp1 = new JScrollPane(mv.getMultTable(m));
	      sp2 = new JScrollPane(mv.getMultTable(s));
	      sp1.getViewport().setBackground(Color.white);
          sp2.getViewport().setBackground(Color.white);
          JPanel pan1 = new JPanel(new BorderLayout());
          JPanel pan2 = new JPanel(new BorderLayout());
          pan1.removeAll();
          pan2.removeAll();
          pan1.add(new JLabel("  Matrix of multiplications : "),BorderLayout.NORTH);
          pan1.add(sp1,BorderLayout.CENTER);
          pan2.add(new JLabel("  Matrix of indices : "),BorderLayout.NORTH);
          pan2.add(sp2,BorderLayout.CENTER);

          matricat.add(pan1);
          matricat.add(pan2);
	      matricat.updateUI();
	      kllp.append(text+MatrixChainOrder.kllapezo(s,0,p.length-2));
	    }
	    catch (Exception ex) 
	    {
	      kllp.setForeground(Color.red);
	      kllp.setText(mesazhi);
	      
	    }
   }

 }