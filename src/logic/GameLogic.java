package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import gui.GameBackground;
import gui.GameManager;
import gui.GameScreen;
import gui.GameWindow;
import gui.HighScoreUtility;
import res.RandomUtility;
import res.Resource;

public class GameLogic {

	private static GameLogic instance = new GameLogic();
	protected GameBackground gameBackground = new GameBackground();
	public Player player;
	public PlayerStatus status = new PlayerStatus();
	protected BossStatus bossStatus = new BossStatus();
	public static List<ScreenObject> screenObjects = new CopyOnWriteArrayList<>();
	protected BossEnemy boss;
	private int spawnDelay = 250;
	private int spawnDelayCounter = 0;
	private int winDelay = 400;
	private int winDelayCounter = 0;
	protected int enemyCount = 0;
	protected boolean isBossAppeared = false;
	
	protected Thread bossThread;

	public static GameLogic getInstance() {
		return instance;
	}
	
	
	public void highScoreRecord(){
		HighScoreUtility.recordHighScore(status.getScore());
	}
	
	public static void resetInstance()
	{
		screenObjects.clear();
		RenderableHolder.getInstance().clearEntities();
		instance = new GameLogic();
	}

	public GameLogic() {
		player = new Player(60, 60, 0, 0, 0, 0.25, 10, Resource.character);
		RenderableHolder.getInstance().add(gameBackground);
		RenderableHolder.getInstance().add(player);
		RenderableHolder.getInstance().add(status);
		RenderableHolder.getInstance().add(bossStatus);
	}

	public void logicUpdate() {
		gameBackground.update();
		player.update();
		
		//Game Over
		if(GameScreen.gameOverScreen){
			if (winDelayCounter < winDelay-200) {
				winDelayCounter++;
				System.out.println(winDelayCounter);
			}
			else {
				highScoreRecord();
				GameManager.gotoTitle();
			}
		}
				
		//You win game 
		if(GameScreen.isWin && !GameScreen.gameOverScreen){
			for(IRenderable object : RenderableHolder.getInstance().getRenderableList()) {
				if(object instanceof Enemy || object instanceof Bullet){
					((ScreenObject)object).isDestroyed = true;
				}
			}
			if (winDelayCounter < winDelay) {
				winDelayCounter++;
				System.out.println(winDelayCounter);
			}
			else {
				highScoreRecord();
				GameManager.gotoTitle();
			}
		}
		
		//remove destroy screen object
		for (ScreenObject object : screenObjects) {
			if (object.isDestroyed()) {
				if(object instanceof BossEnemy) {
					GameScreen.isWin = true;
				}
				else{
					screenObjects.remove(object);
					RenderableHolder.getInstance().getRenderableList().remove(object);
				}
			}
		}
		
		if (enemyCount > 0 && !isBossAppeared) {
		//increase difficulty

			isBossAppeared = true;
			boss = new BossEnemy(GameWindow.SCREEN_WIDTH , 130 , -2 , 0 ,0 ,0 , 20 , 200 , Resource.boss);
			screenObjects.add(boss);
			RenderableHolder.getInstance().add(boss);
			
			enemyCount = -1;
		} else if (enemyCount > 30) {
			spawnDelay = 150;
		} else if (enemyCount > 20) {
			spawnDelay = 180;
		} else if (enemyCount > 10) {
			spawnDelay = 210;
		}

		//check collideWith
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
					if (object instanceof Explosion)
					{
						if (((Explosion)object).isExplode)
						{
							player.hit();
							((Enemy) object).isDestroyed = true;
						}
					}
					else
					{
						player.hit();
						((Enemy) object).isDestroyed = true;
					}
				}
			}
			else if(object instanceof Bullet){
				if(((Bullet)object).shooter instanceof Enemy && player.collideWith(object)){
					player.hit();
					object.isDestroyed = true;
				}
			}
		}

		//random enemy spawn
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

		//out of screen
		for (ScreenObject object : screenObjects) {
			if (object.x < -70 || object.x > GameWindow.SCREEN_WIDTH + 1) {
				object.isDestroyed = true;
			}
		}
	}
}
