package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import res.Resource;
import sun.audio.AudioPlayer;

public class GameTitle extends JPanel{
	
	private GameBackground gameBackground;
	private JButton viewHighScore;
	
	public GameTitle() {
		gameBackground = new GameBackground();
		this.setPreferredSize(new Dimension(GameWindow.SCREEN_WIDTH,GameWindow.SCREEN_HEIGHT));
		this.setLayout(null);
		viewHighScore = new JButton(new ImageIcon(Resource.buttonImage));
		viewHighScore.setLocation(625 , 25);
		viewHighScore.setSize(160, 50);
		this.add(viewHighScore);
		Resource.playBGM(Resource.titleBGM);
		Resource.playBGM(Resource.playBGM);
        
		
		viewHighScore.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Resource.button_select.play();
				HighScoreUtility.displayTop10();
			}
		});
		
		viewHighScore.addKeyListener(new KeyListener() {
			
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
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					GameManager.newGame();
				}
			}
		});
		
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
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					GameManager.newGame();
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.clearRect(0, 0, GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);
		gameBackground.update();
		gameBackground.draw(g2d);
		g2d.drawImage(Resource.crown, null, 680, -20);
		g2d.drawImage(Resource.logo, null, 75, 20);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.titleFont);
		g2d.drawString("PRESS ENTER TO START", 150, 500);
		g2d.setColor(Color.BLUE);
		g2d.setFont(Resource.instructionFont);
		g2d.drawString("INSTRUCTION:", 320, 350);
		g2d.drawString("PRESS \"SPACE\" FOR JUMP", 270, 380);
		g2d.drawString("PRESS \"W\" FOR PAUSE", 285, 410);
		g2d.drawString("LEFT MOUSE CLICK FOR SHOOT!", 230, 440);
	}
		
}
