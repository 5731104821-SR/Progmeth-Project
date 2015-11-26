package logic;

public abstract class ScreenObject implements IRenderable{
	private double x;
	private double y;
	private int maxHp;
	private int hp;
	private double speedX;
	private double speedY;
	private boolean isDestroyed;
	
	public ScreenObject(double x,double y,double speedX,double speedY,int maxHp)
	{
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;

		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.isDestroyed = false;
	}
	
	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public void update()
	{
		x += speedX;
		y += speedY;
	}
	
	public boolean isVisible()
	{
		return isDestroyed;
	}

}
