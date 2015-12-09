package res;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
		public static BufferedImage background;
		public static BufferedImage character;
		public static Font titleFont = new Font("Tahoma" , Font.BOLD , 40);
		public static Font instructionFont = new Font("Angsana NEW" , Font.BOLD , 30);
		public static Font countDownFont = new Font("Angsana NEW" , Font.BOLD , 120);
		
		static {
			ClassLoader loader = Resource.class.getClassLoader();
			try {
				background = ImageIO.read(loader.getResource("res/PiggyBirdBG.png"));
				character = ImageIO.read(loader.getResource("res/PiggyBirdChar2.gif"));
			} 
			catch (IOException e) {
				background = null;
				character = null;
			}
		}

}
