package logic;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Character {
	
	private final int defaultSpeedY = 10; // set it later
	private double rotateDegree = 0;
	public Player(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}

	public Player(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp, image);
		// TODO Auto-generated constructor stub
		
	}
	
	
	@Override
	public void update()
	{
		rotateDegree = speedY * 2; // set it later
		if (InputUtility.getKeyPressed(VK_SPACE))
		{
			this.speedY = defaultSpeedY;
		}
		if (InputUtility.LeftMouseClick)
		{
			fire();
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		AffineTransform a = new AffineTransform();
		a.rotate(rotateDegree);
	}
	
}
