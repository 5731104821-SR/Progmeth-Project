package logic;

import java.awt.Graphics2D;

public class Bullet extends ScreenObject{

	private Character shooter;
	private double bulletSpeed; // the initialize bullet speed
	
	public Bullet(double x, double y, double speedX, double speedY, double accelX, double accelY,Character shooter, double bulletSpeed) {
		super(x, y, speedX, speedY, accelX, accelY);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
		this.shooter = shooter;
	}

	public Bullet(double x, double y, double speedX, double speedY, Character shooter) {
		super(x, y, speedX, speedY);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
		this.shooter = shooter;
	}
	
	public Bullet(Character shooter, double bulletSpeed, double targetX,double targetY, double accelX, double accelY) // use this
	// this will shoot the bullet from shooter to target position
	{
		super();
		this.shooter = shooter;
		this.x = shooter.x;
		this.y = shooter.y;
		this.bulletSpeed = bulletSpeed;
		this.accelX = accelX;
		this.accelY = accelY;
		
		// calculate initial speedX and speedY from shooter position, target position and bullet speed
		double theta; // angle between shooter position and target point position
		if (shooter.x != targetX) theta = Math.atan((shooter.y - targetY)/(targetX - shooter.x)); // don't forget that the x,y system of Java graphic is different from Math x,y co-ordinate
		else theta = (Math.PI)/2; // tan(theta) = infinity when divided by ZERO! Then theta = 90 or 270 degree
		// since arctan function have range only from pi/2 to -pi/2, we will have to find the REAL direction later
		// I mean, there will be a case that theta is more than pi/2 or less than -pi/2 (when you shoot from right to left)
		if (shooter.x < targetX)
		{
			this.speedX = bulletSpeed * Math.cos(theta);
		}
		else this.speedX = -(bulletSpeed * Math.cos(theta));
		if (shooter.y > targetY)
		{
			this.speedY = bulletSpeed * Math.sin(theta);
		}
		else this.speedY = -(bulletSpeed * Math.sin(theta));
	}
	
	public void hit()
	{
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
}