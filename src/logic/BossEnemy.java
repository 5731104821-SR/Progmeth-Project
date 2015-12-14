package logic;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gui.GameScreen;
import gui.GameWindow;
import res.RandomUtility;
import res.Resource;

public class BossEnemy extends Enemy {

	private int attackDelayCounter = 0;
	private int attackDelay = 70;
	private int attackDelay1 = 100;
	private int attackDelay2 = 500;
	private int attackDelay3 = 160;
	private int attackDelay4 = 300;
	private int attackDelay5 = 100;
	private int attackType = 0; // 0 = delay, 1-5 = normal attack, 99 = wait for
								// ultimate, 999 = ultimate
	private boolean isUltimate = false; // check whether is boss already used ultimate or not
	private int animationDelayCounter = 0; // animation delay for blinking boss
	private int animationDelay = 10;

	private int shootDelay = 0;
	private int shootDelayCounter = 30;
	private int shootCount = 0;
	private int shootAmount = 20;
	private int bulletY = 0;
	private int speedChange = 85;
	private int multiplier = -1;

	private double rotateDegree = 0;
	private int flyDelay = 130;
	private int flyDelayCounter = 0;

	

	public BossEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,
			int score, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score, image);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		//
		super.update();
		if (GameScreen.isWin)
		{
			if (flyDelayCounter < flyDelay)
			{
				flyDelayCounter++;
				return;
			}
			this.speedX = 4;
			this.speedY = -4;
			this.rotateDegree += 0.10;
		}
		else 
		{
			if (this.x < GameWindow.SCREEN_WIDTH - 280)
			{
				this.speedX = 0;
			}
			if (this.hp <= this.maxHp * 2 / 10 && !isUltimate)
			{
				attackType = 99;
				attackDelay = 399;
				attackDelayCounter = 0;
				isUltimate = true;
			}
			if (attackType == 0)
			{
				if (attackDelayCounter < attackDelay)
				{
					attackDelayCounter++;
					return;
				}
				System.out.println(attackDelayCounter);
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
						Bullet b = new Bullet(this,6,80,bulletY,0,0,Resource.bullet_lemon);
						RenderableHolder.getInstance().add(b);
						GameLogic.screenObjects.add(b);
						bulletY += speedChange;
						if (bulletY > 380) bulletY = 0;
						speedChange = (int) (Math.random() * 55) + 25;
						shootCount++;
					}
				}
				else
				{
					attackType = 0;
				}
			} 
			else if (attackType == 2)
			{
				int randAttackSpeed = (int) (Math.random() * 200) + 80;
				Enemy e = new ShootingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -6, 0, 0, 0, 7, 10,
						Resource.lemon, -0.4, randAttackSpeed, 270, 6);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);

				randAttackSpeed = (int) (Math.random() * 200) + 80;
				e = new ShootingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -6, 0, 0, 0, 7, 10,
						Resource.lemon, -0.4, randAttackSpeed, 270, 6);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);

				randAttackSpeed = (int) (Math.random() * 200) + 80;
				e = new ShootingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -6, 0, 0, 0, 7, 10,
						Resource.lemon, -0.4, randAttackSpeed, 270, 6);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);

				randAttackSpeed = (int) (Math.random() * 200) + 80;
				e = new ShootingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -6, 0, 0, 0, 7, 10,
						Resource.lemon, -0.4, randAttackSpeed, 270, 6);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);

				attackType = 0;
			}
			else if (attackType == 3)
			{
				if(shootCount < shootAmount)
				{
					if (shootDelay < shootDelayCounter) shootDelay++;
					else
					{
						shootDelay = 0;
						Enemy b = new Explosion(GameLogic.getInstance().player.x, GameLogic.getInstance().player.y, 0.0, 0.0, 0.0 ,0.0 , 1, 0, Resource.bomb_size);
						RenderableHolder.getInstance().add(b);
						GameLogic.screenObjects.add(b);
						shootCount++;
					}
				}
				else
				{
					attackType = 0; // finish attack
				}
			}
			else if (attackType == 4)
			{
				Enemy e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 5, 5,Resource.tomato);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);
				
				e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 5, 5,Resource.tomato);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);
				
				e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 5, 5,Resource.tomato);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);
				
				e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 5, 5,Resource.tomato);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);
				
				e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 5, 5,Resource.tomato);
				GameLogic.screenObjects.add(e);
				RenderableHolder.getInstance().add(e);
				
				attackType = 0;
			}
			else if (attackType == 5)
			{
				if(shootCount < shootAmount)
				{
					if (shootDelay < shootDelayCounter) shootDelay++;
					else
					{
						shootDelay = 0;
						Enemy b = new Explosion(GameLogic.getInstance().player.x, bulletY, 0.0, 0.0, 0.0 ,0.0 , 1, 0, Resource.bomb_size);
						bulletY += 30;
						RenderableHolder.getInstance().add(b);
						GameLogic.screenObjects.add(b);
						shootCount++;
					}
				}
				else
				{
					attackType = 0;
				}
			}
			else if (attackType == 99)
			{
				if (attackDelayCounter < attackDelay)
				{
					attackDelayCounter++;
					return;
				}
				System.out.println("ULTIMATE!");
				attackType = 999;
				attackDelayCounter = 0;
				attackDelay = 150;
				
				shootDelay = 0;
				shootDelayCounter = 20;

				shootCount = 0;
				shootAmount = 99;
				bulletY = 380;
				multiplier = -1;
				speedChange = ((int) (Math.random() * 65) + 40) * multiplier;
			}
			else if (attackType == 999)
			{
				if(shootCount < shootAmount)
				{
					if (shootDelay < shootDelayCounter) shootDelay++;
					else
					{
						shootDelay = 0;
						Bullet b = new Bullet(this,6,80,bulletY,0,0,Resource.bullet_lemon);
						RenderableHolder.getInstance().add(b);
						GameLogic.screenObjects.add(b);
						bulletY += speedChange;
						if ((bulletY >= 380 && multiplier == 1) || (bulletY <= 0 && multiplier == -1)) multiplier *= -1; 
						speedChange = ((int) (Math.random() * 65) + 40) * multiplier;
						shootCount++;
						if (shootCount % 7 == 6)
						{
							Enemy e = new Explosion(GameLogic.getInstance().player.x, GameLogic.getInstance().player.y, 0.0, 0.0, 0.0 ,0.0 , 1, 0, Resource.bomb_size);
							RenderableHolder.getInstance().add(e);
							GameLogic.screenObjects.add(e);
						}
					}
				}
				else
				{
					attackType = 0;
				}
			}
		}
	}
	
	@Override
	public void hit()
	{
		if (!(attackType == 99 || attackType == 999))
		{
			super.hit();
		}
	}

	@Override
	public boolean collideWith(ScreenObject object) {
		return object.x - this.x > 50 && object.x - this.x < 250 && object.y - this.y > -30 && object.y - this.y < 270;
	}

	public void randAttack() {
		int attackRand = (int)(Math.random() * 100);
		if (attackRand < 20) {
			attackType = 1;
			attackDelay = attackDelay1;
			shootDelay = 0;
			shootDelayCounter = 18;

			shootCount = 0;
			shootAmount = (int)(Math.random() * 10) + 14;
			bulletY = 0;
			speedChange = (int) (Math.random() * 55) + 25;
		} else if (attackRand < 40) {
			attackType = 2;
			attackDelay = attackDelay2;
		} else if (attackRand < 60) {
			attackType = 3;
			attackDelay = attackDelay3;

			shootDelay = 0;
			shootDelayCounter = 40;
			shootCount = 0;
			shootAmount = 6;
		} else if (attackRand < 80) {
			attackType = 4;
			attackDelay = attackDelay4;
			
		} else {
			attackType = 5;
			attackDelay = attackDelay5;
			shootDelay = 0;
			shootDelayCounter = 18;

			shootCount = 0;
			shootAmount = 10;
			bulletY = 40;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if (GameScreen.isWin && this.hp <= 0) {
			AffineTransform tx = AffineTransform.getRotateInstance(rotateDegree, this.getImage().getWidth() / 2,
					this.getImage().getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(Resource.boss_dead, null), null, (int) this.x, (int) this.y);
		}
		if (!GameScreen.isWin) {
			if (this.hp <= 0) {
				g2d.drawImage(Resource.boss_dead, null, (int) this.x, (int) this.y);
			} else if (attackType == 99 || attackType == 999) {
				if (animationDelayCounter < animationDelay)
				{
					animationDelayCounter++;
					g2d.drawImage(Resource.boss_rage, null, (int) this.x, (int) this.y);
				}
				else if (animationDelayCounter >= animationDelay && animationDelay == 10)
				{
					animationDelay = 0;
					g2d.drawImage(Resource.boss_rage, null, (int) this.x, (int) this.y);
				}
				else if (animationDelayCounter > animationDelay)
				{
					animationDelayCounter--;
					g2d.drawImage(Resource.boss_rage_gold, null, (int) this.x, (int) this.y);
				}
				else if (animationDelayCounter <= animationDelay && animationDelay == 0)
				{
					animationDelay = 10;
					g2d.drawImage(Resource.boss_rage_gold, null, (int) this.x, (int) this.y);
				}
				
			} else if (this.hp < maxHp / 2) {
				g2d.drawImage(Resource.boss_rage, null, (int) this.x, (int) this.y);
			} else {
				g2d.drawImage(this.getImage(), null, (int) this.x, (int) this.y);
			}
		}
	}

}
