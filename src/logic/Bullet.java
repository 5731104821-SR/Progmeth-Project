package logic;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bullet extends ScreenObject{

	protected Character shooter;
	
	public Bullet(double x, double y, double speedX, double speedY, double accelX, double accelY,Character shooter, double bulletSpeed , BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY ,image);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
		this.shooter = shooter;
	}

	public Bullet(double x, double y, double speedX, double speedY, Character shooter , BufferedImage image) {
		super(x, y, speedX, speedY ,image);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
		this.shooter = shooter;
	}
	
	public Bullet(Character shooter, double bulletSpeed, double targetX,double targetY, double accelX, double accelY , BufferedImage image) 
	// this will shoot the bullet from shooter to target position with custom acceleration (for making parabola curve attack)
	{
		super(image);
		this.shooter = shooter;
		if (shooter instanceof Player) this.x = shooter.x + shooter.getImage().getWidth()/8;
		else this.x = shooter.x + shooter.getImage().getWidth()/2;
		this.y = shooter.y + shooter.getImage().getHeight()/2;
		calculateSpeed(shooter.x, shooter.y, targetX, targetY, bulletSpeed, 0);
		
		this.accelX = accelX;
		this.accelY = accelY;
		
	}
	
	public Bullet(Character shooter, double bulletSpeed, double targetX,double targetY, double bulletAccel , BufferedImage image) // use this
	// this will shoot the bullet from shooter to target position with fixed direction acceleration
	{
		super(image);
		this.shooter = shooter;
		if (shooter instanceof Player) this.x = shooter.x + shooter.getImage().getWidth()/8;
		else this.x = shooter.x + shooter.getImage().getWidth()/2;
		this.y = shooter.y + shooter.getImage().getHeight()/2;
		calculateSpeed(shooter.x, shooter.y, targetX, targetY, bulletSpeed, bulletAccel);
		
	}
	
	private void calculateSpeed(double initX,double initY,double targetX,double targetY,double bulletSpeed,double bulletAccel)
	{
		// calculate initial speedX and speedY from shooter position, target position and bullet speed
		double theta; // angle between shooter position and target point position
		if (initX != targetX) theta = Math.atan((initY - targetY)/(targetX - initX)); // don't forget that the x,y system of Java graphic is different from Math x,y co-ordinate
		else theta = (Math.PI)/2; // tan(theta) = infinity when divided by ZERO! Then theta = 90 or 270 degree
		// since arctan function have range only from pi/2 to -pi/2, we will have to find the REAL direction later
		// I mean, there will be a case that theta is more than pi/2 or less than -pi/2 (when you shoot from right to left)
		//System.out.println("degree = " + theta);
		if (initX < targetX)
		{
			this.speedX = bulletSpeed * Math.cos(theta);
			this.accelX = bulletAccel * Math.cos(theta);
		}
		else
		{
			this.speedX = -(bulletSpeed * Math.cos(theta));
			this.accelX = -bulletAccel * Math.cos(theta);
		}
		if (initX > targetX)
		{
			this.speedY = bulletSpeed * Math.sin(theta);
			this.accelY = bulletSpeed * Math.sin(theta);
		}
		else 
		{
			this.speedY = -bulletSpeed * Math.sin(theta);
			this.accelY = -bulletAccel * Math.sin(theta);
		}
	}
	
	@Override
	public void update()
	{
		super.update();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}

}