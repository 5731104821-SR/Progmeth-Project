package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.IRenderable;
import logic.RenderableHolder;
import res.Resource;

public class GameScreen extends JPanel{
	
	private GameBackground gameBackground;
	private int startDelayCounter = 0;
	private int startDelay = 30;
	private int countDownNumber = 4;
	public static boolean isStart = false;
	public static boolean isPaused = false;
	
	public GameScreen() {
		this.setPreferredSize(new Dimension(GameWindow.SCREEN_WIDTH , GameWindow.SCREEN_HEIGHT));
		gameBackground = new GameBackground();
		setDoubleBuffered(true);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), false);
				InputUtility.setKeyTriggered(e.getKeyCode(),false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(!InputUtility.getKeyPressed(KeyEvent.VK_SPACE) && isStart){
					InputUtility.setKeyPressed(e.getKeyCode(), true);
					InputUtility.setKeyTriggered(e.getKeyCode(),true);
				}
			}
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseLeftDown(false);
				InputUtility.setMouseLeftTriggered(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!InputUtility.isMouseLeftDown() && isStart){
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftTriggered(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		this.addMouseMotionListener(new MouseAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				InputUtility.setMouseX(e.getX());
				InputUtility.setMouseY(e.getY());
				
			}
			public void mouseDragged(MouseEvent e)
			{
				InputUtility.setMouseX(e.getX());
				InputUtility.setMouseY(e.getY());
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(!isStart){
			g2d.clearRect(0, 0, GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);
			g2d.setComposite(gameBackground.transcluentBlack);
			gameBackground.draw(g2d);
			g2d.drawImage(Resource.character, null, 60, 60);
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
			g2d.clearRect(0, 0, GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);
			gameBackground.draw(g2d);
			gameBackground.update();
			for(IRenderable object : RenderableHolder.getInstance().getRenderableList()) {
				object.draw(g2d);
			}
		}
	}
}
