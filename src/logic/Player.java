package logic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import gui.InputUtility;

public class Player extends Character {
	
	private final int defaultSpeedY = -15; // set it later
	private double rotateDegree = 0;
	
	public Player(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}

	public Player(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp, image);
		// TODO Auto-generated constructor stub
		
	}
	
	
	public void fire() {
	//	new Bullet(this , bulletSpeed , InputUtility.getMouseX() , InputUtility.getMouseY() , 0 ,0);
	}
	
	@Override
	public void update()
	{
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))
		{
			this.speedY = defaultSpeedY;
		}
		if (InputUtility.isMouseLeftClicked())
		{
			fire();
		}
		x += speedX;
		y += speedY/2;
		speedX += accelX;
		speedY += accelY;
		//rotateDegree = speedY * 2; // set it later
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//AffineTransform a = new AffineTransform();
		//a.rotate(rotateDegree);
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}
}
	