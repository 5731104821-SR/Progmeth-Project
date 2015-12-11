package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import gui.GameWindow;
import gui.InputUtility;
import res.Resource;

public class Player extends Character {
	
	private final int defaultSpeedY = -7; // set it later
	private double rotateDegree = 0;
	private int currentFrame , frameWidth ,frameDelayCounter;
	private final int frameDelay = 10; 
	
	public Player(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
		this.currentFrame = 0;
		this.frameWidth = image.getWidth()/4;
		this.frameDelayCounter = 0;
	}

	public Player(double x, double y, double speedX, double speedY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, maxHp, image);
		// TODO Auto-generated constructor stub
	}
	
	public void shoot() {
		Bullet b = new Bullet(this , 10 , InputUtility.getMouseX() , InputUtility.getMouseY() , 0 ,0 , Resource.bullet_cake);
		RenderableHolder.getInstance().add(b);
		GameLogic.screenObjects.add(b);
	}
	
	public void hit(){
		System.out.println("hit");
	}
	
	@Override
	public void update()
	{
		super.update();
		if(this.y < 0){
			this.y = 0;
		}
		if(this.y > GameWindow.SCREEN_HEIGHT-100){
			this.y = GameWindow.SCREEN_HEIGHT-100;
		}
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))
		{
			this.speedY = defaultSpeedY;
			//System.out.println("jump");
		}
		if (InputUtility.isMouseLeftClicked())
		{
			shoot();
		}
		if(frameDelayCounter < frameDelay){
			frameDelayCounter++;
			return;
		}
		currentFrame++;
		if(currentFrame == 4){
			currentFrame = 0;
		}
		frameDelayCounter = 0;
		//rotateDegree = speedY * 2; // set it later
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean collideWith(ScreenObject object) {
		// TODO Auto-generated method stub
		return Math.hypot(this.x - object.x, this.y - object.y) <= this.getImage().getWidth()/8 + object.getImage().getWidth()/2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//AffineTransform a = new AffineTransform();
		//a.rotate(rotateDegree);
		g2d.drawImage(getImage().getSubimage(frameWidth*currentFrame, 0, frameWidth, getImage().getHeight()), null, (int)this.x, (int)this.y);
	}
}
	
