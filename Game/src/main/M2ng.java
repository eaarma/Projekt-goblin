package main;

import javax.swing.JFrame;

public class M2ng {
	public static void main(String[] args) {
		JFrame window = new JFrame("Goblin");
		window.setContentPane(new Board());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
		
	}

}
