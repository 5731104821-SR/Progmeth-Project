package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.GameWindow;
import res.Resource;

public class Explosion extends Enemy {
	
	protected boolean isExplode = false;
	private int explodeTime = 100;
	private int explodeCount = 0;
	
	public Explosion(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,int score, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score, image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		super.update();
		if(explodeCount < explodeTime){
			explodeCount++;
			return;
		}
		else if (!isExplode)
		{
			isExplode = true;
			explodeCount = 0;
			explodeTime = 20;
		}
		else
		{
			this.isDestroyed = true;
		}
		
	}
	
	@Override
	public boolean collideWith(ScreenObject object)
	{
		return false;
	}
	
	public void draw(Graphics2D g)
	{
		if (!isExplode)
		{
			g.setColor(Color.BLACK);
			g.drawImage(Resource.bomb_size, null, (int)this.x, (int)this.y);
		}
		else
		{
			g.drawImage(Resource.bomb, null, (int)this.x, (int)this.y);
		}
	}
	
}
