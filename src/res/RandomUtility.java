package res;

import java.util.Random;

import gui.GameWindow;

public class RandomUtility {

	public static int randomEnemySpawn(){
		return new Random().nextInt(3)+1;
	}
	
	public static int randomStartY(){
		return new Random().nextInt(GameWindow.SCREEN_HEIGHT - 70);
	}
}
