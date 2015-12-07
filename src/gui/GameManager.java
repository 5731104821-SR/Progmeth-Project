package gui;
public class GameManager {
		
		private static GameTitle gameTitle;
		private static GameScreen gameScreen;
		private static GameWindow gameWindow;
		
		public static void runGame(){
			gameTitle = new GameTitle();
			gameWindow = new GameWindow(gameTitle);
			gameScreen = new GameScreen();
			
			while(true) {
				try {
					Thread.sleep(12);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameTitle.repaint();
			}
		}
		
		public static void newGame(){
			gameWindow.switchScene(gameScreen);
		}
}
