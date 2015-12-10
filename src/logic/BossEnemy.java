package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.Resource;

public class BossEnemy extends Enemy{

	public BossEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		//
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
