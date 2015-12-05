package logic;

public class Bullet extends ScreenObject{

	public Bullet(double x, double y, double speedX, double speedY, double accelX, double accelY) {
		super(x, y, speedX, speedY, accelX, accelY);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
	}

	public Bullet(double x, double y, double speedX, double speedY) {
		super(x, y, speedX, speedY);
		// TODO Auto-generated constructor stub
		this.maxHp = 1;
		this.hp = 1;
	}
	
	public void hit(Character shooter)
	{
		
	}
	
}
