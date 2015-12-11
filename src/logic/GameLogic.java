package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import gui.GameWindow;
import res.RandomUtility;
import res.Resource;

public class GameLogic {

	private static GameLogic instance = new GameLogic();
	protected Player player = new Player(60, 60, 0, 0, 0, 0.25, 1, Resource.character);
	protected static List<ScreenObject> screenObjects = new CopyOnWriteArrayList<>();
	private int spawnDelay = 250;
	private int spawnDelayCounter = 0;
	protected int enemyCount = 0;
	private boolean isBossAppeared = false;

	public static GameLogic getInstance() {
		return instance;
	}

	public GameLogic() {
		RenderableHolder.getInstance().add(player);
		// RenderableHolder.getInstance().add(playerStatus);
	}

	public void logicUpdate() {
		player.update();
		
		for (ScreenObject object : screenObjects) {
			if (object.isDestroyed()) {
				screenObjects.remove(object);
				RenderableHolder.getInstance().getRenderableList().remove(object);
			}
		}
		
		if (enemyCount > 4) {
			isBossAppeared = true;
			Enemy boss = new BossEnemy(GameWindow.SCREEN_WIDTH , 130 , -2 , 0 ,0 ,0 ,100 , Resource.boss);
			screenObjects.add(boss);
			RenderableHolder.getInstance().add(boss);
			enemyCount = 0;
		} else if (enemyCount > 3) {
			spawnDelay = 150;
		} else if (enemyCount > 2) {
			spawnDelay = 180;
		} else if (enemyCount > 1) {
			spawnDelay = 210;
		} else {

		}

		for (ScreenObject object : screenObjects) {
			object.update();
			if(object instanceof Enemy){
				for (ScreenObject bullet : screenObjects) {
					if(bullet instanceof Bullet && ((Enemy) object).collideWith(bullet)){
						if(((Bullet)bullet).shooter instanceof Player){
							((Enemy) object).hit();
							bullet.isDestroyed = true;
						}
					}
				}
				if(player.collideWith(object)){
					player.hit();
					((Enemy) object).isDestroyed = true;
				}
			}
			else if(object instanceof Bullet){
				if(((Bullet)object).shooter instanceof Enemy && player.collideWith(object)){
					player.hit();
					object.isDestroyed = true;
				}
			}
		}

		if (!isBossAppeared) {
			if (spawnDelayCounter < spawnDelay) {
				spawnDelayCounter++;
			} else {
				int spawn = RandomUtility.randomEnemySpawn();
				if (spawn == 1) {
					Enemy e = new NormalEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -3, 0, 0, 0, 10,
							Resource.lemon);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				} else if (spawn == 2) {
					Enemy e = new RushEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -1, 0, -0.15, 0, 10,
							Resource.carrot);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				} else {
					Enemy e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 10,
							Resource.tomato);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				}
				spawnDelayCounter = 0;
			}
		}

		for (ScreenObject object : screenObjects) {
			if (object.x < -70) {
				object.isDestroyed = true;
			}
		}
	}
}
