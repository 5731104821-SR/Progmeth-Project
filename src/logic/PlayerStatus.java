package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.GameWindow;
import res.Resource;

public class PlayerStatus implements IRenderable{

	private int score;
	private int maxHpBar;
	
	public PlayerStatus(){
		this.setScore(0);
		this.maxHpBar = 200;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GameWindow.SCREEN_WIDTH, 40);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Resource.statusFont);
		g2d.drawString("SCORE: " + this.getScore(), GameWindow.SCREEN_WIDTH - 185, 35);
		g2d.fillRect(10, 10, maxHpBar, 20);
		g2d.setColor(Color.RED);
		g2d.fillRect(12, 12, (GameLogic.getInstance().player.hp*(maxHpBar-4))/GameLogic.getInstance().player.maxHp, 16);
		g2d.setColor(Color.GREEN);
		g2d.setFont(Resource.hpFont);
		g2d.drawString("PLAYER HP", 20, 25);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public void addScore(int score){
		this.setScore(this.getScore() + score);
		System.out.println(this.getScore());
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
