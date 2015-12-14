package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.GameScreen;
import gui.GameWindow;
import res.Resource;

public class BossStatus implements IRenderable{
	
	private int maxHpBar;

	public BossStatus(){
		this.maxHpBar = GameWindow.SCREEN_WIDTH;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE-1;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//Do not remove if, this will cause NullPointerException
		if(GameLogic.getInstance().isBossAppeared && !GameScreen.isWin){
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 40, maxHpBar, 20);
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(2, 42, (GameLogic.getInstance().boss.hp*(maxHpBar-4))/GameLogic.getInstance().boss.maxHp, 16);
			g2d.setColor(Color.GREEN);
			g2d.setFont(Resource.hpFont);
			g2d.drawString("BOSS HP", 10, 55);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
