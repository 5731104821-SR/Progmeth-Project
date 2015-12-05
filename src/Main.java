import java.awt.Dimension;

import javax.swing.JFrame;

import gui.GameTitle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameTitle gameTitle = new GameTitle();
		JFrame f = new JFrame("Piggy Bird");
		f.add(gameTitle);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		
		while(true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameTitle.repaint();
		}
	}

}
