package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Character{

	public Enemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}

	public Enemy(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp, image);
		// TODO Auto-generated constructor stub
		
	}

	public void hit(){
		
	}
}
