package gui;

import java.awt.event.KeyEvent;

import logic.GameLogic;
import logic.RenderableHolder;

public class GameManager {

	private static GameTitle gameTitle;
	private static GameScreen gameScreen;
	private static GameWindow gameWindow;
	private static GameLogic gameLogic;

	public static void runGame() {
		gameLogic = GameLogic.getInstance();
		gameTitle = new GameTitle();
		gameScreen = new GameScreen();
		gameWindow = new GameWindow(gameScreen);

		new Thread(new Runnable() {
			// This thread is for run game without pause
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true) {
						synchronized (RenderableHolder.getInstance().getRenderableList()) {
							Thread.sleep(12);
							if (gameScreen.isPaused) {
								RenderableHolder.getInstance().getRenderableList().wait();
							}
							if (gameScreen.isStart && !gameScreen.isGameOver) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	public static void newGame() {
		gameWindow.switchScene(gameScreen);
	}

	public static void gotoTitle() {
		GameLogic.resetInstance();
		gameWindow.switchScene(gameTitle);
		gameScreen = new GameScreen();
	}
}
