package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameScreen extends JPanel{
	
	private GameBackground gameBackground;
	
	public GameScreen() {
		this.setPreferredSize(new Dimension(GameWindow.screenWidth , GameWindow.screenHeight));
		gameBackground = new GameBackground();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.clearRect(0, 0, GameWindow.screenWidth, GameWindow.screenHeight);
		g2d.setComposite(gameBackground.transcluentBlack);
		gameBackground.draw(g2d);
		g2d.setComposite(gameBackground.opaque);
	}
}
