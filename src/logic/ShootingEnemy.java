package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.GameWindow;
import gui.InputUtility;
import res.Resource;

public class ShootingEnemy extends Enemy{
	
	private int shootDelayCounter = 0;
	private int shootDelay = 100;
	private int widthStop = 130;
	private double speedXAfter = 0; // the speed that lemon move after coming into screen
	private double bulletSpeed = 8;

	public ShootingEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,int score,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score , image);
		// TODO Auto-generated constructor stub
	}
	
	public ShootingEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,int score,
			BufferedImage image, double speedXAfter,int shootDelay, int widthStop, double bulletSpeed) { // for boss summoning
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score , image);
		// TODO Auto-generated constructor stub
		this.shootDelay = shootDelay;
		this.speedXAfter = speedXAfter;
		this.widthStop = widthStop;
		this.bulletSpeed = bulletSpeed;
	}
	
	@Override
	public void update(){
		super.update();
		if(this.x < GameWindow.SCREEN_WIDTH - widthStop){
			this.speedX = speedXAfter;
		}
		if(shootDelayCounter < shootDelay){
			shootDelayCounter++;
			return;
		}
		shoot(GameLogic.getInstance().player.x , GameLogic.getInstance().player.y);
		shootDelayCounter = 0;
		
	}
	
	public void shoot(double targetX , double targetY){
		Bullet b = new Bullet(this , bulletSpeed , targetX , targetY , 0 ,0 , Resource.bullet_lemon);
		RenderableHolder.getInstance().add(b);
		GameLogic.screenObjects.add(b);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}

}
