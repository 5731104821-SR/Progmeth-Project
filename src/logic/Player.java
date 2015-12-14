package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import gui.GameScreen;
import gui.GameWindow;
import gui.InputUtility;
import res.Resource;
import sun.audio.AudioPlayer;
import sun.java2d.pipe.BufferedBufImgOps;

public class Player extends Character {
	
	private final int defaultSpeedY = -7; // set it later
	private double rotateDegree = 0;
	private int currentFrame , frameWidth ,frameDelayCounter;
	private final int frameDelay = 10;
	private int gameOverDelay = 0;
	private boolean lastBounce = false; //bounce up when game over 
	
	private int invulDelayCounter = 200;
	private int invulDelay = 200;
	
	public Player(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
		// TODO Auto-generated constructor stub
		this.currentFrame = 0;
		this.frameWidth = image.getWidth()/4;
		this.frameDelayCounter = 0;
	}
	
	public void shoot() {
		if(!GameScreen.isWin){
			Bullet b = new Bullet(this , 10 , InputUtility.getMouseX() , InputUtility.getMouseY() , 0 , 0 , Resource.bullet_cake);
			RenderableHolder.getInstance().add(b);
			GameLogic.screenObjects.add(b);
			Resource.shoot.play();
		}
	}
	
	public void hit(){
		if (invulDelayCounter >= invulDelay)
		{
			this.hp--;
			
			if(this.hp==0){
				GameScreen.isGameOver = true;
			}
			invulDelayCounter = 0;
		}
	}
	
	public void gameOver(){
		if(gameOverDelay < 80){
			gameOverDelay++;
			return;
		}
		if(!lastBounce){
			this.speedY = -10;
			lastBounce = true;
		}
		rotateDegree+= 0.03;
		x += speedX;
		y += speedY;
		speedX += accelX;
		speedY += accelY;
		if(this.y > GameWindow.SCREEN_HEIGHT + 800){
			GameScreen.gameOverScreen = true;
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		if (invulDelayCounter < invulDelay) invulDelayCounter++;
		if(this.y < 40){
			this.y = 40;
		}
		if(this.y > GameWindow.SCREEN_HEIGHT-100){
			this.y = GameWindow.SCREEN_HEIGHT-100;
			this.hp = 0;
			GameScreen.isGameOver = true;
			
		}
		if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE))
		{
			this.speedY = defaultSpeedY;
			//System.out.println("jump");
			Resource.jump.play();
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
		return Math.hypot(this.x+this.getImage().getWidth()/8 - object.x-object.getImage().getWidth()/2, this.y+this.getImage().getHeight()/2 - object.y-object.getImage().getHeight()/2) <= this.getImage().getWidth()/8 + object.getImage().getWidth()/2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotateDegree,this.getImage().getWidth()/8 , this.getImage().getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		if (invulDelayCounter % 10 < 7) g2d.drawImage(op.filter(getImage().getSubimage(frameWidth*currentFrame, 0, frameWidth, getImage().getHeight()),null), null, (int)this.x, (int)this.y);

		
	}
}
	
