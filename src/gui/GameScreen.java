package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logic.IRenderable;
import logic.RenderableHolder;
import res.Resource;

public class GameScreen extends JPanel{
	
	private GameBackground gameBackground;
	private RenderableHolder holder;
	private int startDelayCounter = 0;
	private int startDelay = 60;
	private int countDownNumber = 4;
	private boolean isStart = false;
	
	public GameScreen(RenderableHolder renderableHolder) {
		this.setPreferredSize(new Dimension(GameWindow.screenWidth , GameWindow.screenHeight));
		gameBackground = new GameBackground();
		this.holder = renderableHolder;
		setDoubleBuffered(true);
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
			g2d.drawImage(Resource.character, null, 30, 220);
			for(IRenderable object : RenderableHolder.getInstance().getRenderableList()) {
				if(object.isVisible()) {
					object.draw(g2d);
				}
			}
		}
	}
}
