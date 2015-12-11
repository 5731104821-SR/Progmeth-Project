package gui;

import java.awt.event.KeyEvent;

import logic.GameLogic;

public class GameManager {
		
		private static GameTitle gameTitle;
		private static GameScreen gameScreen;
		private static GameWindow gameWindow;
		
		public static void runGame(){
			GameLogic gameLogic = GameLogic.getInstance();
			gameTitle = new GameTitle();
			gameScreen = new GameScreen();
			gameWindow = new GameWindow(gameScreen);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true) {
						try {
							Thread.sleep(12);
						} 
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gameWindow.repaint();
						if(gameScreen.isStart && !gameScreen.isPaused && !gameScreen.isDead){
							gameScreen.gameBackground.update();
							gameLogic.logicUpdate();
						}
						if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
							InputUtility.setKeyTriggered(KeyEvent.VK_SPACE , false);
						}
						if(InputUtility.getKeyTriggered(KeyEvent.VK_W)){
							InputUtility.setKeyTriggered(KeyEvent.VK_W , false);
						}
						if(InputUtility.isMouseLeftClicked()){
							InputUtility.setMouseLeftTriggered(false);
						}
					}
				}
			}).start();
		}
		
		public static void newGame(){
			gameWindow.switchScene(gameScreen);
		}
}
