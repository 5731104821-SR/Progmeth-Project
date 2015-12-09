package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import res.Resource;

public class GameLogic {

	private static GameLogic instance = new GameLogic();
	protected Player player = new Player(30,30,0,0,0,1,1,Resource.character);
	protected static List<Bullet> bullets = new CopyOnWriteArrayList<>();
	private static final int SPAWN_DELAY = 100;
	private int spawnDelayCounter = 0;
	
	public static GameLogic getInstance() {
		return instance;
	}
	
	public GameLogic() {
		RenderableHolder.getInstance().add(player);
		//RenderableHolder.getInstance().add(playerStatus);
	}
	
	public void logicUpdate() {
		player.update();
		for(Bullet bullet : bullets){
			bullet.update();
		}
	}
}
