package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{

	public static final int screenWidth = 800;
	public static final int screenHeight = 600;
	private JPanel currentScene;
	
	public GameWindow(JPanel scene){
		super("Piggy Bird");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		pack();
		setVisible(true);
		//setResizable(false);
		currentScene.requestFocus();
	}
	
	public void switchScene(JPanel scene){
		getContentPane().remove(currentScene);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		getContentPane().validate();
		pack();
		currentScene.requestFocus();
	}
	
	public JPanel getCurrentScene(){
		return currentScene;
	}
}
