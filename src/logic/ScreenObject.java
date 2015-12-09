package logic;

public abstract class ScreenObject implements IRenderable{
	protected double x;
	protected double y;
	protected int maxHp;
	protected int hp;
	protected double speedX;
	protected double speedY;
	protected double accelX;
	protected double accelY;
	private boolean isDestroyed;
	private boolean isVisible;
	
	public ScreenObject(double x,double y,double speedX,double speedY,double accelX,double accelY,int maxHp)
	{
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
	
	public ScreenObject(double x,double y,double speedX,double speedY,double accelX,double accelY)
	{
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = accelX;
		this.accelY = accelY;

		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject(double x,double y,double speedX,double speedY ,int maxHp)
	{
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
	
	public ScreenObject(double x,double y,double speedX,double speedY)
	{
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
	}

	
	public ScreenObject(double x,double y)
	{
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.speedX = 0;
		this.speedY = 0;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
	}
	
	public ScreenObject()
	{
		this.x = 0;
		this.y = 0;
		this.speedX = 0;
		this.speedY = 0;
		this.accelX = 0;
		this.accelY = 0;
		
		this.isDestroyed = false;
		this.isVisible = true;
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
<<<<<<< HEAD
		y += speedY;
=======
		y += speedY/2;
>>>>>>> a766d2a2ca3c92d1acdbd48e2e6d8bd5cb367052
		speedX += accelX;
		speedY += accelY;
	}
	
	public boolean isVisible()
	{
<<<<<<< HEAD
		return isVisible;
=======
		return true;
>>>>>>> a766d2a2ca3c92d1acdbd48e2e6d8bd5cb367052
	}

}
