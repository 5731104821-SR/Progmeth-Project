package gui;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameLogic;
import logic.RenderableHolder;
import logic.SoundRunable;
import res.Resource;

public class GameManager {

	private static GameTitle gameTitle;
	private static GameScreen gameScreen;
	private static GameWindow gameWindow;
	private static GameLogic gameLogic;
	private static boolean isEndGame; //true when restart game
	
	public static void setWindowScreen() {
		gameWindow = new GameWindow(new JPanel());
	}

	public static void runGame() {
		new Thread(new Runnable() {
			// This thread is for run game without pause
			@Override
			public void run() {
				gameLogic = GameLogic.getInstance();
				gameTitle = new GameTitle();
				gameScreen = new GameScreen();
				gameWindow.switchScene(gameTitle);
				isEndGame = false;
				try {
					while (!isEndGame) {
						synchronized (RenderableHolder.getInstance().getRenderableList()) {
							Thread.sleep(12);
							if (gameScreen.isPaused) {
								RenderableHolder.getInstance().getRenderableList().wait();
							}
							if ((gameScreen.isStart && !gameScreen.isGameOver) || gameScreen.gameOverScreen) {
								gameLogic.logicUpdate();
							}
							if (gameScreen.isGameOver) {
								GameLogic.getInstance().player.gameOver();
							}
						}
						if (InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)) {
							InputUtility.setKeyTriggered(KeyEvent.VK_SPACE, false);
						}
						if (InputUtility.getKeyTriggered(KeyEvent.VK_W)) {
							InputUtility.setKeyTriggered(KeyEvent.VK_W, false);
						}
						if (InputUtility.isMouseLeftClicked()) {
							InputUtility.setMouseLeftTriggered(false);
						}
						gameWindow.repaint();
					}
				} catch (InterruptedException e) {
					//not care
					e.printStackTrace();
				}
			}
		}).start();

	}
	
	public static void playSoundThread(){
		SoundRunable sound1 = new SoundRunable(Resource.playBGM , null);
		Thread th1 = new Thread(sound1);
		th1.start();
		SoundRunable sound2 = new SoundRunable(Resource.bossBGM , th1);
		Thread th2 = new Thread(sound2);
		th2.start();
		SoundRunable sound3 = new SoundRunable(Resource.winBGM , th2);
		Thread th3 = new Thread(sound3);
		th3.start();
	}

	public static void newGame() {
		gameWindow.switchScene(gameScreen);
	}

	public static void gotoTitle() {
		GameLogic.resetInstance();
		gameScreen.resetParameter();
		Resource.stopAllBGM();
		isEndGame = true;
		GameManager.runGame();
	}
}
