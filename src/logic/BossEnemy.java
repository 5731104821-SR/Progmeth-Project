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
	private int attackDelay2 = 600;
	private int attackDelay3 = 220;
	private int attackDelay4 = 70;
	private int attackType = 0; // 0 = delay, 1-5 = normal attack, 99 = wait for
								// ultimate, 999 = ultimate

	private int shootDelay = 0;
	private int shootDelayCounter = 30;
	private int shootCount = 0;
	private int shootAmount = 20;
	private int bulletY = 0;
	private int speedChange = 85;

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
		else if (!GameScreen.isWin)
		{
			if (this.x < GameWindow.SCREEN_WIDTH - 280)
			{
				this.speedX = 0;
			}
			if (attackType == 0)
			{
				if (attackDelayCounter < attackDelay)
				{
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
						Bullet b = new Bullet(this,5,60,bulletY,0,0,Resource.bullet_lemon);
						RenderableHolder.getInstance().add(b);
						GameLogic.screenObjects.add(b);
						bulletY += speedChange;
						if (bulletY > 400) bulletY = 0;
						shootCount++;
					}
				}
				else
				{
					attackType = 0;
					shootDelay = 0;
					Bullet b = new Bullet(this,5,60,bulletY,0,0,Resource.bullet_lemon);
					RenderableHolder.getInstance().add(b);
					GameLogic.screenObjects.add(b);
					bulletY += speedChange;
					if (bulletY > 400) bulletY = 0;
					shootCount++;
				}
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
					Enemy b = new Explosion(GameLogic.getInstance().player.x, GameLogic.getInstance().player.y, 0, 0, 1, Resource.bomb_size);
					RenderableHolder.getInstance().add(b);
					GameLogic.screenObjects.add(b);
					bulletY += speedChange;
					if (bulletY > 400) bulletY = 0;
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
	public boolean collideWith(ScreenObject object) {
		return object.x - this.x > 50 && object.x - this.x < 250 && object.y - this.y > -30 && object.y - this.y < 270;
	}
	
	public void randAttack()
	{
		//int attackRand = (int)(Math.random() * 100);
		int attackRand = 55;
		if (attackRand < 20)
		{
			attackType = 1;
			attackDelay = attackDelay1;
			shootDelay = 0;
			shootDelayCounter = 25;

			shootCount = 0;
			shootAmount = 18;
			bulletY = 0;
			speedChange = 75;
		} else if (attackRand < 40) {
			attackType = 2;
			attackDelay = attackDelay2;
		}
		else if (attackRand < 60)
		{
			attackType = 3;
			attackDelay = attackDelay3;
			
			shootDelay = 0;
			shootDelayCounter = 40;
			shootCount = 0;
			shootAmount = 4;
		}
		else if (attackRand < 80)
		{
			attackType = 1;
			attackDelay = attackDelay1;
		} else {
			attackType = 1;
			attackDelay = attackDelay1;
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
			} else if (this.hp < maxHp / 2) {
				g2d.drawImage(Resource.boss_rage, null, (int) this.x, (int) this.y);
			} else {
				g2d.drawImage(this.getImage(), null, (int) this.x, (int) this.y);
			}
		}
	}

}
