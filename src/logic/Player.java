package logic;

<<<<<<< HEAD
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Character {
	
	private final int defaultSpeedY = 10; // set it later
	private double rotateDegree = 0;
=======
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import gui.InputUtility;

public class Player extends Character {
	
	private final int defaultSpeedY = -15; // set it later
	private double rotateDegree = 0;
	
>>>>>>> a766d2a2ca3c92d1acdbd48e2e6d8bd5cb367052
	public Player(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}

	public Player(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp, image);
		// TODO Auto-generated constructor stub
		
	}
	
	
<<<<<<< HEAD
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
=======
	public void fire() {
		Bullet b = new Bullet(this , 3 , InputUtility.getMouseX() , InputUtility.getMouseY() , 0 ,0);
		RenderableHolder.getInstance().add(b);
		GameLogic.bullets.add(b);
	}
	
	@Override
	public void update()
	{
		super.update();
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))
		{
			this.speedY = defaultSpeedY;
		}
		if (InputUtility.isMouseLeftClicked())
		{
			fire();
		}
		//x += speedX;
		//y += speedY/2;
		//speedX += accelX;
		//speedY += accelY;
		//rotateDegree = speedY * 2; // set it later
>>>>>>> a766d2a2ca3c92d1acdbd48e2e6d8bd5cb367052
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		AffineTransform a = new AffineTransform();
		a.rotate(rotateDegree);
	}
	
}
=======
		//AffineTransform a = new AffineTransform();
		//a.rotate(rotateDegree);
		g2d.setColor(Color.RED);
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}
}
	
>>>>>>> a766d2a2ca3c92d1acdbd48e2e6d8bd5cb367052
