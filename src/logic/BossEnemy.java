package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.GameWindow;
import res.Resource;

public class BossEnemy extends Enemy{
	
	private int attackDelayCounter = 0;
	private int attackDelay = 70;
	private int attackDelay1 = 70;
	private int attackDelay2 = 70;
	private int attackDelay3 = 70;
	private int attackDelay4 = 70;
	private int attackType = 0; // 0 = delay, 1-5 = normal attack, 99 = wait for ultimate, 999 = ultimate
	
	private int shootDelay = 0;
	private int shootDelayCounter = 30;
	private int shootCount = 0;
	private int shootAmount = 30;
	private int bulletY = 0;
	private int speedChange = 85;
	

	public BossEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, int score,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score, image);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		//
		super.update();
		if(this.x < GameWindow.SCREEN_WIDTH - 280){
			this.speedX = 0;
		}
		
		if (attackType == 0)
		{
			if(attackDelayCounter < attackDelay){
				attackDelayCounter++;
				return;
			}
			randAttack();
			attackDelayCounter = 0;
			
		}
		else if (attackType == 1)
		{
			if(shootCount < shootAmount)
			{
				if (shootDelay < shootDelayCounter) shootDelay++;
				else
				{
					shootDelay = 0;
					Bullet b = new Bullet(this,5,0,bulletY,0,0,Resource.bullet_lemon);
					RenderableHolder.getInstance().add(b);
					GameLogic.screenObjects.add(b);
					bulletY += speedChange;
					bulletY %= 400;
					shootCount++;
				}
			}
			else
			{
				attackType = 0; // finish attack
			}
		}
	}
	
	@Override
	public boolean collideWith(ScreenObject object){
		return object.x - this.x > 50 && object.x - this.x < 250 && object.y - this.y > -30 && object.y - this.y < 270;
	}
	
	public void randAttack()
	{
		int attackRand = (int)(Math.random() * 100);
		if (attackRand < 20)
		{
			attackType = 1;
			attackDelay = attackDelay1;
			shootDelay = 0;
			shootDelayCounter = 30;
			 
			shootCount = 0;
			shootAmount = 30;
			bulletY = 0;
			speedChange = 85;
		}
		else if (attackRand < 40)
		{
			attackType = 1;
			attackDelay = attackDelay1;
		}
		else if (attackRand < 60)
		{
			attackType = 1;
			attackDelay = attackDelay1;
		}
		else if (attackRand < 80)
		{
			attackType = 1;
			attackDelay = attackDelay1;
		}
		else
		{
			attackType = 1;
			attackDelay = attackDelay1;
		}
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(this.hp==0){
			g2d.drawImage(Resource.boss_dead, null, (int)this.x, (int)this.y);
		}
		else if(this.hp < maxHp/2){
			g2d.drawImage(Resource.boss_rage, null, (int)this.x, (int)this.y);
		}
		else{
			g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
		}
	}

}
