package gui;

import java.awt.event.KeyEvent;

import logic.GameLogic;
import logic.RenderableHolder;

public class GameManager {
		
		private static GameTitle gameTitle;
		private static GameScreen gameScreen;
		private static GameWindow gameWindow;
		
		public static void runGame(){
			GameLogic gameLogic = GameLogic.getInstance();
			gameTitle = new GameTitle();
			gameScreen = new GameScreen();
			gameWindow = new GameWindow(gameScreen);
			
			while(true) {
				try {
					Thread.sleep(12);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameWindow.repaint();
				if(gameScreen.isStart){
					gameLogic.logicUpdate();
				}
				if(InputUtility.getKeyTriggered(KeyEvent.VK_SPACE)){
					InputUtility.setKeyTriggered(KeyEvent.VK_SPACE , false);
				}
			}
		}
		
		public static void newGame(){
			gameWindow.switchScene(gameScreen);
		}
}
