package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.GameWindow;
import gui.InputUtility;
import res.Resource;

public class NormalEnemy extends Enemy{
	
	private int shootDelayCounter = 0;
	private int shootDelay = 100;

	public NormalEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		super.update();
		if(this.x < GameWindow.SCREEN_WIDTH - 130){
			this.speedX = 0;
		}
		if(shootDelayCounter < shootDelay){
			shootDelayCounter++;
			return;
		}
		shoot(GameLogic.getInstance().player.x , GameLogic.getInstance().player.y);
		shootDelayCounter = 0;
		
	}
	
	public void shoot(double targetX , double targetY){
		Bullet b = new Bullet(this , 8 , targetX , targetY , 0 ,0 , Resource.bullet_lemon);
		RenderableHolder.getInstance().add(b);
		GameLogic.screenObjects.add(b);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}

}
