package logic;

import java.awt.image.BufferedImage;

public abstract class Character extends ScreenObject {
	
	private BufferedImage image;
	
	public Character(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp);
		// TODO Auto-generated constructor stub
		
		this.image = image;
	}

	public Character(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp);
		// TODO Auto-generated constructor stub
		
		this.image = image;
	}
	
	
	public BufferedImage getImage()
	{
		return image;
	}
	
}
