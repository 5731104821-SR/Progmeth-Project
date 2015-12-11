package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RushEnemy extends Enemy{

	public RushEnemy(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, int score,
			BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, score ,image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		super.update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(this.getImage(), null, (int)this.x, (int)this.y);
	}

}
