package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.Resource;

public class HomingEnemy extends Enemy{

	public HomingEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, int score,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score, image);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		super.update();
		if(GameLogic.getInstance().player.y < this.y){
			speedY = -1;
		}
		else{
			speedY = 1;
		}
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(this.x >= 450){
			g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
		}
		else{
			g2d.drawImage(Resource.tomato_angry, null, (int)this.x, (int)this.y);
		}
	}

}
