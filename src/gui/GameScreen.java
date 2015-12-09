package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import logic.IRenderable;
import logic.RenderableHolder;
import res.Resource;

public class GameScreen extends JPanel{
	
	private GameBackground gameBackground;
	private int startDelayCounter = 0;
	private int startDelay = 60;
	private int countDownNumber = 4;
	public static boolean isStart = false;
	
	public GameScreen() {
		this.setPreferredSize(new Dimension(GameWindow.screenWidth , GameWindow.screenHeight));
		gameBackground = new GameBackground();
		//setDoubleBuffered(true);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), true);
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(!isStart){
			g2d.clearRect(0, 0, GameWindow.screenWidth, GameWindow.screenHeight);
			g2d.setComposite(gameBackground.transcluentBlack);
			gameBackground.draw(g2d);
			g2d.drawImage(Resource.character, null, 30, 220);
			g2d.setComposite(gameBackground.opaque);
			if(startDelayCounter < startDelay && countDownNumber==4){
				startDelayCounter++;
				g2d.setColor(Color.BLUE);
				g2d.setFont(Resource.countDownFont);
				g2d.drawString("GET READY", 160, 320);
			}
			else if(startDelayCounter < startDelay && countDownNumber < 4){
				startDelayCounter++;
				g2d.setColor(Color.BLUE);
				g2d.setFont(Resource.countDownFont);
				g2d.drawString(Integer.toString(countDownNumber), 380, 320);
			}
			else if(startDelayCounter >= startDelay && countDownNumber==1){
				isStart = true;
				startDelayCounter = 0;
			}
			else{
				startDelayCounter = 0;
				countDownNumber--;
			}
		}
		if(isStart){
			g2d.clearRect(0, 0, GameWindow.screenWidth, GameWindow.screenHeight);
			gameBackground.draw(g2d);
			gameBackground.updateBackground();
			for(IRenderable object : RenderableHolder.getInstance().getRenderableList()) {
				object.draw(g2d);
			}
		}
	}
}
