package logic;

import java.awt.image.BufferedImage;

public abstract class ScreenObject implements IRenderable{
	
	protected double x;
	protected double y;
	protected int maxHp;
	protected int hp;
	protected double speedX;
	protected double speedY;
	protected double accelX;
	protected double accelY;
	protected boolean isDestroyed;
	private boolean isVisible;
	private BufferedImage image;
	
	public ScreenObject(double x,double y,double speedX,double speedY,double accelX,double accelY,int maxHp, BufferedImage image)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = accelX;
		this.accelY = accelY;

		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject(double x,double y,double speedX,double speedY,double accelX,double accelY, BufferedImage image)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = accelX;
		this.accelY = accelY;

		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject(double x,double y,double speedX,double speedY ,int maxHp, BufferedImage image)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = 0;
		this.accelY = 0;
		
		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject(double x,double y,double speedX,double speedY,BufferedImage image)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
	}

	
	public ScreenObject(double x,double y,BufferedImage image)
	{
		// TODO Auto-generated constructor stub
		this.image = image;
		this.x = x;
		this.y = y;
		this.speedX = 0;
		this.speedY = 0;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject(BufferedImage image)
	{
		this.image = image;
		this.x = 0;
		this.y = 0;
		this.speedX = 0;
		this.speedY = 0;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public int getZ()
	{
		return 0;
	}
	
	public void update()
	{
		x += speedX;
		y += speedY;
		speedX += accelX;
		speedY += accelY;
	}
	
	public boolean isVisible()
	{
		return isVisible;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

}
