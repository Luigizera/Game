package app;

import javax.swing.JFrame;

public class Application {
	
	public static void main(String[] args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Ludas Game");
		
		GamePanel gPanel = new GamePanel();
		window.add(gPanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
}
