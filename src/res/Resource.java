package res;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
		public static BufferedImage background;
		public static BufferedImage character;
		public static BufferedImage logo;
		public static BufferedImage lemon;
		public static BufferedImage carrot;
		public static BufferedImage tomato;
		public static BufferedImage tomato_angry;
		public static BufferedImage boss;
		public static BufferedImage boss_rage;
		public static BufferedImage boss_dead;
		public static BufferedImage bullet_cake;
		public static BufferedImage bullet_lemon;
		public static Font titleFont = new Font("Tahoma" , Font.BOLD , 40);
		public static Font instructionFont = new Font("Angsana NEW" , Font.BOLD , 30);
		public static Font countDownFont = new Font("Angsana NEW" , Font.BOLD , 120);
		
		static {
			ClassLoader loader = Resource.class.getClassLoader();
			try {
				background = ImageIO.read(loader.getResource("res/PiggyBirdBG.png"));
				character = ImageIO.read(loader.getResource("res/PiggyBirdChar2.gif"));
				logo =  ImageIO.read(loader.getResource("res/Logo.png"));
				lemon = ImageIO.read(loader.getResource("res/lemon.png"));
				carrot = ImageIO.read(loader.getResource("res/carrot.png"));
				tomato = ImageIO.read(loader.getResource("res/Tomato_normal.png"));
				tomato_angry = ImageIO.read(loader.getResource("res/Tomato_angry.png"));
				boss = ImageIO.read(loader.getResource("res/Bokchoi_normal.png"));
				boss_rage = ImageIO.read(loader.getResource("res/Bokchoi_rage.png"));
				boss_dead = ImageIO.read(loader.getResource("res/Bokchoi_annoy.png"));
				bullet_cake = ImageIO.read(loader.getResource("res/Cake.png"));
				bullet_lemon = ImageIO.read(loader.getResource("res/Lemon_Bullet.png"));
			} 
			catch (IOException e) {
				System.out.println("null");
			}
		}

}
