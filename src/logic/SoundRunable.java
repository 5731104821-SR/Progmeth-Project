package logic;

import java.applet.AudioClip;

import gui.GameScreen;
import res.Resource;

public class SoundRunable implements Runnable{
	
	private AudioClip clip;
	private Thread prevThread;
	
	public SoundRunable(AudioClip clip , Thread prevThread) {
		this.clip = clip;
		this.prevThread = prevThread;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(prevThread != null) {
				prevThread.join();
			}
			clip.loop();
			while(true) {
				synchronized (clip) {
					//if(GameScreen.isPaused){
					//	clip.wait();
					//}
					if(GameScreen.isGameOver){
						Resource.stopAllBGM();
						break;
					}
					if(GameLogic.getInstance().isBossAppeared && clip.equals(Resource.playBGM)){
						Resource.stopAllBGM();
						break;
					}
					if(GameScreen.isWin && clip.equals(Resource.bossBGM)){
						Resource.stopAllBGM();
						break;
					}
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
