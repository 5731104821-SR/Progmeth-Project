package res;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
		public static BufferedImage boss_rage_gold;
		public static BufferedImage boss_dead;
		public static BufferedImage bullet_cake;
		public static BufferedImage bullet_lemon;
		public static BufferedImage bomb_size;
		public static BufferedImage bomb;
		public static BufferedImage buttonImage;
		public static BufferedImage crown;
		public static BufferedImage win;
		public static BufferedImage congrat;
		public static AudioClip jump;
		public static AudioClip shoot;
		public static AudioClip pause;
		public static AudioClip button_select;
		public static AudioStream titleBGM;
		public static AudioStream playBGM;
		public static AudioStream bossBGM;
		public static AudioStream winBGM;
		public static AudioStream gameoverBGM;
		public static Font titleFont = new Font("Tahoma" , Font.BOLD , 40);
		public static Font instructionFont = new Font("Angsana NEW" , Font.BOLD , 30);
		public static Font countDownFont = new Font("Angsana NEW" , Font.BOLD , 120);
		public static Font pauseFont = new Font("Angsana NEW" , Font.BOLD , 120);
		public static Font statusFont = new Font("Tahoma" , Font.BOLD , 30);
		public static Font hpFont = new Font("Tahoma" , Font.BOLD , 10);
		
		static {
			ClassLoader loader = Resource.class.getClassLoader();
			try {
				background = ImageIO.read(loader.getResource("res/PiggyBirdBG.png"));
				character = ImageIO.read(loader.getResource("res/PiggyBirdChar3.png"));
				logo =  ImageIO.read(loader.getResource("res/Logo.png"));
				lemon = ImageIO.read(loader.getResource("res/lemon.png"));
				carrot = ImageIO.read(loader.getResource("res/carrot.png"));
				tomato = ImageIO.read(loader.getResource("res/Tomato_normal.png"));
				tomato_angry = ImageIO.read(loader.getResource("res/Tomato_angry.png"));
				boss = ImageIO.read(loader.getResource("res/Bokchoi_normal.png"));
				boss_rage = ImageIO.read(loader.getResource("res/Bokchoi_rage.png"));
				boss_rage_gold = ImageIO.read(loader.getResource("res/Bokchoi_rage_gold.png"));
				boss_dead = ImageIO.read(loader.getResource("res/Bokchoi_annoy.png"));
				bullet_cake = ImageIO.read(loader.getResource("res/Cake.png"));
				bullet_lemon = ImageIO.read(loader.getResource("res/Lemon_Bullet.png"));
				bomb_size = ImageIO.read(loader.getResource("res/bomb_size.png"));
				bomb = ImageIO.read(loader.getResource("res/bomb.png"));
				buttonImage = ImageIO.read(loader.getResource("res/Frame.png"));
				crown = ImageIO.read(loader.getResource("res/crown.png"));
				win = ImageIO.read(loader.getResource("res/Logo_win.png"));
				congrat = ImageIO.read(loader.getResource("res/Congrat.png"));
				jump = Applet.newAudioClip(loader.getResource("res/jump.wav"));
				shoot = Applet.newAudioClip(loader.getResource("res/shoot.wav"));
				pause = Applet.newAudioClip(loader.getResource("res/pause.wav"));
				button_select = Applet.newAudioClip(loader.getResource("res/button_select.wav"));
				titleBGM = new AudioStream(loader.getResourceAsStream("res/TitleBGM.mp3"));
				playBGM = new AudioStream(loader.getResourceAsStream("res/PlayBGM.mp3"));
				bossBGM = new AudioStream(loader.getResourceAsStream("res/BossBattleBGM.mp3"));
				winBGM = new AudioStream(loader.getResourceAsStream("res/win.mp3"));
				gameoverBGM = new AudioStream(loader.getResourceAsStream("res/Gameover.mp3"));
			} 
			catch (Exception e) {
				//System.out.println("null");
				e.printStackTrace();
			}
		}
		
		public static void playBGM(AudioStream as)
		{
			AudioPlayer.player.stop(Resource.titleBGM);
			AudioPlayer.player.start(as);
		}

}
