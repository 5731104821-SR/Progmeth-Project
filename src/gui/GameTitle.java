package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import res.Resource;

public class GameTitle extends JComponent{
	
	public static final int screenWidth = 800;
	public static final int screenHeight = 600;
	private GameBackground gameBackground;
	
	public GameTitle() {
		this.setPreferredSize(new Dimension(800,600));
		gameBackground = new GameBackground();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.clearRect(0, 0, 800, 600);
		gameBackground.draw(g2d);
		gameBackground.updateBackground();
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.titleFont);
		g2d.drawString("PRESS ENTER TO START", 150, 500);
		g2d.setColor(Color.BLUE);
		g2d.setFont(Resource.instructionFont);
		g2d.drawString("INSTRUCTION:", 320, 350);
		g2d.drawString("PRESS SPACE FOR JUMP", 270, 380);
		g2d.drawString("LEFT MOUSE CLICK FOR SHOOT!", 230, 410);
	}
		
}
