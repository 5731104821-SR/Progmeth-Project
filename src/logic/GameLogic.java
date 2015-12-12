package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import gui.GameBackground;
import gui.GameWindow;
import res.RandomUtility;
import res.Resource;

public class GameLogic {

	private static GameLogic instance = new GameLogic();
	protected GameBackground gameBackground = new GameBackground();
	public Player player = new Player(60, 60, 0, 0, 0, 0.25, 3, Resource.character);
	protected PlayerStatus status = new PlayerStatus();
	protected BossStatus bossStatus = new BossStatus();
	public static List<ScreenObject> screenObjects = new CopyOnWriteArrayList<>();
	protected BossEnemy boss;
	private int spawnDelay = 250;
	private int spawnDelayCounter = 0;
	protected int enemyCount = 0;
	protected boolean isBossAppeared = false;
	
	protected Thread bossThread;

	public static GameLogic getInstance() {
		return instance;
	}

	public GameLogic() {
		RenderableHolder.getInstance().add(gameBackground);
		RenderableHolder.getInstance().add(player);
		RenderableHolder.getInstance().add(status);
		RenderableHolder.getInstance().add(bossStatus);
	}

	public void logicUpdate() {
		gameBackground.update();
		player.update();
		
		for (ScreenObject object : screenObjects) {
			if (object.isDestroyed()) {
				screenObjects.remove(object);
				RenderableHolder.getInstance().getRenderableList().remove(object);
			}
		}
		
		if (enemyCount > 2) {
			isBossAppeared = true;
			boss = new BossEnemy(GameWindow.SCREEN_WIDTH , 130 , -2 , 0 ,0 ,0 ,100 , 200 , Resource.boss);
			screenObjects.add(boss);
			RenderableHolder.getInstance().add(boss);
			
			enemyCount = -1;
		} else if (enemyCount > 30) {
			spawnDelay = 150;
		} else if (enemyCount > 20) {
			spawnDelay = 180;
		} else if (enemyCount > 10) {
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
					Enemy e = new ShootingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -3, 0, 0, 0, 10, 10,
							Resource.lemon);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				} else if (spawn == 2) {
					Enemy e = new RushEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -1, 0, -0.10, 0, 5, 15,
							Resource.carrot);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				} else {
					Enemy e = new HomingEnemy(GameWindow.SCREEN_WIDTH, RandomUtility.randomStartY(), -2, 0, 0, 0, 10, 5,
							Resource.tomato);
					screenObjects.add(e);
					RenderableHolder.getInstance().add(e);
				}
				spawnDelayCounter = 0;
			}
		}

		for (ScreenObject object : screenObjects) {
			if (object.x < -70 || object.x > GameWindow.SCREEN_WIDTH + 1) {
				object.isDestroyed = true;
			}
		}
	}
}
