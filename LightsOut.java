import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LightsOut {

	
	public static void main(String[] args) {
	
		JFrame Frame= new JFrame ("Frame");

		Frame.setPreferredSize(new Dimension(1200, 700));
		Frame.setLayout(null);
		Frame.setVisible(true);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panel1= new JPanel();
		panel1.setSize(600, 600);
		
		GridLayout Grid= new GridLayout(5, 5);
		
		panel1.setLayout(Grid);
		Frame.add(panel1);
		panel1.setBackground(Color.lightGray);
		
		
				boolean array[][]= new boolean [5][5];
				JButton[][] buttons= new JButton[5][5];
		for (int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				buttons[i][j]= new JButton();
				JButton gay=buttons[i][j];
				panel1.add(buttons[i][j]);
				buttons[i][j].setBackground(Color.yellow);
				array [i][j]=true;
				boolean gay2=array[i][j];
				  
				  ActionListener invert = new ActionListener() {
			            public void actionPerformed (ActionEvent e) {
			            	gay.setBackground(Color.black);
			            	gay2=false;
			            	
			            			
			            }
			        };
			        buttons[i][j].addActionListener(invert);
			}
		}
		
		
	
		Frame.pack();
	}
	

}
